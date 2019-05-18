package ro.licenta.model.utils;

public enum EventType {
	DEMONSTRATION("DEMONSTRATION"), SEMINAR("SEMINAR"), COMPETITION("COMPETITION");
	
	private final String displayEvent;
	
	EventType(String displayEvent){
		this.displayEvent = displayEvent;
	}

	public String getDisplayEvent() {
		return displayEvent;
	}
}
