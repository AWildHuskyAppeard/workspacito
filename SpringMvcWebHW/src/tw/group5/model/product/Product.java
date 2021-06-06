package tw.group5.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

//@Entity
//@Table(name = "Product")
//@Component("Product")
public class Product {
	@Id
	@Column(name = "p_ID")
	@GenericGenerator(name = "generator",strategy = "foreign",parameters = @Parameter(name="property", value = "ProductInfo"))
	@GeneratedValue(generator = "generator")
	private Integer p_ID;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private ProductInfo productInfo;
	@Column(name = "p_Img")
	private String p_Img;
	@Column(name = "p_Video")
	private String p_Video;

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public String getP_Img() {
		return p_Img;
	}

	public void setP_Img(String p_Img) {
		this.p_Img = p_Img;
	}

	public String getP_Video() {
		return p_Video;
	}

	public void setP_Video(String p_Video) {
		this.p_Video = p_Video;
	}

	public Integer getP_ID() {
		return p_ID;
	}

	public void setP_ID(Integer p_ID) {
		this.p_ID = p_ID;
	}

	@Override
	public String toString() {
		return "Product [p_ID=" + p_ID + ", productInfo=" + productInfo + ", p_Img=" + p_Img + ", p_Video=" + p_Video
				+ "]";
	}

}
