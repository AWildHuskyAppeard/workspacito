package tw.group5.model.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	// 到會員的index
	@GetMapping(path = "/user/gotoUserIndex.controller")
	public String gotoUserIndex() {
		return "user/userIndex";
	}
	
	//到登入頁面
	@GetMapping(path = "/user/login.controller")
	public String gotoLoginPage() {
		return "user/login";
	}
	
	//到註冊頁面
	@GetMapping(path = "/user/signup.controller")
	public String gotoSignupPage() {
		return "user/signup";
	}
	
//	//查看全部會員資料
//	@GetMapping(path = "/user/showAllUser.controller")
//	@ResponseBody
//	public User_Info gotoFindAllUserPage() {
//		return ;
//	}
	
//	//修改會員資料
//	@PutMapping(path = "/user/updateUserinfo.controller")
//	public User_Info updateUser() {
//		return ;
//	}
	
	
	
	
}
