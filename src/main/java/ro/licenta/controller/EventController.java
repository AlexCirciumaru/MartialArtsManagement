package ro.licenta.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.licenta.dto.EventDTO;
import ro.licenta.dto.SearchDTO;
import ro.licenta.model.Event;
import ro.licenta.service.EventService;

@Controller
public class EventController {

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService eventService;

	@GetMapping(value = "/events")
	public String getEvents(@ModelAttribute SearchDTO searchDTO, Model model) {
		Page<Event> events = eventService.search(searchDTO);
		model.addAttribute("events", events);
		return "events";
	}

	@GetMapping(value = "/event/add")
	public String getAddEventForm(Model model) {
		EventDTO eventDTO = new EventDTO();
		model.addAttribute("eventDTO", eventDTO);
		return "add-event";
	}

	@PostMapping(value = "/event/add")
	public String addEventForm(@Valid @ModelAttribute EventDTO eventDTO, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Add event error : " + result.getAllErrors());
			return "add-event";
		} else {
			Event event = new Event();
			event.setName(eventDTO.getName());
			event.setEventType(eventDTO.getEventType());
			event.setCapacity(eventDTO.getCapacity());
			event.setLocation(eventDTO.getLocation());
			event.setDate(eventDTO.getDate());
			
			eventService.addEvent(event);
			redirectAttributes.addFlashAttribute("message", "Successfully added.");
			return "redirect:/events";
		}
	}

	@GetMapping(value = "/event/update")
	public String getEditEventForm(@RequestParam(value = "id", required = true) Long id, Model model,
			RedirectAttributes redirectAttributes) {
		Event event = eventService.findEvent(id);
		logger.debug("Edit event : " + event);
		if (event != null) {
			// Create and put EventDTO needed to edit the event.
			EventDTO eventDTO = new EventDTO();
			eventDTO.setId(event.getId());
			eventDTO.setName(event.getName());
			eventDTO.setEventType(event.getEventType());
			eventDTO.setCapacity(event.getCapacity());
			eventDTO.setLocation(event.getLocation());
			eventDTO.setDate(event.getDate());

			model.addAttribute("eventDTO", eventDTO);

			return "update-event";
		} else {
			logger.error("Edit error : Event with id {} not found." + id);
			redirectAttributes.addFlashAttribute("errorMessage", "Event with specified id not found.");
			return "redirect:/events";
		}
	}

	@PostMapping(value = "/event/update")
	public String updateEvent(@Valid @ModelAttribute EventDTO eventDTO, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Update event validation error." + result.getAllErrors());
			return "update-degree";
		} else {
			Event event = new Event();
			event.setId(eventDTO.getId());
			event.setName(eventDTO.getName());
			event.setEventType(eventDTO.getEventType());
			event.setCapacity(eventDTO.getCapacity());
			event.setLocation(eventDTO.getLocation());
			event.setDate(eventDTO.getDate());

			eventService.updateEvent(event);

			redirectAttributes.addFlashAttribute("message", "Event updated successfully.");
			return "redirect:/events";
		}
	}

	@GetMapping(value = "/event/delete")
	public String deleteEvent(@Valid @ModelAttribute Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			eventService.deleteEvent(id);
			redirectAttributes.addFlashAttribute("message", "Event successfully deleted.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error : " + e.getMessage());
		}
		return "redirect:/events";
	}
}
