package tw.group5.model.chat;

import java.util.ArrayList;

import javax.transaction.Transactional;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteChat(Chat chat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateChat(Chat chat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Chat> findAllChat() {
		// TODO Auto-generated method stub
		return null;
	}

}
