package tw.group5.controller.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.group5.model.chat.Chat;
import tw.group5.model.chat.ChatService;
import tw.group5.model.chat.ChatServiceImpl;

@Controller
public class ChatController {
	
	@Autowired
	ChatService chatService;
	@Autowired
	ChatServiceImpl chatServiceImpl;
	
	@GetMapping(path = "/chatIndex")
	public String gotoUserIndex() {
		return "chat/chatIndex";
	}
	
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
	
	@GetMapping(path = "/selectAllChat", produces = {"application/json"})
	@ResponseBody
	public List<Chat> goSelectAllChat() {
		List<Chat> chat = chatService.findAllChat();
		return chat;
	}
	
	@PostMapping(path = "/insertChat", produces = {"application/json"})
	@ResponseBody
	public Map<String, String> InsertChat(@RequestBody Chat chat){
		Map<String, String> map = new HashMap<>();
		try {
			chatService.insertChat(chat);
			map.put("success", "新增成功");
		} catch (Exception e) {
			map.put("fail", "新增失敗");
			e.printStackTrace();
		}
		return map;
	}
	
	@PutMapping(path = "/updateChat/{c_ID}", consumes = { "application/json" }, produces = {"application/json" })
	@ResponseBody
	public Map<String, String> updateChat(@RequestBody Chat chat, @PathVariable String id) {
		Map<String, String> map = new HashMap<>();
		try {
			chatService.insertChat(chat);
			map.put("success", "新增成功");
		} catch (Exception e) {
			map.put("fail", "新增失敗");
			e.printStackTrace();
		}
		return map;
	}

}