package tw.group5.model.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	public List<Order> selectCustom(String hql) {
		return orderDao.selectCustom(hql);
	}
	
	// Admin - 1
	public List<Order> selectTop20() {
		return orderDao.selectTop20();
	}
	
	// Admin - 2
	public boolean update(Order newBean) {
		return orderDao.update(newBean);
	}
	
	// Admin - 3
	public boolean delete(Order orderBean) {
		return orderDao.delete(orderBean);
	}
}
