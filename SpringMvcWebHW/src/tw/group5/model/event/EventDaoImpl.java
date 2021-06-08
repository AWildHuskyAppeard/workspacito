package tw.group5.model.event;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EventDaoImpl implements  EventDao{
	@Autowired
	SessionFactory factory;

	
	public Eventbean findByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
		Eventbean Eventbean = session.get(Eventbean.class, key);
		return Eventbean;
	}

	
	@SuppressWarnings("unchecked")
	public Eventbean findByName(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Eventbean WHERE name=:name";
		Eventbean Eventbean = null;
		List<Eventbean> list = session.createQuery(hql).setParameter("name", name).getResultList();
		if (!list.isEmpty())
			Eventbean = list.get(0);
		return Eventbean;
	}

	@Override
	public int saveEvent(Eventbean eventbean) {
		int n = 0;
		boolean exist = isEventbeanExist(eventbean);
		if (exist) {
			return -1;
		}
		Session session = factory.getCurrentSession();
		try {
			session.save(eventbean);
			n = 1;
		} catch (Exception e) {
			n = -2;
		}
		return n;
	}

	@Override
	public void updateEvent(Eventbean eventbean) {
		Session session = factory.getCurrentSession();
		session.update(eventbean);

	}
	
	@Override
	public void evictEvent(Eventbean eventbean) {
		Session session = factory.getCurrentSession();
		session.evict(eventbean);

	}

	@Override
	public void deleteEventByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
		Eventbean eventbean = new Eventbean();
		eventbean.setAid(key);
		session.delete(eventbean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eventbean> findAllEvents() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Eventbean";
		List<Eventbean> list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void deleteAllEvents() {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM Member";
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public boolean isEventbeanExist(Eventbean eventbean) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM Eventbean m WHERE uid=:id";
		try {
			session.createQuery(hql).setParameter("id", eventbean.getUid()).getSingleResult();
			exist = true;
		} catch (NoResultException ex) {
			;
		}
		return exist;
	}

	@Override
	public String checkEventId(String eventbeanId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Eventbean m WHERE e.id = :id";
		String id = "";
		try {
			Eventbean e = (Eventbean) session.createQuery(hql).setParameter("id", eventbeanId).getSingleResult();
			id = e.getUid();
		} catch (NoResultException ex) {
			;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			id = "Error: 資料庫異常，請檢查資料庫";
		}
		return id;
	}


	
}

