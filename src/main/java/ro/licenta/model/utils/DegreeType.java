package ro.licenta.model.utils;

public enum DegreeType {
	KYU("KYU"), DAN("DAN");
	
	private final String displayDegree;
	
	DegreeType(String displayDegree) {
		this.displayDegree = displayDegree;
	}

	public String getDisplayDegree() {
		return displayDegree;
	}
}
