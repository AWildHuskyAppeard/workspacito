package tw.group5.model.cart.test;
// Just borrowed for test. Shall be deleted later.
import java.util.Date;

public class UserBean {
	
	private String u_ID;
	private String u_Psw;
	private String u_BirthDay;
	private String u_LastName;
	private String u_FirstName;
	private String u_Email;
	private String u_Tel;
	private String u_Sex;
	private String u_Address;
	
	public UserBean(){
		
	}

	public UserBean(String u_ID, String u_Psw, String u_BirthDay, String u_LastName, String u_FirstName, String u_Email,
			String u_Tel, String u_Sex, String u_Address) {
		super();
		this.u_ID = u_ID;
		this.u_Psw = u_Psw;
		this.u_BirthDay = u_BirthDay;
		this.u_LastName = u_LastName;
		this.u_FirstName = u_FirstName;
		this.u_Email = u_Email;
		this.u_Tel = u_Tel;
		this.u_Sex = u_Sex;
		this.u_Address = u_Address;
	}


	
	public String getU_ID() {
		return u_ID;
	}
	public void setU_ID(String u_ID) {
		this.u_ID = u_ID;
	}
	public String getU_Psw() {
		return u_Psw;
	}
	public void setU_Psw(String u_Psw) {
		this.u_Psw = u_Psw;
	}
	public String getU_BirthDay() {
		return u_BirthDay;
	}
	public void setU_BirthDay(String u_BirthDay) {
		this.u_BirthDay = u_BirthDay;
	}
	public String getU_LastName() {
		return u_LastName;
	}
	public void setU_LastName(String u_LastName) {
		this.u_LastName = u_LastName;
	}
	public String getU_FirstName() {
		return u_FirstName;
	}
	public void setU_FirstName(String u_FirstName) {
		this.u_FirstName = u_FirstName;
	}
	public String getU_Email() {
		return u_Email;
	}
	public void setU_Email(String u_Email) {
		this.u_Email = u_Email;
	}
	public String getU_Tel() {
		return u_Tel;
	}
	public void setU_Tel(String u_Tel) {
		this.u_Tel = u_Tel;
	}
	public String getU_Sex() {
		return u_Sex;
	}
	public void setU_Sex(String u_Sex) {
		this.u_Sex = u_Sex;
	}
	public String getU_Address() {
		return u_Address;
	}
	public void setU_Address(String u_Address) {
		this.u_Address = u_Address;
	}
	
}
