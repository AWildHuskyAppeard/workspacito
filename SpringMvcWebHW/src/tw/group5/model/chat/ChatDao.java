package tw.group5.model.chat;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("chatDao")
@Transactional
public class ChatDao {
	@Autowired
	private SessionFactory sessionFactory;

}
