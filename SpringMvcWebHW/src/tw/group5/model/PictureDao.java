package tw.group5.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("pictureDao")
@Transactional
public class PictureDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Picture insert(Picture bean) {
		Session session = sessionFactory.getCurrentSession();
		
		if(bean!=null) {
			session.save(bean);
		}
		return bean;
	}

}
