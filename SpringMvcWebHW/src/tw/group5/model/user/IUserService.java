package tw.group5.model.user;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserService {

	// 檢查帳號是否可用
	public String checkUserId(String u_id);

	// 會員註冊
	public int saveUser(User_Info user_Info);

	// 檢查u_id是否存在
	public boolean isUserExist(User_Info user_Info);

	// 登入
	public User_Info login(User_Info user_Info);

	// 查看全部會員資料
	public List<User_Info> showAllUsers();

}
