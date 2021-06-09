package tw.group5.controller.Event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventJumpController {
	// 到首頁
	@GetMapping("/Event")
	public String index() {
		return "Event/EventIndex";
	}

	// 到新增頁面
	@GetMapping("/insertEventForm")
	public String registerFindView() {
		return "Event/EventInsert";
	}

	// 送出顯示所有Member紀錄的表單
	@GetMapping({ "/showAllEvent" })

	public String showAllMembersFindView() {
		return "Event/EventshowAll";
	}

    //回大家首頁
	@GetMapping({ "/gohome" })
	public String gohome() {
		return "tempIndex";
	}

}
