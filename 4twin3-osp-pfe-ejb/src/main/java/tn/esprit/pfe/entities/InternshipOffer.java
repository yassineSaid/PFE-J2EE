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

}
