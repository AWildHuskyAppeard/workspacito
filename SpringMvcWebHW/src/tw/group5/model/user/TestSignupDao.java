package tw.group5.model.user;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("testsignupDao")
@Transactional
public class TestSignupDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public User_Info insert(User_Info user_Info) {
		Session session = sessionFactory.getCurrentSession();
		
		if(user_Info!=null) {
			session.save(user_Info);			
		}
		return user_Info;
	}
	
}
