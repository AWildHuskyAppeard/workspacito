package tw.group5.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.group5.model.user.IUserService;
import tw.group5.model.user.UserDao;
import tw.group5.model.user.User_Info;

@Controller
@EnableTransactionManagement
@SessionAttributes(names = {"loginBean"})
public class UserController {
	@Autowired
	IUserService iUserService;
	@Autowired
	User_Info user_info;
//	@Autowired
//	UserDao userDao;

	// 到會員的index
	@GetMapping(path = "/gotoUserIndex.controller")
	public String gotoUserIndex() {
		return "user/userIndex";
	}
	
	//到登入頁面
	@GetMapping(path = "/gotologin.controller")
	public String gotoLoginPage() {
		return "user/login";
	}
	
	//到註冊頁面
	@GetMapping(path = "/gotosignup.controller")
	public String gotoSignupPage() {
		return "user/signup";
	}
	
	//到查看全部會員資料頁面
	@GetMapping(path="/gotoShowAllUser.controller")
	public String gotoShowAllUser() {
		return "user/showAllUser";
	}
	
//	//到修改會員資料頁面
//	@GetMapping(path = "/gotoUpdateUserinfo.controller")
//	public String gotoUpdateUser() {
//		return "";
//	}
	
	
	
	//登入
	@PostMapping(path = "/login.controller", produces = {"application/json"})
	@ResponseBody
	public Map<String, Object> login(@RequestBody User_Info user_Info, Model model){
		Map<String, Object> map = new HashMap<>();
		user_info = null;
		try {
			user_info = iUserService.login(user_Info);
			if(user_info != null && user_info.getU_id().length()>0) {
				map.put("success", "登入成功");
//				map.put("u_id", user_info.getU_id());
				map.put("loginBean", user_info);
				model.addAttribute("loginBean", user_info);
			} else if(user_info == null) {
				map.put("fail", "帳號或密碼錯誤，請再試一次...");
			}
		} catch (Exception e) {
			System.out.println("********************************");
			System.out.println("有exception，在\'登入controller\'");
			System.out.println("********************************");
			map.put("fail", e.getMessage());
		}
		return map;
	}
	
	//檢查帳號是否可用
	@PostMapping(path = "/checkUserId", produces = {"application/json"})
	@ResponseBody
	public Map<String, String> checkUserId(@RequestParam String u_id){
		Map<String, String> map = new HashMap<>();
		String user_id = iUserService.checkUserId(u_id);
		map.put("u_id", user_id);
		return map;
	}
	
	//會員註冊
	@PostMapping(path = "/userSignup", produces = {"application/json"})
	@ResponseBody
	public Map<String, String> signup(@RequestBody User_Info user_Info){
		Map<String, String> map = new HashMap<>();
		int n = 0;
		try {
			n = iUserService.saveUser(user_Info);
			if(n == 1) {
				map.put("success", "註冊成功");
			}else if(n == -1) {
				map.put("fail", "帳號重複");
			}
		} catch (Exception e) {
			System.out.println("********************************");
			System.out.println("有exception，在\'會員註冊controller\'");
			System.out.println("********************************");
			map.put("fail", e.getMessage());
		}
		return map;
	}
	
	//查看全部會員資料
	@GetMapping(path = "/showAllUser.controller", produces = {"application/json"})
	@ResponseBody
	public List<User_Info> gotoFindAllUserPage() {
		List<User_Info> users = iUserService.showAllUsers();
		return users;
	}
	
//	//修改會員資料
//	@PutMapping(path = "/updateUserinfo.controller")
//	public User_Info updateUser() {
//		return ;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
