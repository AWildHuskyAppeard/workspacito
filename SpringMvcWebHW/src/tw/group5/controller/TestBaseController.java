package tw.group5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestBaseController {
	
	@GetMapping(path = "/")
	public String gotoUserIndex() {
		return "tempIndex";
	}
	
	
	
}
