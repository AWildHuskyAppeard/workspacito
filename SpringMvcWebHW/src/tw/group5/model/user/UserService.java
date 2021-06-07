package tw.group5.model.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service
@Transactional
@EnableTransactionManagement
public class UserService implements IUserService {
	@Autowired
	IUserDao iUserDao;

	@Override
	//// 檢查帳號是否可用
	public String checkUserId(String u_id) {
		return iUserDao.checkUserId(u_id);
	}

	@Override
	// 會員註冊
	public int saveUser(User_Info user_Info) {
		int n = iUserDao.saveUser(user_Info);
		return n;
	}

	@Override
	// 檢查u_id是否存在
	public boolean isUserExist(User_Info user_Info) {
		return iUserDao.isUserExist(user_Info);
	}

	@Override
	// 登入
	public User_Info login(User_Info user_Info) {
		return iUserDao.login(user_Info);
	}
	
	@Override
	// 查看全部會員資料
	public List<User_Info> showAllUsers(){
		return iUserDao.showAllUsers();
	}

}
