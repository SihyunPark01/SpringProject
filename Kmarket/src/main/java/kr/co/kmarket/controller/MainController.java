package kr.co.kmarket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.service.MainService;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductVo;

@Controller
public class MainController {

	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MainService service;
	
	@GetMapping(value= {"/","/index"})
	public String index(Model model) {

		//logger.info("MainController - index");
		
		//히트상품 조회 
		List<ProductVo> ProductsHit = service.selectMainProduct("hit");
		//List<ProductVo> ProductsRecommend = service.selectMainProduct("score");
		//List<ProductVo> ProductsLatest = service.selectMainProduct("rdate");
		//List<ProductVo> ProductsDiscount = service.selectMainProduct("discount");
		List<ProductVo> ProductsBest = service.selectMainProduct("sold");
		
		model.addAttribute("ProductsHit", ProductsHit);
		//model.addAttribute("ProductsRecommend", ProductsRecommend);
		//model.addAttribute("ProductsLatest", ProductsLatest);
		//model.addAttribute("ProductsDiscount", ProductsDiscount);
		model.addAttribute("ProductsBest", ProductsBest);
		
		//이 방법 하지말고
		//List<CategoriesVo> cates = service.selectCategories();
		//model.addAttribute("cates", cates);
		
		return "/index";
	}
	
	@ResponseBody
	@GetMapping("/getCategories")
	public List<CategoriesVo> getCategories() {
		
		//logger.info("MainController - getCategories");
		
		List<CategoriesVo> cates = service.selectCategories();
		return cates; // /getCategories 링크 들어가서 확인해봐
		
	}
	
	//ajax 추천상품 요청하기 (index)
	@ResponseBody
	@GetMapping("/getMainProduct")
	public List<ProductVo> getMainProduct(String order) {
		
		//logger.info("MainController - getMainProduct");
		
		List<ProductVo> products = service.selectMainProduct(order);
		return products;
	}
	
}
