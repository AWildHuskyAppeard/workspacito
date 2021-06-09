package tw.group5.model.product.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.group5.model.product.Product;
@Repository("testFilDao")
@Transactional
public class TestFileDao {

	
		@Autowired
		private SessionFactory sessionFactory;
		
		public Product insert(Product bean) {
			org.hibernate.Session session = sessionFactory.openSession();
			
			if(bean!=null) {
				session.save(bean);
			}
			return bean;
		}
	}
	

