package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table( name = "Categorie")
@Inheritance(strategy = InheritanceType.JOINED)

public class Categorie implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id; 
	@Column
	private String name; 
	
	@Column
	private String image; 
	
	@Column
	private boolean exixtecommemodule;
	
	@JsonIgnore
	@ManyToMany(mappedBy="categories")
	private List<SheetPFE> sheetPFEs;
	

	@JsonIgnore
    @ManyToOne
	Enseignant enseignant; 
	
	
	
	public Categorie() {
		super();
	}

	public Categorie( String name, String image, boolean exixtecommemodule) {
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

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public List<SheetPFE> getSheetPFEs() {
		return sheetPFEs;
	}

	public void setSheetPFEs(List<SheetPFE> sheetPFEs) {
		this.sheetPFEs = sheetPFEs;
	}
	
	
	
	
	

}