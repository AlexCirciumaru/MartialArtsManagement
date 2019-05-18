package ro.licenta.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import ro.licenta.model.utils.EventType;

public class EventDTO {

	private Long id;

	@NotBlank(message = "The name cannot be empty.")
	private String name;

	@NotNull
	private EventType eventType;

	@NotNull(message = "Capacity cannot be empty.")
	private int capacity;

	@NotBlank(message = "Location cannot be empty.")
	private String location;

	@NotNull(message = "Date cannot be empty.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
