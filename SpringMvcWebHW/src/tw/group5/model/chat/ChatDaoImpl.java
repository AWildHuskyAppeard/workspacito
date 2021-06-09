package tw.group5.model.chat;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("chatDao")
@Transactional
public class ChatDaoImpl implements ChatDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertChat(Chat chat) {
		Session session = sessionFactory.getCurrentSession();
		if(chat!=null) {
			session.save(chat);
		}
		return false;
	}

	@Override
	public boolean deleteChat(Chat chat) {
		Session session = sessionFactory.getCurrentSession();
		if(chat!=null) {
			session.delete(chat);
		}
		return false;
	}

	@Override
	public boolean updateChat(Chat chat) {
		Session session = sessionFactory.getCurrentSession();
		if(chat!=null) {
			session.update(chat);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chat> findAllChat() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Chat";
		List<Chat> chat = session.createQuery(sql).getResultList();
		return chat;
	}

}
