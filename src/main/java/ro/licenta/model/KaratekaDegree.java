package ro.licenta.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "karateka_degree")
public class KaratekaDegree {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "karateka_id", nullable = false)
	private Karateka karateka;
	
	@ManyToOne
	@JoinColumn(name = "degree_id", nullable = false)
	private Degree degree;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_of_receipt", nullable = false)
	private Date dateOfReceipt;
	
	@ManyToOne
	@JoinColumn(name = "club_id", nullable = false)
	private Club club;
	
	@ManyToOne
	@JoinColumn(name = "trainer_id", nullable = false)
	private Karateka trainer;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Karateka getKarateka() {
		return karateka;
	}

	public void setKarateka(Karateka karateka) {
		this.karateka = karateka;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public Date getDateOfReceipt() {
		return dateOfReceipt;
	}

	public void setDateOfReceipt(Date dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Karateka getTrainer() {
		return trainer;
	}

	public void setTrainer(Karateka trainer) {
		this.trainer = trainer;
	}
}
