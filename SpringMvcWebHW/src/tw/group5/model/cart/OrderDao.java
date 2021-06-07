package tw.group5.model.cart;
// 購物車的連線物件
// 要考慮做DAO Factory嗎？
import java.sql.*;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderDao implements IOrderDao {
	@Autowired // SDI✔
	private SessionFactory factory;
//	private Session session;
	// dataArrays在每次執行selectAllOrder()或selectOrder()時都會先被重製，
	// 其他需要重製的時候需要手動重製。
	public static ArrayList<ArrayList<String>> dataArrays;
	public static Order odBean01 = new Order(1, "p001", "EngSpeaking", 300, "elf01", "fl", "b", "w@w", "cancelled", "1999-12-01 00:00:00", 1 );
	public static final String columnNames[] = {"O_ID", "P_ID", "P_Name", "P_Price", "U_ID", "U_FirstName", "U_LastName",
			"U_Email", "O_Status", "O_Date", "O_Amt"};
	
	
	@Override
	public Order insert(Order order) {

		Session session = factory.getCurrentSession();
		// O_ID感覺沒辦法用在下述
		Order resultBean = session.get(Order.class, order.getO_id());
		if (resultBean == null) {
			session.save(resultBean);
			return resultBean;
		} else {
			System.out.println("Order exists already.");
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
		// HQL好像不是用table名而是entity名，所以有可能是OrderBean
		Query<Order> query = session.createQuery("SELECT * FROM Order_Info WHERE p_id = :pid", Order.class);
		query.setParameter("pid", P_ID);
		return query.uniqueResult();
	}

	@Override
	public Order selectCustom(String hql) {
		Session session = factory.getCurrentSession();
//		this.session.createQuery(hql, OrderBean.class);
		return null;
	}
	
	// 取得o_id最大的row
	public Order selectCustom2() {
		Session session = factory.getCurrentSession();
		Query<Order> query = session.createQuery("FROM OrderBean ob ORDER BY ob.O_ID DESC", Order.class).setMaxResults(1);
		Order uniqueResult = query.uniqueResult();
		return uniqueResult;
	}

}