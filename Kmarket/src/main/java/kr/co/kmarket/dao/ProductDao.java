package kr.co.kmarket.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.CategoriesVo;
import kr.co.kmarket.vo.ProductVo;
import kr.co.kmarket.vo.SearchVo;

@Repository
public interface ProductDao {
	
	public void insertProduct();
	
	public void insertCart();
	
	public ProductVo selectProduct(int productCode);
	public List<ProductVo> selectProducts(ProductVo vo);
	public CategoriesVo selectCategoryTitle(ProductVo vo);
	public int selectCountTotal(ProductVo vo);
	public List<ProductVo> selectProductSearch(SearchVo vo);
	public void updateProduct();
	public void deleteProduct();
	

}
