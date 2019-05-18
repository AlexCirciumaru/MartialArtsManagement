package ro.licenta.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ClubDTO {

	private Long id;

	@NotBlank(message = "The club name cannot be empty.")
	private String clubName;

	@NotBlank(message = "Address cannot be empty.")
	private String address;

	@NotNull(message = "Date of establishment cannot be empty.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfEstablishment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfEstablishment() {
		return dateOfEstablishment;
	}

	public void setDateOfEstablishment(Date dateOfEstablishment) {
		this.dateOfEstablishment = dateOfEstablishment;
	}
}
