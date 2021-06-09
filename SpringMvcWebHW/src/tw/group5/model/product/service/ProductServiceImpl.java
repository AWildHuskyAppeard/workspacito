package tw.group5.model.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tw.group5.model.product.ProductInfo;
import tw.group5.model.product.dao.ProductDaoImpl;

@Service
@Transactional
@EnableTransactionManagement(proxyTargetClass = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDaoImpl dao;

	@Override
	public List<ProductInfo> findAll() {
		return dao.findAll();
	}

	@Override
	public List<ProductInfo> findByProductName(String p_Name) {
		return dao.findByProductName(p_Name);
	}

	@Override
	public ProductInfo findByProductID(Integer p_ID) {
		return dao.findByProductID(p_ID);
	}

	@Override
	public boolean saveProduct(ProductInfo productInfo) {
		return dao.saveProduct(productInfo);
	}

	@Override
	public boolean deleteProduct(Integer p_ID) {
		return dao.deleteProduct(p_ID);
	}

	@Override
	public boolean isProductExist(ProductInfo productInfo) {
		return dao.isProductExist(productInfo);
	}

}
