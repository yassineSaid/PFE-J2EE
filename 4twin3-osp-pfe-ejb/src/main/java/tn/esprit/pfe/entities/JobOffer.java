package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "JobOffer")
public class JobOffer implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String Poste;
	
	@Column
	private String Description;
	
	@Column
	private String Mot_cle;

	public int getId() {
		return id;
	}


	public String getPoste() {
		return Poste;
	}

	public void setPoste(String poste) {
		Poste = poste;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMot_cle() {
		return Mot_cle;
	}

	public void setMot_cle(String mot_cle) {
		Mot_cle = mot_cle;
	}


	public JobOffer(int id, String poste, String description, String mot_cle) {
		super();
		this.id = id;
		Poste = poste;
		Description = description;
		Mot_cle = mot_cle;
	}


	public JobOffer(String poste, String description, String mot_cle) {
		super();
		Poste = poste;
		Description = description;
		Mot_cle = mot_cle;
	}


	public JobOffer() {
		super();
	}
	
	
}
