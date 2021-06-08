package tw.group5.model.product;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ProductInfo")
@Component("ProductInfo")
public class ProductInfo {
	@Id
	@Column(name = "p_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer p_ID;
	@Column(name = "p_Name")
	private String p_Name;
	@Column(name = "p_Class")
	private String p_Class;
	@Column(name = "p_Price")
	private int p_Price;
	@Column(name = "p_DESC")
	private String p_DESC;
	@Column(name = "u_ID")
	private String u_ID;
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ProductInfo")
//	private Product product;
	@Column(name = "p_createDate")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Taipei")
	private String p_createDate;
	@Column(name = "p_Img")
	private byte[] p_Img;
	@Column(name = "p_Video")
	private byte[] p_Video;

//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}
	
	public ProductInfo(byte[] p_Img, byte[] p_Video) {
		super();
		this.p_Img = p_Img;
		this.p_Video = p_Video;
	}
	

	public Integer getP_ID() {
		return p_ID;
	}


	public void setP_ID(Integer p_ID) {
		this.p_ID = p_ID;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public String getP_Class() {
		return p_Class;
	}

	public void setP_Class(String p_Class) {
		this.p_Class = p_Class;
	}

	public int getP_Price() {
		return p_Price;
	}

	public void setP_Price(int p_Price) {
		this.p_Price = p_Price;
	}

	public String getP_DESC() {
		return p_DESC;
	}

	public void setP_DESC(String p_DESC) {
		this.p_DESC = p_DESC;
	}

	public String getU_ID() {
		return u_ID;
	}

	public void setU_ID(String u_ID) {
		this.u_ID = u_ID;
	}

	public String getP_createDate() {
		return p_createDate;
	}

	public void setP_createDate(String p_createDate) {
		this.p_createDate = p_createDate;
	}

	public byte[] getP_Img() {
		return p_Img;
	}

	public void setP_Img(byte[] p_Img) {
		this.p_Img = p_Img;
	}

	public byte[] getP_Video() {
		return p_Video;
	}

	public void setP_Video(byte[] bs) {
		this.p_Video = bs;
	}

	@Override
	public String toString() {
		return "ProductInfo [p_ID=" + p_ID + ", p_Name=" + p_Name + ", p_Class=" + p_Class + ", p_Price=" + p_Price
				+ ", p_DESC=" + p_DESC + ", u_ID=" + u_ID + ", p_createDate=" + p_createDate + ", p_Img=" + p_Img
				+ ", p_Video=" + p_Video + "]";
	}

}
