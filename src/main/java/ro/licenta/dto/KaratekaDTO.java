package ro.licenta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class KaratekaDTO {

	private Long id;
	
	@NotBlank(message = "The first name cannot be empty.")
	private String firstName;
	
	@NotBlank(message = "The last name cannot be empty.")
	private String lastName;
	
	@NotNull(message = "Age cannot be empty.")
	private int age;
	
	@NotNull(message = "Beggininn Year cannot be empty.")
	private int beginningYear;
	
	@NotBlank(message = "Email cannot be empty.")
	private String email;
	
	@NotBlank(message = "Password cannot be empty")
	private String password;
	
	@NotNull(message = "Trainer cannot be empty.")
	private boolean trainer;
	
	@NotNull
	private Long clubId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBeginningYear() {
		return beginningYear;
	}

	public void setBeginningYear(int beginningYear) {
		this.beginningYear = beginningYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTrainer() {
		return trainer;
	}

	public void setTrainer(boolean trainer) {
		this.trainer = trainer;
	}

	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}
}
