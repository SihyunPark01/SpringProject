$(document).ready(function(){
	
	$('input[name=nick]').focusout(function(){
				
				var nick = $(this).val();
				
				// Json 생성
				var jsonData = {'nick':nick }; //키와 밸류 순서로 
				
				//DB에서 아이디 있는지 확인 작업
				$.ajax({
					url: '/Farmstory/member/checkNick',      //'/member/checkNick?nick='+nick,이렇게 잡아줘도 됨					
					type: 'GET',
					data: jsonData,
					dataType: 'json',
					success: function(data){
												
						if(success == 100){
				    		alert('일치하는 회원이 없습니다.\n아이디, 비밀번호를 다시 확인해 주세요.');
				    	}else if(success == 101){
				    		alert('정상적으로 로그아웃이 되었습니다.');
				    	}else if(success == 102){
				    		alert('먼저 로그인을 하셔야 합니다.');
				    	}else if(success == 103){
				    		alert('글 작성을 위해 로그인을 하셔야 합니다.');
    	}
						
						
						
						
						
						
						
						
					}
					
				});
			});			
		});