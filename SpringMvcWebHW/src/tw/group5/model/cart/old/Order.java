package tw.group5.model.cart.old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

// Cart = ArrayList<ProductBean> = ArrayList<CartItem>
// OrderBean = cart +- 一些額外資訊
@Entity @Table(name = "Order_Info")
//@Component
public class Order {
	@Id @Column(name = "O_ID")
	private String O_ID ; // PK
	@Column(name = "P_ID")
	private String P_ID; // FK
	@Column(name = "P_NAME")
	private String P_Name; // FK
	@Column(name = "P_PRICE")
	private Integer P_Price; // FK
	@Column(name = "U_ID")
	private String U_ID; // FK
	@Column(name = "U_FIRSTNAME")
	private String U_FirstName; // FK
	@Column(name = "U_LASTNAME")
	private String U_LastName; // FK
	@Column(name = "U_EMAIL")
	private String U_Email; // FK
	@Column(name = "O_STATUS")
	private String O_Status;
	@Column(name = "O_DATE")
	private String O_Date; // Date()會不會更好？
	@Column(name = "O_AMT")
	private Integer O_Amt;
	
	// constructors
	public Order() {};
	public Order(String o_ID, String p_ID, String p_Name, Integer p_Price, String u_ID, String u_FirstName,
			String u_LastName, String u_Email, String o_Status, String o_Date, Integer o_Amt) {
		setO_ID         (o_ID       );
		setP_ID         (p_ID       );
		setP_Name       (p_Name     );
		setP_Price      (p_Price    );
		setU_ID         (u_ID       );
		setU_FirstName  (u_FirstName);
		setU_LastName   (u_LastName );
		setU_Email      (u_Email    );
		setO_Status     (o_Status   );
		setO_Date       (o_Date     );
		setO_Amt        (o_Amt      );
	}                    
	
	// getters
	public String getO_ID() {return O_ID;}
	public String getP_ID() {return P_ID;}
	public String getP_Name() {return P_Name;}
	public Integer getP_Price() {return P_Price;}
	public String getU_ID() {return U_ID;}
	public String getU_FirstName() {return U_FirstName;}
	public String getU_LastName() {return U_LastName;}
	public String getU_Email() {return U_Email;}
	public String getO_Status() {return O_Status;}
	public String getO_Date() {return O_Date;}
	public Integer getO_Amt() {return O_Amt;}
	
	// setters
	public void setO_ID(String o_ID) {	O_ID = o_ID;}
	public void setP_ID(String p_ID) {P_ID = p_ID;}
	public void setP_Name(String p_Name) {P_Name = p_Name;}
	public void setP_Price(Integer p_Price) {P_Price = p_Price;}
	public void setU_ID(String u_ID) {U_ID = u_ID;}
	public void setU_FirstName(String u_FirstName) {U_FirstName = u_FirstName;}
	public void setU_LastName(String u_LastName) {U_LastName = u_LastName;}
	public void setU_Email(String u_Email) {U_Email = u_Email;}
	public void setO_Status(String o_Status) {O_Status = o_Status;}
	public void setO_Date(String o_Date) {O_Date = o_Date;}
	public void setO_Amt(Integer o_Amt) {O_Amt = o_Amt;}
	
	// 為了配合for迴圈的懶人用方法之一...
	public String take(int SQLindex) {
		String returnedString = null;
		switch (SQLindex) {
		case 1:
			returnedString = getO_ID();
			break;
		case 2:
			returnedString = getP_ID();
			break;
		case 3:
			returnedString = getP_Name();
			break;
		case 4:
			returnedString = String.valueOf(getP_Price());
			break;
		case 5:
			returnedString = getU_ID();
			break;
		case 6:
			returnedString = getU_FirstName();
			break;
		case 7:
			returnedString = getU_LastName();
			break;
		case 8:
			returnedString = getU_Email();
			break;
		case 9:
			returnedString = getO_Status();
			break;
		case 10:
			returnedString = getO_Date();
			break;
		case 11:
			returnedString = String.valueOf(getO_Amt());
			break;

		default:
			break;
		}
		return returnedString;
	}
	
	// 為了配合for迴圈的懶人用方法之二...
	public void assign(int SQLindex, String value) {
		switch (SQLindex) {
		case 1:
			setO_ID(value);
			break;
		case 2:
			setP_ID(value);
			break;
		case 3:
			setP_Name(value);
			break;
		case 4:
			setP_Price(Integer.parseInt(value));
			break;
		case 5:
			setU_ID(value);
			break;
		case 6:
			setU_FirstName(value);
			break;
		case 7:
			setU_LastName(value);
			break;
		case 8:
			setU_Email(value);
			break;
		case 9:
			setO_Status(value);
			break;
		case 10:
			setO_Date(value);
			break;
		case 11:
			setO_Amt(Integer.parseInt(value));
			break;

		default:
			break;
		}
	}
	
}