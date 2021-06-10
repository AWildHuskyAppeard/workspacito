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
	ChatService chatService;

	@Override
	public void insertChat(Chat chat) {
		chatService.insertChat(chat);
	}

	@Override
	public void deleteChat(int c_ID) {
		chatService.deleteChat(c_ID);
	}

	@Override
	public void updateChat(Chat chat) {
		chatService.updateChat(chat);
	}

	@Override
	public List<Chat> findAllChat() {
		return chatService.findAllChat();
	}

}
