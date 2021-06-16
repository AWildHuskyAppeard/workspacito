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
	SessionFactory sessionFactory;
	
	@Autowired
	Chat chat;

	@Override
	public void insertChat(Chat chat) {
		Session session = sessionFactory.getCurrentSession();
		if(chat!=null) {
			session.save(chat);
		}
	}

	@Override
	public void deleteChat(int c_ID) {
		Session session = sessionFactory.getCurrentSession();
		chat.setC_ID(c_ID);
		session.delete(chat);
	}

	@Override
	public void updateChat(Chat chat) {
		Session session = sessionFactory.getCurrentSession();
		session.update(String.valueOf(chat.getC_ID()), chat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chat> findAllChat() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Chat";
		List<Chat> chat = session.createQuery(sql).getResultList();
		return chat;
	}

	@Override
	public Chat selectChatById(int c_ID) {
		Session session = sessionFactory.getCurrentSession();
		Chat chat = session.get(Chat.class, c_ID);
		return chat;
	}


}
