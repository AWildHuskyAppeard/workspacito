package tw.group5.model.cart.old;

import java.util.*;

public interface ICartDAO {
	// 建立連線、提供SQL方法
	
	Order insertOrder(Order orderBean);
	Order selectOrder(String P_ID);
	Order selectCustom(String hql);
	List<Order> selectAllOrder();
	
	/**
	
	// 回傳資料筆數；0表示沒變化、-1表示出問題、1以上表示更改比數
	boolean updateOrder(OrderBean orderBean, String str3, Object obj4); 
	
	// order只會修正資料，紀錄會一直留下
	boolean deleteOrder(String O_ID); 
	*/
}
