package tw.group5.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tw.group5.model.cart.Order;
import tw.group5.model.product.ProductInfo;

@Entity
@Table(name = "user_info")
@Component("user_info")
public class User_Info {

	@Id	@Column(name = "U_ID")
	private String u_id;
	@Column(name = "U_PSW")	
	private String u_psw;
	@Column(name = "U_LASTNAME")	
	private String u_lastname;
	@Column(name = "U_FIRSTNAME")	
	private String u_firstname;
	@Column(name = "U_BIRTHDAY")	
	private String u_birthday;
	@Column(name = "U_EMAIL")	
	private String u_email;
	@Column(name = "U_TEL")	
	private String u_tel;
	@Column(name = "U_GENDER")	
	private String u_gender;
	@Column(name = "U_ADDRESS")	
	private String u_address;
	@Column(name = "U_IMG")	
	private byte[] u_img;
	
	/*********************************************************************/
	// 被Order參考
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "user_Info")
	private Set<Order> order = new HashSet<Order>();
	public Set<Order> getOrder() {		return order;	}
	public void setOrder(Set<Order> order) {		this.order = order;	}
	// 被ProductInfo參考
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "user_Info")
	private Set<ProductInfo> productInfo = new HashSet<ProductInfo>();
	public Set<ProductInfo> getProductInfo() {		return productInfo;	}
	public void setProductInfo(Set<ProductInfo> productInfo) {		this.productInfo = productInfo;	}
	/*********************************************************************/

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
