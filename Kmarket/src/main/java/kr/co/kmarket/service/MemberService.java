package kr.co.kmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.MemberDao;
import kr.co.kmarket.vo.MemberTermsVo;
import kr.co.kmarket.vo.MemberVo;


@Service
public class MemberService {

	@Autowired
	private MemberDao dao; //이건 mybatis 객체 주입
	
	//@Autowired
	//private MemberRepo repo; //이건 JPA 객체 주입
	
	//@Autowired
	//private MemberTermsRepo repoTerms; //이건 JPA 객체 주입
	
	
	public void insertMember(MemberVo vo) {
		//Mybatis 실행
		dao.insertMember(vo);
		
		//JPA 실행
		//repo.save(vo); //이게 insert 쿼리임. JPA객체는 쿼리문을 작성하지 않음. 회원가입처리
	};
	
	public MemberVo selectMember(MemberVo vo) {
		
		//Mybatis 실행
		MemberVo memberVo = dao.selectMember(vo);

		//JPA 실행 - 쿼리 메서드
		//MemberVo memberVo = repo.findby(); 로그인처리
				
		return memberVo;
	};
	
	public MemberTermsVo selectTerms() {
		
		//Mybatis 실행
		MemberTermsVo termsVo = dao.selectTerms();
		
		//JPA 실행
		//MemberTermsVo termsVo = repoTerms.findById(1).get();
		
		return dao.selectTerms();
		
	};
	
}
