package kr.co.farmstory.dao;

import org.springframework.stereotype.Repository;

import kr.co.farmstory.vo.MemberVo;
import kr.co.farmstory.vo.TermsVo;

@Repository
public interface MemberDao {
	
	public void insertMember(MemberVo vo);
	
	//회원가입 중복체크작업
	public int selectCountUid(String uid); //맵퍼 #{uid}에 맵핑시키려면 String uid가 필요함
	public int selectCountNick(String nick); 
	public int selectCountEmail(String email); 
	public int selectCountHp(String hp); 
	
	
	public MemberVo selectMember(String uid, String pass); //쿼리문에 맵핑시킬놈을 변수로 가져옴
	
	public TermsVo selectTerms();
	public void selectMembers();
	public void updateMember();
	public void deleteMember();
}
