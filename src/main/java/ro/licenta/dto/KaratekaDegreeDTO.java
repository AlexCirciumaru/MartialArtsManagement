package ro.licenta.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class KaratekaDegreeDTO {
	
	private Long id;
	
	@NotNull
	private Long karatekaId;
	
	@NotNull
	private Long degreeId;
	
	@NotNull(message = "Date cannot be empty.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfReceipt;
	
	@NotNull
	private Long clubId;
	
	@NotNull
	private Long trainerId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKaratekaId() {
		return karatekaId;
	}

	public void setKaratekaId(Long karatekaId) {
		this.karatekaId = karatekaId;
	}

	public Long getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(Long degreeId) {
		this.degreeId = degreeId;
	}

	public Date getDateOfReceipt() {
		return dateOfReceipt;
	}

	public void setDateOfReceipt(Date dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

	public Long getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
}
