package kr.co.farmstory.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.farmstory.service.MainService;
import kr.co.farmstory.vo.ArticleVo;
import kr.co.farmstory.vo.MemberVo;

@Controller
public class MainController {

	@Autowired
	private MainService service;
	
	
	@GetMapping(value = {"/","/index"})
	public String index(HttpSession sess, Model model, String success) {
		
		MemberVo vo = (MemberVo) sess.getAttribute("sessMember");
		model.addAttribute("success", success);

		List<ArticleVo> latest1 = service.selectLatest("grow");
		List<ArticleVo> latest2 = service.selectLatest("school");
		List<ArticleVo> latest3 = service.selectLatest("story");
		
		model.addAttribute("latest1", latest1);
		model.addAttribute("latest2", latest2);
		model.addAttribute("latest3", latest3);
		
		
		return "/index";
	}
	
	@GetMapping("/introduction/hello")
	public String hello() {
		return "/introduction/hello";
	}
	@GetMapping("/introduction/direction")
	public String direction() {
		return "/introduction/direction";
	}
}
