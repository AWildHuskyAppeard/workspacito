package tw.group5.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import tw.group5.model.chat.ChatService;
import tw.group5.model.chat.ChatServiceImpl;

@Controller
public class ChatController {
	
	@Autowired
	ChatService chatService;
	@Autowired
	ChatServiceImpl chatServiceImpl;
	
	@GetMapping("/selectAllChat")
	public String selectAllChat(){
		return "chat/SelectAllChat";
	}
	
	@GetMapping("/insertChat")
	public String insertChat(){
		return "chat/InsertChat";
	}
	
	@GetMapping("/deleteChat")
	public String deleteChat(){
		return "chat/DeleteChat";
	}
	
	@GetMapping("/updateChat")
	public String updateChat(){
		return "chat/UpdateChat";
	}

}