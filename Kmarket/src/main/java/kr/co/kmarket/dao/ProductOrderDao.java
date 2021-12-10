package kr.co.kmarket.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.ProductCartVo;
import kr.co.kmarket.vo.ProductOrderVo;

@Repository
public interface ProductOrderDao {
	
	public void insertOrder(ProductOrderVo vo); //키값을 리턴받을것이므로 리턴값은 int가 되어야함
	public void insertOrderDetail (int orderId, int productCode); //키값을 리턴받을것이므로 리턴값은 int가 되어야함
	public List<ProductOrderVo> selectOrders(int orderId);
	public void selectOrder();
	public int updateOrder(ProductOrderVo vo); //업데이트 레코드 개수를 리턴하므로 int
	public void deleteOrder();  

}
