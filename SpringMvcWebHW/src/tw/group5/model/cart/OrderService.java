package tw.group5.model.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService implements IOrderService{
	@Autowired // SDI✔
	private OrderDao orderDao;
	
	public Order insert(Order order) {
			return orderDao.insert(order);
		}
	
	public List<Order> selectAll() {
		return orderDao.selectAll();
	}
	
	public Order select(String P_ID) {
		return orderDao.select(P_ID);
	}
	
/**
 * 未完成品 
 **/
	public Order selectCustom(String hql) {
		return orderDao.selectCustom(hql);
	}
}
