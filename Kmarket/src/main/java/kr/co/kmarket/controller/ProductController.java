package kr.co.kmarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.kmarket.service.ProductCartService;
import kr.co.kmarket.service.ProductOrderService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.MemberVo;
import kr.co.kmarket.vo.ProductCartVo;
import kr.co.kmarket.vo.ProductOrderVo;
import kr.co.kmarket.vo.ProductVo;
import kr.co.kmarket.vo.SearchVo;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	@Autowired
	private ProductCartService cartService;
	@Autowired
	private ProductOrderService orderService;
	
	
	
	@GetMapping("/product/cart")
	public String cart(HttpSession sess, Model model) {
		
		//로그인 여부 확인
		MemberVo vo = (MemberVo) sess.getAttribute("sessMember");
		
		if(vo != null) {
			
				List<ProductCartVo> cartProducts = cartService.selectCarts(vo.getUid());
				model.addAttribute("cartProducts", cartProducts);
				
			return "/product/cart";
			
		}else {
			
			return "redirect:/member/login?success=201";
		}
	}
	
	@ResponseBody
	@PostMapping("/product/cart")
	public String cart(ProductCartVo vo) {
		
		int result = cartService.selectCountCart(vo); //result값은 1또는 0임
		if(result == 0) {//장바구니에 상품이 없으면
			cartService.insertCart(vo);
		}
		
		//이게 왜 필요하냐면 view페이지의 **********ajax 처리하기 위함...
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		return new Gson().toJson(json);
	}
	
	@GetMapping("/product/cartDelete")
	public String cartDelete(int[] cartIds) { 
		
		int result = cartService.deleteCart(cartIds);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		return new Gson().toJson(json);
				
		
		/*
		for(int cartId : cartIds) {
			System.out.println("cartId : "+cartId); 확인작업, 이렇게 하면 콘솔창에 cartId 뜸!
		}*/
	}
	

	@GetMapping("/product/list")
	public String list(ProductVo vo, Model model, String pg) {
		
		System.out.println("cate1 : "+vo.getCate1());
		System.out.println("cate2 : "+vo.getCate2());
		System.out.println("order : "+vo.getOrder());
		
		// 리스트 페이지 처리
		int currentPage = service.getCurrentPage(pg); 
		int start = service.getLimitStart(currentPage);
		int total = service.selectCountTotal(vo); //전체 게시물 개수니까 쿼리문 날려야 함 -- 쿼리문작업하고돌아와
		int pageStartNum = service.getPageStartNum(total, start);
		int lastPageNum = service.getLastPageNum(total);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		
		vo.setStart(start);  //위에 있는 int start를 아래 selectProducts(vo)여기 vo에 담기위해서 vo.setStart(start) 선언해줘야함
		
		List<ProductVo> products = service.selectProducts(vo);
		CategoriesVo cateVo = service.selectCategoryTitle(vo);
		
		model.addAttribute("products", products);
		model.addAttribute("cateVo", cateVo);
		model.addAttribute("order", vo.getOrder());
		model.addAttribute("productCode", vo.getProductCode());
		
		model.addAttribute("pageStartNum", pageStartNum); 
		model.addAttribute("currentPage", currentPage); 
		model.addAttribute("lastPageNum", lastPageNum); 
		model.addAttribute("groups", groups); 

		return "/product/list";
	}
	
	@GetMapping("/product/order")
	public String order(int orderId, Model model) {
		
		List<ProductOrderVo> orderProducts = orderService.selectOrders(orderId);
		model.addAttribute("orderProducts",orderProducts);
		model.addAttribute("productOrderVo",orderProducts.get(0));
		
		return "/product/order";
	}
	
	
	@ResponseBody
	@PostMapping("/product/order")
	public String order(ProductOrderVo vo) {
		
		// 장바구니 주문하기 상품 주문 테이블 저장
		orderService.insertOrder(vo); //여기 vo 안에 orderId가 있음

		// 장바구니제품을 주문 테이블에 Insert 후 주문번호 가져오기
		int orderId = vo.getOrderId(); 
		
		// 주문번호 상품코드 입력하기
		for(int productCode : vo.getProductCodes()) {
			
			orderService.insertOrderDetail(orderId, productCode);
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("orderId", orderId);
		
		return new Gson().toJson(json);
		
	}
	
	
	@GetMapping("/product/order-complete")
	public String orderComplete() {
		return "/product/order-complete";
	}
	
	
	@ResponseBody
	@PostMapping("/product/order-complete")
	public Map<String, Integer> orderComplete(ProductOrderVo vo) {
		
		//최종주문 완료하기
		int result = orderService.updateOrder(vo);
		
		//Jackson 라이브러리로 자바 map 구조체를 Json 데이터로 변환
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result); //result 1이 리턴되는 것임
		
		return map;
	}
	
	@GetMapping("/product/search")
	public String search(SearchVo vo, Model model) {
		
		List<ProductVo> products = service.selectProductSearch(vo);
		model.addAttribute("products", products);
		model.addAttribute("keyword", vo.getKeyword());
		model.addAttribute("productCount", products.size());
		
		return "/product/search";
	}
	
	
	@GetMapping("/product/view")
	public String view(HttpSession sess, int productCode, Model model) {
		
		//현재 로그인 아이디 가져오기
		//MemberVo memberVo = (MemberVo) sess.getAttribute("sessMember");
		
		//String uid = memberVo.getUid();
		
		//뷰 페이지에 출력할 상품 DB에서 가져오기
		ProductVo product = service.selectProduct(productCode); 
				
		
		
		model.addAttribute("product", product); //강사님은 productVo에 담음 그래서 view페이지에 모두 productVo.~~~임 (나는 product.~~~이구)
		
		return "/product/view";
	}
}
