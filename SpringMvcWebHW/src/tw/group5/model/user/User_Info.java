package tw.group5.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "user_info")
@Component("user_info")
public class User_Info {

	@Id
	@Column(name = "u_id")
	private String u_id;

	private String u_psw;
	private String u_lastname;
	private String u_firstname;
	private String u_birthday;
	private String u_email;
	private String u_tel;
	private String u_gender;
	private String u_address;
	private byte[] u_img;

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_psw() {
		return u_psw;
	}

	public void setU_psw(String u_psw) {
		this.u_psw = u_psw;
	}

	public String getU_lastname() {
		return u_lastname;
	}

	public void setU_lastname(String u_lastname) {
		this.u_lastname = u_lastname;
	}

	public String getU_firstname() {
		return u_firstname;
	}

	public void setU_firstname(String u_firstname) {
		this.u_firstname = u_firstname;
	}

	public String getU_birthday() {
		return u_birthday;
	}

	public void setU_birthday(String u_birthday) {
		this.u_birthday = u_birthday;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_tel() {
		return u_tel;
	}

	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}

	public String getU_gender() {
		return u_gender;
	}

	public void setU_gender(String u_gender) {
		this.u_gender = u_gender;
	}

	public String getU_address() {
		return u_address;
	}

	public void setU_address(String u_address) {
		this.u_address = u_address;
	}

	public byte[] getU_img() {
		return u_img;
	}

	public void setU_img(byte[] u_img) {
		this.u_img = u_img;
	}

}
