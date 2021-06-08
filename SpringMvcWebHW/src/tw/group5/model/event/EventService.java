package tw.group5.model.event;

import java.util.List;

public interface EventService {

	
	
	public Eventbean findByPrimaryKey(int key);
	public Eventbean findByName(String name);
	public int saveEvent(Eventbean eventbean);
	public void updateEvent(Eventbean eventbean);
	public void evictEvent(Eventbean eventbean);
	public void deleteEventByPrimaryKey(int key);
	public List<Eventbean> findAllEvents();
	public void deleteAllEvents() ;
	public boolean isEventbeanExist(Eventbean eventbean);
	public String checkEventId(String eventbeanId);
}
