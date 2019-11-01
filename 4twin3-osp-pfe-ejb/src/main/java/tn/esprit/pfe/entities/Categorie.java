package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table( name = "Categorie")
@Inheritance(strategy = InheritanceType.JOINED)

public class Categorie implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private int id; 
	@Column
	private String name; 
	
	@Column
	private String image; 
	
	@Column
	private boolean exixtecommemodule;
	
	@JsonIgnore
	@ManyToMany(mappedBy="categories", cascade = CascadeType.ALL)
	private Set<SheetPFE> sheetPFEs;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Enseignant> enseignant;
	
	
	public Categorie() {
		super();
	}

	public Categorie(int id, String name, String image, boolean exixtecommemodule) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.exixtecommemodule = exixtecommemodule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isExixtecommemodule() {
		return exixtecommemodule;
	}

	public void setExixtecommemodule(boolean exixtecommemodule) {
		this.exixtecommemodule = exixtecommemodule;
	}

	public Set<SheetPFE> getSheetPFEs() {
		return sheetPFEs;
	}

	public void setSheetPFEs(Set<SheetPFE> sheetPFEs) {
		this.sheetPFEs = sheetPFEs;
	}

	public Set<Enseignant> getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Set<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}

	
	
	
	

}