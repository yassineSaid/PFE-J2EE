package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Packs")
public class Packs implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String Nom;
	
	@Column
	private String Description;

	public int getId() {
		return id;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Packs(int id, String nom, String description) {
		super();
		this.id = id;
		Nom = nom;
		Description = description;
	}
	
	public Packs( String nom, String description) {
		super();
		Nom = nom;
		Description = description;
	}
	
	
}
