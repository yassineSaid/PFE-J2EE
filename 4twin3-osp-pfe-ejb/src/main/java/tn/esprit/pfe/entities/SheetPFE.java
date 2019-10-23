package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="T_SheetPFE")
public class SheetPFE implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String description;
	private String problematic;
	private String features;
	private List<Categorie> categorie;
	private String keywords;
	private Entreprise entreprise;
	private Etudiant etudiant;
	
	public SheetPFE() {
	}

	public SheetPFE(String title, String description, String problematic, String features, List<Categorie> categorie,
			String keywords) {
		
		this.title = title;
		this.description = description;
		this.problematic = problematic;
		this.features = features;
		this.categorie = categorie;
		this.keywords = keywords;
	}
	
	public SheetPFE(int id,String title, String description, String problematic, String features, List<Categorie> categorie,
			String keywords) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.problematic = problematic;
		this.features = features;
		this.categorie = categorie;
		this.keywords = keywords;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProblematic() {
		return problematic;
	}

	public void setProblematic(String problematic) {
		this.problematic = problematic;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Categorie> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<Categorie> categorie) {
		this.categorie = categorie;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@OneToOne
	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	@OneToOne
	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	
	
	
	
	
}
