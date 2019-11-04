package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "SheetPFE")
public class SheetPFE implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private String problematic;
	private String features;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Categorie> categories = new HashSet<Categorie>();
	private String qrcode;
	private String fromYear;
	private String toYear;
	@Enumerated(EnumType.STRING)
	private EtatSheetPFE etat;
	private String note;
	@ManyToOne
	private Entreprise entreprise;
	@OneToOne
	private Etudiant etudiant;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Enseignant> enseignant = new HashSet<Enseignant>();
	
	@JsonIgnore
	@OneToOne(mappedBy="sheetPFE")
	private RequestCancelInternship request;

	public SheetPFE() {
	}

	public SheetPFE(String title, String description, String problematic, String features) {

		this.title = title;
		this.description = description;
		this.problematic = problematic;
		this.features = features;
	}

	public SheetPFE(int id, String title, String description, String problematic, String features) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.problematic = problematic;
		this.features = features;
	}

	
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

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
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

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Set<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(HashSet<Categorie> categories) {
		this.categories = categories;
	}

	public Set<Enseignant> getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Set<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}

	public RequestCancelInternship getRequest() {
		return request;
	}

	public void setRequest(RequestCancelInternship request) {
		this.request = request;
	}

	
	

	
	
}
