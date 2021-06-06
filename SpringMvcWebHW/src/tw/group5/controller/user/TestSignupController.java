package tw.group5.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.group5.model.user.TestSignupDao;
import tw.group5.model.user.User_Info;

@Controller
public class TestSignupController {
	
	@GetMapping(path = "/signup")
	public String gotoSignup() {
		return "user/signup";
	}
	
	@Autowired
	private TestSignupDao testSignupDao;
	@Autowired
	private User_Info user_Info;
	@PostMapping(path = "/savesignup.controller")
	@ResponseBody
	public User_Info processSaveSignup(@RequestParam String u_id,
										@RequestParam String u_psw,
										@RequestParam String u_lastname,
										@RequestParam String u_firstname,
										@RequestParam String u_email) {
		user_Info.setU_id(u_id);
		user_Info.setU_psw(u_psw);
		user_Info.setU_lastname(u_lastname);
		user_Info.setU_firstname(u_firstname);
		user_Info.setU_email(u_email);
		User_Info resultBean = testSignupDao.insert(user_Info);
		
		return resultBean;
	}
	
}
