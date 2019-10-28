package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name= "InternshipOffer")
public class InternshipOffer implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column
	private String OffreName;
	
	@Column
	private String Description;

	@Temporal (TemporalType.DATE)
	private Date dateDebut;
	
	@Temporal (TemporalType.DATE)
	private Date dateFin;

	public InternshipOffer(String offreName, String description, Date dateDebut, Date dateFin) {
		super();
		OffreName = offreName;
		Description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public InternshipOffer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOffreName() {
		return OffreName;
	}

	public void setOffreName(String offreName) {
		OffreName = offreName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	
}
