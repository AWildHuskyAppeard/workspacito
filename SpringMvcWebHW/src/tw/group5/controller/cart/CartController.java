package tw.group5.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CartController {
	
	@GetMapping(value = "/omegalul.controller")
	@ResponseBody
	public String meaninglessMethod() {
		return "Here's your OMEGALUL";
	}
}