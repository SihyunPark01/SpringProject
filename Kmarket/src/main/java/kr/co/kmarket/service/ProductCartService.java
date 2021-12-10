package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.ProductCartDao;
import kr.co.kmarket.dao.ProductDao;
import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductCartVo;
import kr.co.kmarket.vo.ProductVo;
import kr.co.kmarket.vo.SearchVo;

@Service
public class ProductCartService {

	@Autowired
	private ProductCartDao dao;
	
	public void insertCart(ProductCartVo vo) {
		dao.insertCart(vo);
	}
	
	public int selectCountCart(ProductCartVo vo) {
		return dao.selectCountCart(vo);
	}

	public void selectCart() {}
	public List<ProductCartVo> selectCarts(String uid){
		return dao.selectCarts(uid);
		
	}
	public void updateCarts(){}
	
	public int deleteCart(int[] cartIds){
		return dao.deleteCart(cartIds);
	}
	
}
