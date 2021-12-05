 
 
 //정규표현식(Regular Expression) /^ [   ] $/ 서칭해서 붙여넣으면 됨.
    var reUid = /^[a-z]+[a-z0-9]{3,10}$/; //3,10도 붙어적어야함!!! //아이디 - 영문소문자 + 영문소문자, 숫자 를 4자리에서 10자리까지만 입력가능
    var rePass = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,}$/;
    var reName = /^[가-힣]{2,10}$/;
    var reNick = /^[a-z가-힣0-9]{2,10}$/;
    
 //최종 유효성 검사에 사용될 상태변수
    var isUidOk = false;
    var isPassOk = false;
    var isNameOk = false;
    var isNickOk = false;
    
	$(document).ready(function(){
	 		
	 		$('.register > form').submit(function(){
	 			
	 			if(!isUidOk){ // 
	 				alert('아이디가 유효하지 않습니다. 다시 입력하세요.');
	 				return false; //false : 서버로 전송 취소, 막음. 
	 			}
	 			
	 			if(!isPassOk){
	 				alert('비밀번호가 유효하지 않습니다. 다시 입력하세요.');
	 				return false; //false : 서버로 전송 취소, 막음. 
	 			}
	 			
	 			if(!isNameOk){
	 				alert('이름이 유효하지 않습니다. 다시 입력하세요.');
	 				return false; //false : 서버로 전송 취소, 막음. 
	 			}
	 			
	 			if(!isNickOk){
	 				alert('닉네임이 유효하지 않습니다. 다시 입력하세요.');
	 				return false; //false : 서버로 전송 취소, 막음. 
	 			}
	 			
	 			return true; //true : 전송하기
	 			
	 		});
	 		
	 	});

    	
    	//아이디 중복체크
    	$(function(){
    		
			$('input[name=uid]').focusout(function(){ //마우스 포커스가 빠질 때
				//alert('포커스아웃!');
				
				// 입력한 아이디 가져오기
				var uid = $(this).val(); //현재 입력한 아이디값을 uid라는 변수에 담아라
				
				// Json 생성
				var jsonData = {'uid':uid }; //키와 밸류 순서로 
					
				/* Ajax함수 중 get(), post()도 있음
				$.get('/member/checkUid', jsonData, function(data){
					alert(data.result);
					
				}, 'json');
				
				});
				$.post();
				
				$.ajax({
					url: '',
					type: 'get' or 'post'
				});
				*/
								
				// Ajax 통신
				$.ajax({
					url: '/member/checkUid',           			//요청할 서버 주소
					type: 'GET',								//요청방식 Get or Post
					data: jsonData,								//서버로 전송되는 데이터
					dataType: 'json',							//서버로부터 전송받는 데이터 형식
					success: function(data){					//이건 그냥 외워
						//alert(data.result);
						
						if(uid == ''){
							$('.resultId').css('color', 'green').text('아이디를 입력하세요.');	
						}
						else if(data.result == 1){
							$('.resultId').css('color', 'red').text('이미 사용중인 아이디입니다.');
								isUidOk = false;
						}else{
							if(reUid.test(uid)){
								$('.resultId').css('color','gray').text('사용 가능한 아이디입니다.');
								isUidOk = true;
							}else{
								$('.resultId').css('color','red').text('아이디는 영문 소문자, 숫자 포함 4~10자여야 합니다.');
								isUidOk = false;						
							}
							isUidOk = true;
						}
					} 		
				});				
			});
			
			//비밀번호 유효성 검사
			$('input[name=pass2]').focusout(function(){
				
				var pass1 = $('input[name=pass1]').val();
				var pass2 = $('input[name=pass2]').val();
				
				if(pass1 == pass2){ //비번 확인란이랑 일치한다면
					
					if(rePass.test(pass2)){
						$('.resultPass').css('color','gray').text('비밀번호가 일치합니다.');
						isPassOk = true;
					}else{
						$('.resultPass').css('color','red').text('비밀번호는 영문,숫자 조합 최소 4이상이어야 합니다.');
						isPassOk = false;
					}
					
				}else{ // 일치하지않는다면
					$('.resultPass').css('color','red').text('비밀번호가 일치하지 않습니다.');
					isPassOk = false;
				}
				isPassOk = true;
			});	
    	
			// 이름 유효성 검사
    		$('input[name=name]').focusout(function(){
				
				var name = $(this).val();
				
				if(reName.test(name)){
					$('.resultName').css('color','gray').text('이름이 유효합니다.');
					isNameOk = true;
				}else{
					$('.resultName').css('color','red').text('이름이 유효하지 않습니다.');
					isNameOk = false;
				}
			});

			//닉네임 중복체크
			$('input[name=nick]').focusout(function(){
				
				var nick = $(this).val();
				
				// Json 생성
				var jsonData = {'nick':nick }; //키와 밸류 순서로 
				
				//DB에서 아이디 있는지 확인 작업
				$.ajax({
					url: '/member/checkNick',      //'/member/checkNick?nick='+nick,이렇게 잡아줘도 됨					
					type: 'GET',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						if(data.result == 1){
							$('.resultNick').css('color', 'red').text('이미 사용중인 닉네임입니다.');
								isNickOk = false;
						}else{
							if(reNick.test(nick)){
								$('.resultNick').css('color','gray').text('사용 가능한 닉네임입니다.');
								isNickOk = true;
							}else{
								$('.resultNick').css('color','red').text('닉네임은 영문 소문자, 숫자 포함 4~10자여야 합니다.');
								isNickOk = false;						
							}
						}
					}
				});
			});			
    				
			// 이메일 중복체크
    		$('input[name=email]').focusout(function(){
    			
    			var email = $(this).val();
    			
    			// Json 생성
				var jsonData = {'email':email };  
    			
    			//DB에서 가져와서 아이디 있는지 없는지 확인해봐야하는 작업
    			$.ajax({
    				url: '/member/checkEmail',
    				type: 'get',
    				data: jsonData,
    				dataType: 'json',
    				success: function(data){
    					
    					if(data.result == 1){
    						$('.resultEmail').css('color', 'red').text('이미 사용중인 이메일 입니다.')
    					}else{
    						$('.resultEmail').css('color', 'green').text('사용 가능한 이메일 입니다.')
    					}
    				}	
    			});
    		});
					
			// 휴대폰 중복체크
			$('input[name=hp]').focusout(function(){
				
				var hp = $(this).val();
				
				// Json 생성
				var jsonData = {'hp': hp };  
				
				//DB에서 가져와서 아이디 있는지 없는지 확인해봐야하는 작업
    			$.ajax({
    				url: '/member/checkHp',
    				type: 'get',
    				data: jsonData,
    				dataType: 'json',
    				success: function(data){
    					
    					if(data.result == 1){
    						$('.resultHp').css('color', 'blue').text('이미 사용중인 번호입니다.')
    					}else{
    						$('.resultHp').css('color', 'gray').text('사용 가능한 번호입니다.')
    						
    					}
    				}	
    			});
			});
    	});    
       
    