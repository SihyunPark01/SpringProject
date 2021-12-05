package kr.co.farmstory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.farmstory.service.MemberService;
import kr.co.farmstory.vo.MemberVo;
import kr.co.farmstory.vo.TermsVo;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/member/login")
	public String login(Model model, String success) {
		
		model.addAttribute("success", success);
		
		return "/member/login";
	}
	
	@PostMapping("/member/login")
	public String login(HttpSession sess, String uid, String pass) {
		
		MemberVo vo = service.selectMember(uid, pass); //얘는 왜 MemberVo에 담는거야?아 if문에 쓸거니까!
		
		if(vo == null) {//회원이 아닐경우
			return "redirect:/member/login?success=100";
		}else {//회원이 맞으면
			sess.setAttribute("sessMember", vo);
			return "redirect:/index";
		}
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession sess) {
		sess.invalidate(); //현재 사용자 정보객체 세션 삭제
		return "redirect:/member/login?success=101";
	}
	
	
	@GetMapping("/member/terms")
	public String terms(Model model) {
		
		TermsVo vo = service.selectTerms();
		model.addAttribute(vo);
		
		return "/member/terms";
	}
	
	
	@GetMapping("/member/register")
	public String register() {
		return "/member/register";
	}
	
	@PostMapping("/member/register")
	public String register(MemberVo vo, HttpServletRequest req) {
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		service.insertMember(vo); //뷰페이지에서 필요한게 없으니 담을 필요 없겠지...?
		return "redirect:/member/login";
	}
	
	
	
	@ResponseBody       //클라이언트로가는 response객체를 body에 채워서 클라이언트를 거치지말고 보내라
	@GetMapping("/member/checkUid")
	public String checkUid(String uid) {
		System.out.println("uid : "+uid);
		
		int result = service.selectCountUid(uid);
		
		//json라이브러리 추가해야함!!! pom.xml에 복붙!
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		return new Gson().toJson(json); 
		//"{'result':"+result+"}"; 이렇게 써도 되지만 많아질수록 너무 복잡해지니 json라이브러리를 쓰자 alert창으로 뜰것이야
	}
	
	@ResponseBody   /*response객체에 실어서 전송, 자바객체를 HTTP요청의 바디내용으로 매핑하여 클라이언트로 전송*/
	@GetMapping("/member/checkNick")
	public String checkNick(String nick){//()안에 파라미터 이름 들어가쟈냐
		System.out.println("nick : "+nick);
		int result = service.selectCountNick(nick);
		
		//Json 객체 생성 --- Gson 라이브러리 추가 
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		return new Gson().toJson(json);
		//return "/member/register";
	}
	@ResponseBody       
	@GetMapping("/member/checkEmail")
	public String checkEmail(String email) {
		System.out.println("email : "+email);
		
		int result = service.selectCountEmail(email);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		return new Gson().toJson(json); 
	}
	@ResponseBody       
	@GetMapping("/member/checkHp")
	public String checkHp(String hp) {
		System.out.println("hp : "+hp);
		
		int result = service.selectCountHp(hp);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		return new Gson().toJson(json); 
	}
	
	
	
}
