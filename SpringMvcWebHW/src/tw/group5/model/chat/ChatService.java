package tw.group5.model.chat;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ChatService {

	public void insertChat(Chat chat);
	
    public void deleteChat(int c_ID);
	
    public void updateChat(Chat chat);
	
	public List<Chat> findAllChat();
	
}
