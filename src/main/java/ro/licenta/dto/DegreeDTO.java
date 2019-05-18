package ro.licenta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ro.licenta.model.utils.DegreeType;

public class DegreeDTO {

	private Long id;
	
	@NotNull
	private int degreeRank;
	
	@NotNull
	private DegreeType degreeType;
	
	@NotBlank(message = "Belt Color cannot be empty.")
	private String beltColor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDegreeRank() {
		return degreeRank;
	}

	public void setDegreeRank(int degreeRank) {
		this.degreeRank = degreeRank;
	}

	public String getBeltColor() {
		return beltColor;
	}

	public void setBeltColor(String beltColor) {
		this.beltColor = beltColor;
	}

	public DegreeType getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(DegreeType degreeType) {
		this.degreeType = degreeType;
	}
}
