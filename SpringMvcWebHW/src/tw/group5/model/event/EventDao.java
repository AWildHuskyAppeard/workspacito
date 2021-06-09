package tw.group5.model.event;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface EventDao  {
	
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
