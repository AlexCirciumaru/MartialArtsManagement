package ro.licenta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MembershipFeeDTO {
	
	private Long id;
	
	@NotBlank(message = "Year cannot be empty.")
	private int year;
	
	@NotBlank(message = "Value cannot be empty.")
	private int value;
	
	@NotNull
	private Long clubId;
	
	@NotNull
	private Long karatekaId;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	public Long getKaratekaId() {
		return karatekaId;
	}

	public void setKaratekaId(Long karatekaId) {
		this.karatekaId = karatekaId;
	}
}
