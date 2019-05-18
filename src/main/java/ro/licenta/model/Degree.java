package ro.licenta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ro.licenta.model.utils.DegreeType;

@Entity
@Table(name = "degree")
public class Degree {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "degree_rank", nullable = false)
	private int degreeRank;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "degree_type", nullable = false)
	private DegreeType degreeType;
	
	@Column(name = "belt_color", nullable = false)
	private String beltColor;
	
	public Degree() {		
	}
	
	public Degree(Long id) {
		super();
		this.id = id;
	}

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
	
	public String getFullDegree() {
		return String.valueOf(degreeRank) + " " + degreeType;
	}
}
