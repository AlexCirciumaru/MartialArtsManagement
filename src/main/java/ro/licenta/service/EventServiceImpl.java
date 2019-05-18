package ro.licenta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ro.licenta.controller.utils.PaginationUtils;
import ro.licenta.dto.SearchDTO;
import ro.licenta.exception.RecordNotFoundException;
import ro.licenta.model.Event;
import ro.licenta.repository.EventRepository;

@Service("eventService")
public class EventServiceImpl implements EventService {

	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

	@Autowired
	private EventRepository eventRepository;

	@Override
	public Event addEvent(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public Event updateEvent(Event event) {
		Event existingEvent = eventRepository.findById(event.getId()).orElse(null);
		if (existingEvent == null) {
			String errorMessage = "Event with id : " + event.getId() + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		return eventRepository.save(event);
	}

	@Override
	public void deleteEvent(Long eventId) {
		Event event = eventRepository.findById(eventId).orElse(null);
		if (event != null) {
			eventRepository.deleteById(eventId);
		} else {
			String errorMessage = "Event with id : " + eventId + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
	}

	@Override
	public Event findEvent(Long eventId) {
		return eventRepository.findById(eventId).orElse(null);
	}

	@Override
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	@Override
	public Page<Event> search(SearchDTO searchDTO) {
		Page<Event> result = null;
		PageRequest pageRequest = PaginationUtils.getPageRequest(searchDTO);
		if (StringUtils.hasText(searchDTO.getQuery())) {
			result = this.eventRepository
					.findByNameIgnoreCaseContainingOrLocationIgnoreCaseContaining(
							pageRequest, searchDTO.getQuery(), searchDTO.getQuery());
		} else {
			result = this.eventRepository.findAll(pageRequest);
		}
		return result;
	}
}
