package tw.group5.model.cart;
// 購物車的連線物件
// 要考慮做DAO Factory嗎？

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao implements IOrderDao {
	@Autowired // SDI✔
	private SessionFactory factory;
//	private Session session;
	// dataArrays在每次執行selectAllOrder()或selectOrder()時都會先被重製，
	// 其他需要重製的時候需要手動重製。
	public static ArrayList<ArrayList<String>> dataArrays;
	public static final String columnNames[] = {"O_ID", "P_ID", "P_Name", "P_Price", "U_ID", "U_FirstName", "U_LastName",
			"U_Email", "O_Status", "O_Date", "O_Amt"};
	
	
	@Override
	public Order insert(Order order) {

		Session session = factory.getCurrentSession();
		// O_ID感覺沒辦法用在下述
		Order resultBean = session.get(Order.class, order.getO_id());
		if (resultBean == null) {
			session.save(order);
			return order;
		} else {
			System.out.println("Insertion failed.");
			return null;
//		session.getTransaction().commit();
		}
	}
	
	@Override
	public List<Order> selectAll() {
		Session session = factory.getCurrentSession();
		Query<Order> query = session.createQuery("FROM Order_Info", Order.class);
		return query.list();
	}
	
	@Override
	public Order select(String P_ID) {
		Session session = factory.getCurrentSession();
		// ‼ HQL不是用table名而是 @Entity ‼
		Query<Order> query = session.createQuery("SELECT * FROM Order WHERE p_id = :pid", Order.class);
		query.setParameter("pid", P_ID);
		return query.uniqueResult();
	}

	@Override
	public List<Order> selectCustom(String hql) {
		Session session = factory.getCurrentSession();
		Query<Order> query = session.createQuery(hql, Order.class);
		List<Order> resultList = query.getResultList();
		return resultList;
	}
	
	// Admin - 1
	public List<Order> selectTop20() {
		Session session = factory.getCurrentSession();
		Query<Order> query = session.createQuery("FROM Order ob ORDER BY ob.o_id ASC", Order.class).setMaxResults(20);
//		Order uniqueResult = query.uniqueResult();
		List<Order> resultList = query.getResultList();
		return resultList;
	}
	// Admin - 2
	public boolean update(Order newBean) {
		boolean updateStatus = false;
		Session session = factory.getCurrentSession();
		Order resultBean = session.get(Order.class, newBean.getO_id()); // 以PK查
		if (resultBean != null) {
//			resultBean.setO_id         (newBean.getO_id()       ); // 無意義  
			resultBean.setP_id         (newBean.getP_id()       );  
			resultBean.setP_name       (newBean.getP_name()     );  
			resultBean.setP_price      (newBean.getP_price()    );  
			resultBean.setU_id         (newBean.getU_id()       );  
			resultBean.setU_firstname  (newBean.getU_firstname());  
			resultBean.setU_lastname   (newBean.getU_lastname() );  
			resultBean.setU_email      (newBean.getU_email()    );  
			resultBean.setO_status     (newBean.getO_status()   );  
			resultBean.setO_date       (newBean.getO_date()     );  
			resultBean.setO_amt        (newBean.getO_amt()      );  
//			session.update(resultBean); // 不寫也會更新。...那這個方法還有存在意義嗎ˊ<_ˋ
		} else {
			System.out.println("*** Order with O_ID = " + newBean.getO_id() + "doesn't exist in the database :^) ***");
		}
		updateStatus = true;
		return updateStatus;
	}
	
	public boolean delete(Order orderBean) {
		Session session = factory.getCurrentSession();
		// 方法⓵ > 執行SELECT + DELETE
/**		Order resultBean = session.get(Order.class, orderBean.getO_id());
		if (resultBean != null) {
			session.delete(orderBean); 
		}*/
		// 方法⓶ > HQL
		Query query = session.createQuery("DELETE Order WHERE o_id = :oid");
		query.setParameter("oid", orderBean.getO_id());
		int deletedNum = query.executeUpdate();
		System.out.println("You deleted " + deletedNum + " row(s) from order_info table.");
		return (deletedNum == 0)? false : true;
	}

}