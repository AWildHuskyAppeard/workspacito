package tw.group5.model.product;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInfo> findAll() {

		Session session = sessionFactory.getCurrentSession();
		String hql = "from ProductInfo";
		List<ProductInfo> list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public ProductInfo findByProductID(Integer p_ID) {
		Session session = sessionFactory.getCurrentSession();
		ProductInfo productInfo = session.get(ProductInfo.class, p_ID);
		return productInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInfo> findByProductName(String p_Name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ProductInfo where p_name like '%"+p_Name+"%'";
		List<ProductInfo> list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public boolean saveProduct(ProductInfo productInfo) {
		boolean exist = isProductExist(productInfo);
		Session session = sessionFactory.getCurrentSession();
		if (exist) {
			session.save(productInfo);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean deleteProduct(Integer p_ID) {

		Session session = sessionFactory.getCurrentSession();
		ProductInfo productInfo = findByProductID(p_ID);
		boolean exist = isProductExist(productInfo);
		if (exist) {
			session.delete(productInfo);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isProductExist(ProductInfo productInfo) {

		boolean exist = false;
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ProductInfo p where p.p_ID = :p_ID";
		try {
			session.createQuery(hql).setParameter("p_ID", productInfo.getP_ID()).getSingleResult();
			
		} catch (NoResultException e) {
			
		}
		exist = true;
		return exist;
	}

}
