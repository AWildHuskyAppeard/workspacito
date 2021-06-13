package tw.group5.model.question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuesDAOImpl implements QuesDAO {
	
	@Autowired
	SessionFactory factory;

	
//// 新增
	//servlet boolean => int
	@Override
	public int insertQues(QuesBean quesBean) {
		int n = 0;
		boolean exist = isQuestionExist(quesBean);
		if(exist) {
			return -1;
		}
		Session session = factory.getCurrentSession();
		try {
			session.save(quesBean);
			n = 1;			
		} catch (Exception e) {
			n = -2;
		}
		return n;
	}
	//確認ID是否有重複
	@Override
	public boolean isQuestionExist(QuesBean quesBean) {
		boolean exist = false;
		Session Session = factory.getCurrentSession();	
		//hql使用參數前加冒號:
		String hql = "FROM QuesBean q WHERE q.q_ID=:q_ID ";
		try {
			Session.createQuery(hql).setParameter("q_ID", quesBean.getQ_ID()).getSingleResult();
			exist = true;
		} catch (NoResultException ex) {
		//?? 因為ID存在所以catch起來,什麼都不做 . 送回的 exist就應該還是 = false ?
			;
		}
		return exist;
	}
	
	


	
//// 刪除
	@Override
	public void deleteQues(int q_ID) {
		Session session = factory.getCurrentSession();
		QuesBean quesBean = new QuesBean();
		quesBean.setQ_ID(q_ID);
		session.delete(quesBean);

	
	}

//// 修改
	@Override
	public void updateQues(QuesBean quesBean) {
		Session session = factory.getCurrentSession();
		session.update(quesBean);

	}
			

		
//// 查詢所有資料
	//	 ?? 告訴編譯器忽略 unchecked 警告資訊,如使用List,ArrayList等未進行引數化產生的警告資訊。
	@SuppressWarnings("unchecked")
	@Override
	public List<QuesBean> findAllQuesBean() {
		Session session = factory.getCurrentSession();
		String hql = "FROM QuesBean";
		List<QuesBean> list = session.createQuery(hql).getResultList();
		return list;
	}
	
	//從session快取中清除物件
	@Override
	public void evictQues(QuesBean quesBean) {
		Session session = factory.getCurrentSession();
		session.evict(quesBean);
		
	}
	
	
	
	
//// 搜尋單筆
	@Override
	public QuesBean findByPrimaryKey(int q_ID) {
		Session session = factory.getCurrentSession();
		QuesBean quesBean = session.get(QuesBean.class , q_ID);
		return quesBean;

	}

	
	
	
		
		
	//測驗區先不使用檢驗編號重複功能 , 問題點:q_ID非同老師為字串 , 但回傳為String
//	@Override
//	public String checkQuestionId(String q_ID) {
//	Session session = factory.getCurrentSession();
//	String hql = "FROM QuesBean q WHERE q.q_ID=:q_ID";
//	String q_ID="0" ;
//	try {
//		QuesBean m = (QuesBean) session.createQuery(hql).setParameter("q_ID", q_ID).getSingleResult();
//		q_ID = m.getQ_ID();
//	} catch (NoResultException ex) {
//		;
//	} catch (Exception ex) {
//		ex.printStackTrace();
//		System.out.println(ex.getMessage());
//		q_ID = "Error: 資料庫異常，請檢查資料庫";
//	}
//	return q_ID;
//}



}