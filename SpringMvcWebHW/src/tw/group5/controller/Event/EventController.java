package tw.group5.controller.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.group5.model.event.EventService;
import tw.group5.model.event.Eventbean;


@Controller
public class EventController {
	
	@Autowired
	EventService service;



	
	// 讀取瀏覽器送出Ajax請求， 呼叫 service.saveMember(member) 新增會員資料
		@PostMapping(value="/Events", consumes = "application/json")
		public@ResponseBody Map<String, String>  save(@RequestBody Eventbean event) {
			Map<String, String> map = new HashMap<>();
			int n = 0;
			try {
				n = service.saveEvent(event);
				if (n == 1) {
					map.put("success", "新增成功");
				} else if (n == -1) {
					map.put("fail", "帳號重複");
				}
			} catch (Exception e) {
				map.put("fail", e.getMessage());
			}
			return map;
		}
		//查詢所有資料 送JSON 回去給瀏覽器 
		@GetMapping("/Events")
		public @ResponseBody List<Eventbean> queryAllMembers(Model model) {
			List<Eventbean> members = service.findAllEvents();
			return members;
		}
		//跳出更新頁面 把 KEY 丟到 MODLE裡 
		@GetMapping(value = "/EventsEdit/{key}", produces = { "application/json" })
		public String editMemberFindView(@PathVariable Integer key, Model model) {
			model.addAttribute("pk", key);
			return "Event/EventUpdate";
		}
		// 讀取並傳回單筆會員資料
		@GetMapping("/Events/{key}")
		public @ResponseBody Eventbean showEditMember(@PathVariable Integer key) {
			Eventbean event = service.findByPrimaryKey(key);
			return event;
		}
		
		// 依照鍵值刪除單筆會員資料
		@DeleteMapping("/Events/{key}")
		public @ResponseBody Map<String, String> deleteMember(@PathVariable(required = true) Integer key) {
			Map<String, String> map = new HashMap<>();
			try {
				service.deleteEventByPrimaryKey(key);
				map.put("success", "刪除成功");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("fail", "刪除失敗");
				System.out.println("刪除失敗");
			}
			return map;
		}
//		private void copyUnupdateField(Eventbean event0, Eventbean event) {
//			event.setExtra(event0.getExtra());
//		}//讓舊的BEAN 資料裡的 Extra 放去新的BEAN 裡的 Extra  自動新增的欄位才要
		
		// 修改單筆會員資料
		@PutMapping(value = "/Events/{key}", consumes = { "application/json" }, produces = { "application/json" })
		public @ResponseBody Map<String, String> updateMember(@RequestBody Eventbean event, @PathVariable Integer key) {
//			Eventbean member0 = null;
//			if (key != null) {
//				member0 = serviceUpdate.findByPrimaryKey(key);
//				if (member0 == null) {
//					throw new RuntimeException("鍵值不存在, key=" + key);
//				}
//				serviceUpdate.evictMember(member0);
//			session.evict(obj)，會把指定的緩衝物件進行清除
//			} else {
//				throw new RuntimeException("鍵值不存在, key=" + key);
//			}
//			copyUnupdateField(member0, member);
//			//如果資料庫 有前端沒有送過來的欄位資料 就會NULL 這個是避免那個情況

			Map<String, String> map = new HashMap<>();
			try {
				service.updateEvent(event);
				map.put("success", "更新成功");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("fail", "更新失敗");
			}
			return map;
		}
}
