package kr.co.kmarket.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductVo;

@Repository
public interface MainDao {

	public List<CategoriesVo> selectCategories();
	public List<ProductVo> selectMainProduct(String order);   //mapper의 조건문에서 order사용되므로
	
	
}
