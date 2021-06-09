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

/* servlet	
	private Connection conn;

	// 建構子
	public QuesDAOImpl(Connection conn) {
		this.conn = conn;
	} */


	//新增
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
//		hql使用參數前加冒號:
		String hql = "FROM QuesBean q WHERE q.q_ID=:q_ID ";
		try {
			Session.createQuery(hql).setParameter("q_ID", quesBean.getQ_ID()).getSingleResult();
			exist = true;
		} catch (NoResultException ex) {
//??
			;
		}
		return exist;
	}
	
	
		
		
/* servlet	新增
		 	boolean isInsert = false;		
			String sqlString = "INSERT INTO Questions(Q_ID,Q_Type,Q_Ques,Q_Selection,Q_Ans,P_Class) " + "VALUES("
					+ QuesData.getQ_ID() + ",'" + QuesData.getQ_Type() + "', N'" + QuesData.getQ_Ques() + "',N'"
					+ QuesData.getQ_Selection() + "',N'" + QuesData.getQ_Ans() + "','" + QuesData.getP_Class() + "')";

			Statement stmt = conn.createStatement();
			System.out.println(sqlString);

			int i = stmt.executeUpdate(sqlString);
			if (i > 0) {
				isInsert = true;
			}
			stmt.close();
		} catch (Exception e) {
			System.err.println("建立資料時發生錯誤:" + e);
			isInsert = false;
		}
			return isInsert;
	
*/


	
	// 刪除
	@Override
	public void deleteQues(int q_ID) {
		Session session = factory.getCurrentSession();
		QuesBean quesBean = new QuesBean();
		quesBean.setQ_ID(q_ID);
		session.delete(quesBean);

		/*servlet
		try {
			String sqlString = "DELETE FROM Questions " + "WHERE Q_ID = " + Q_ID;
			Statement stmt = conn.createStatement();
			int deletecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (deletecount >= 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.err.println("刪除部門時發生錯誤: " + e);
			return false;
		} */
	}

	// 修改
	@Override
	public void updateQues(QuesBean quesBean) {
		Session session = factory.getCurrentSession();
		session.update(quesBean);

	}
	

		
/* servlet		
		try {
			String sqlString = "UPDATE Questions " + "SET Q_ID =" + QuesData.getQ_ID() + ", Q_Type= '"
					+ QuesData.getQ_Type() + "' , Q_Ques=N'" + QuesData.getQ_Ques() + "', Q_Selection =N'"
					+ QuesData.getQ_Selection() + "', Q_Ans=N'" + QuesData.getQ_Ans() + "', P_Class='"
					+ QuesData.getP_Class() + "' WHERE Q_ID=" + QuesData.getQ_ID();
			System.out.println(sqlString);

			Statement stmt = conn.createStatement();
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1) {
				return true;
			}

			else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("更新資料時發生錯誤:" + e);
			return false;
		}
	}
*/
		
	// 搜尋
	@Override
	public QuesBean findByPrimaryKey(int q_ID) {
		Session session = factory.getCurrentSession();
		QuesBean quesBean = session.get(QuesBean.class , q_ID);
		return quesBean;

		
/* servlet		
		@Override
		public Member findByPrimaryKey(int key) {
			Session session = factory.getCurrentSession();
			Member member = session.get(Member.class, key);
			return member;
		}
		try {
			QuesBean que = null;

			String Q_Type;
			String Q_Ques;
			String Q_Selection;
			String Q_Ans;
			String P_Class;

			Statement stmt = conn.createStatement();
			String sqlString = "SELECT * " + "FROM Questions WHERE Q_ID = " + Q_ID;

			ResultSet rs = stmt.executeQuery(sqlString);

			if (rs.next()) {
				Q_Type = rs.getString("Q_Type");
				Q_Ques = rs.getString("Q_Ques");
				Q_Selection = rs.getString("Q_Selection");
				Q_Ans = rs.getString("Q_Ans");
				P_Class = rs.getString("P_Class");

				que = new QuesBean(Q_ID, Q_Type, Q_Ques, Q_Selection, Q_Ans, P_Class);
			}
			rs.close();
			stmt.close();
			return que;

		} catch (Exception e) {
			System.err.println("尋找資料時發生錯誤:" + e);
			return null;
		}  */
	}

		//查詢所有資料
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
	
	
	
/* servlet	
	@Override
	public ArrayList<QuesBean> findAllQuesBean() {
		// 取出結果集資料
		ArrayList<QuesBean> allQuesBean = new ArrayList<>();
		// 準備儲存輸出對象的容器
		
		try {
			Statement stmt = conn.createStatement();
			String sqlString = " select * from Questions";
			System.out.println(sqlString);
			ResultSet rs = stmt.executeQuery(sqlString);
			while (rs.next()) {
				QuesBean quesBean = new QuesBean();
				quesBean.setQ_ID(rs.getInt(1));
				quesBean.setQ_Type(rs.getString(2));
				quesBean.setQ_Ques(rs.getString(3));
				quesBean.setQ_Selection(rs.getString(4));
				quesBean.setQ_Ans(rs.getString(5));
				quesBean.setP_Class(rs.getString(6));

				allQuesBean.add(quesBean);

			}
			conn.close();// 歸還資源//非釋放
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allQuesBean;
	} */
	
		
		
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