package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="SheetPFE")
public class SheetPFE implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String title;
	private String description;
	private String problematic;
	private String features;
	private List<Categorie> categories;
	private String keywords;
	private EtatSheetPFE etat;
	private String note;
	private Entreprise entreprise;
	private Etudiant etudiant;
	
	public SheetPFE() {
	}

	public SheetPFE(String title, String description, String problematic, String features, List<Categorie> categories,
			String keywords) {
		
		this.title = title;
		this.description = description;
		this.problematic = problematic;
		this.features = features;
		this.categories = categories;
		this.keywords = keywords;
	}
	
	public SheetPFE(int id,String title, String description, String problematic, String features, List<Categorie> categories,
			String keywords) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.problematic = problematic;
		this.features = features;
		this.categories = categories;
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

	@ManyToMany
	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
	public EtatSheetPFE getEtat() {
		return etat;
	}

	public void setEtat(EtatSheetPFE etat) {
		this.etat = etat;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@OneToOne
	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	@OneToOne(mappedBy="sheetPFE")
	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	
	
	
	
	
}
