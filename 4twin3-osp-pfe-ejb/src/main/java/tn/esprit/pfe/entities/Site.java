package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table
public class Site implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column
	@NotNull
	@NotBlank
	private String nom;

	@Column
	@NotNull
	@NotBlank
	private String adresse;

	@OneToOne
	private Enseignant directeurDesStages;
	
	@ManyToOne
	private Ecole ecole;

	public Site() {
		super();
	}

	public Site(String nom, String adresse) {
		super();
		this.nom = nom;
		this.adresse = adresse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Enseignant getDirecteurDesStages() {
		return directeurDesStages;
	}

	public void setDirecteurDesStages(Enseignant directeurDesStages) {
		this.directeurDesStages = directeurDesStages;
	}

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}
	
	
	

}
