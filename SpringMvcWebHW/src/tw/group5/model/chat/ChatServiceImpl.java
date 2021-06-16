package tw.group5.model.chat;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service
@Transactional
@EnableTransactionManagement
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	ChatDao chatDao;

	@Override
	public void insertChat(Chat chat) {
		chatDao.insertChat(chat);
	}

	@Override
	public void deleteChat(int c_ID) {
		chatDao.deleteChat(c_ID);
	}

	@Override
	public void updateChat(Chat chat) {
		chatDao.updateChat(chat);
	}

	@Override
	public List<Chat> findAllChat() {
		return chatDao.findAllChat();
	}

	@Override
	public Chat selectChatById(int c_ID) {
		return chatDao.selectChatById(c_ID);
	}

}
