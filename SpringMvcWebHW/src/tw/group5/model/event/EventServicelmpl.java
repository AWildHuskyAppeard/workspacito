package tw.group5.model.event;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service
@Transactional
@EnableTransactionManagement

public class EventServicelmpl implements EventService {

	@Autowired
	EventDao eventDao ;
	
	
	@Override
	public Eventbean findByPrimaryKey(int key) {
		return eventDao.findByPrimaryKey(key);
	}

	@Override
	public Eventbean findByName(String name) {
		return eventDao.findByName(name);
	}

	@Override
	public int saveEvent(Eventbean eventbean) {
		int n = eventDao.saveEvent(eventbean);
		return n ;
	}

	@Override
	public void updateEvent(Eventbean eventbean) {
		eventDao.updateEvent(eventbean);
	}

	@Override
	public void evictEvent(Eventbean eventbean) {
		eventDao.evictEvent(eventbean);
	}

	@Override
	public void deleteEventByPrimaryKey(int key) {
		eventDao.deleteEventByPrimaryKey(key);
	}

	@Override
	public List<Eventbean> findAllEvents() {
		return eventDao.findAllEvents();
	}

	@Override
	public void deleteAllEvents() {
		eventDao.deleteAllEvents();
	}

	@Override
	public boolean isEventbeanExist(Eventbean eventbean) {
		return eventDao.isEventbeanExist(eventbean);
	}

	@Override
	public String checkEventId(String eventbeanId) {
		return eventDao.checkEventId(eventbeanId);
	}

}
