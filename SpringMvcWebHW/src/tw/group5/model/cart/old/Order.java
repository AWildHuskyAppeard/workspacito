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
@Entity @Table(name = "order_info")
@Component
public class Order {
	@Id @Column(name = "O_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer o_id ; // PK
	@Column(name = "P_ID")
	private String p_id; // FK
	@Column(name = "P_NAME")
	private String p_name; // FK
	@Column(name = "P_PRICE")
	private Integer p_price; // FK
	@Column(name = "U_ID")
	private String u_id; // FK
	@Column(name = "U_FIRSTNAME")
	private String u_firstname; // FK
	@Column(name = "U_LASTNAME")
	private String u_lastname; // FK
	@Column(name = "U_EMAIL")
	private String u_email; // FK
	@Column(name = "O_STATUS")
	private String o_status;
	@Column(name = "O_DATE")
	private String o_date; // Date()會不會更好？
	@Column(name = "O_AMT")
	private Integer o_amt;
	
	// constructors
	public Order() {};
	public Order(Integer o_ID, String p_ID, String p_Name, Integer p_Price, String u_ID, String u_FirstName,
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
	public Integer getO_ID() {return o_id;}
	public String getP_ID() {return p_id;}
	public String getP_Name() {return p_name;}
	public Integer getP_Price() {return p_price;}
	public String getU_ID() {return u_id;}
	public String getU_FirstName() {return u_firstname;}
	public String getU_LastName() {return u_lastname;}
	public String getU_Email() {return u_email;}
	public String getO_Status() {return o_status;}
	public String getO_Date() {return o_date;}
	public Integer getO_Amt() {return o_amt;}
	
	// setters
	public void setO_ID(Integer o_ID) {	o_id = o_ID;}
	public void setP_ID(String p_ID) {p_id = p_ID;}
	public void setP_Name(String p_Name) {p_name = p_Name;}
	public void setP_Price(Integer p_Price) {p_price = p_Price;}
	public void setU_ID(String u_ID) {u_id = u_ID;}
	public void setU_FirstName(String u_FirstName) {u_firstname = u_FirstName;}
	public void setU_LastName(String u_LastName) {u_lastname = u_LastName;}
	public void setU_Email(String u_Email) {u_email = u_Email;}
	public void setO_Status(String o_Status) {o_status = o_Status;}
	public void setO_Date(String o_Date) {o_date = o_Date;}
	public void setO_Amt(Integer o_Amt) {o_amt = o_Amt;}
	
	// 為了配合for迴圈的懶人用方法之一...
	public String take(int SqlIndex) {
		String returnedString = null;
		switch (SqlIndex) {
		case 1:
			returnedString = String.valueOf(getO_ID());
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
			setO_ID(Integer.parseInt(value));
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