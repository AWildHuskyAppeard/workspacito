package tw.group5.model.chat;

import java.util.ArrayList;

public interface ChatDao {
	
	boolean insertChat(Chat chat);
	
    boolean deleteChat(Chat chat);
	
	boolean updateChat(Chat chat);
	
	ArrayList<Chat> findAllChat();

}
