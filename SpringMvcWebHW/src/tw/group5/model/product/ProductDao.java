package tw.group5.model.product;

import java.util.ArrayList;
import java.util.List;

public interface ProductDao {
	
	//findall
	List<ProductInfo>findAll();
	//find by product name
	List<ProductInfo> findByProductName(String p_Name);
	//findbyp_id
	ProductInfo findByProductID(Integer p_ID);
	//insert product
	boolean saveProduct(ProductInfo productInfo);
	//delete product
	boolean deleteProduct(Integer p_ID);
	//check if product is exist
	public boolean isProductExist(ProductInfo productInfo);
		
}
