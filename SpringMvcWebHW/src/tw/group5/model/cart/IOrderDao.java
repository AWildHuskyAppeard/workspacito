package tw.group5.model.cart;

import java.util.*;

import org.springframework.stereotype.Repository;

public interface IOrderDao {
	// 建立連線、提供SQL方法
	
	Order insert(Order orderBean);
	Order select(String P_ID);
	List<Order> selectCustom(String hql);
	List<Order> selectAll();
	
	/**
	
	// 回傳資料筆數；0表示沒變化、-1表示出問題、1以上表示更改比數
	boolean updateOrder(OrderBean orderBean, String str3, Object obj4); 
	
	// order只會修正資料，紀錄會一直留下
	boolean deleteOrder(String O_ID); 
	*/
}
