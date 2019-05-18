package ro.licenta.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ro.licenta.dto.SearchDTO;
import ro.licenta.model.Event;

public interface EventService {
	
	Event addEvent(Event event);
	
	Event updateEvent(Event event);
	
	void deleteEvent(Long eventId);
	
	Event findEvent(Long eventId);
	
	List<Event> getAllEvents();
	
	Page<Event> search(SearchDTO searchDTO);
}
