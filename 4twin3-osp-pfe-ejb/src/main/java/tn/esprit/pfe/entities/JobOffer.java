package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	
	@ManyToOne
	Entreprise Entreprise ;
	
	@Column
	private String Profilrecherche;
	
	@Column
	private String CompétencesetConnaissances;
	
	@Column
	private String Sujet;
	
	@Column
	private String Reference;
	
	@Temporal (TemporalType.DATE)
	private Date datePublier;
	
	@ManyToOne
	InternshipCataloge InternshipCataloge ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Entreprise getEntreprise() {
		return Entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		Entreprise = entreprise;
	}

	public String getProfilrecherche() {
		return Profilrecherche;
	}

	public void setProfilrecherche(String profilrecherche) {
		Profilrecherche = profilrecherche;
	}

	public String getCompétencesetConnaissances() {
		return CompétencesetConnaissances;
	}

	public void setCompétencesetConnaissances(String compétencesetConnaissances) {
		CompétencesetConnaissances = compétencesetConnaissances;
	}

	public String getSujet() {
		return Sujet;
	}

	public void setSujet(String sujet) {
		Sujet = sujet;
	}

	public String getReference() {
		return Reference;
	}

	public void setReference(String reference) {
		Reference = reference;
	}

	public InternshipCataloge getInternshipCataloge() {
		return InternshipCataloge;
	}

	public void setInternshipCataloge(InternshipCataloge internshipCataloge) {
		InternshipCataloge = internshipCataloge;
	}
	

	public Date getDatePublier() {
		return datePublier;
	}

	public void setDatePublier(Date datePublier) {
		this.datePublier = datePublier;
	}

	

	public JobOffer(String poste, String description, String profilrecherche, String compétencesetConnaissances,
			String sujet, String reference, Date datePublier) {
		super();
		Poste = poste;
		Description = description;
		Profilrecherche = profilrecherche;
		CompétencesetConnaissances = compétencesetConnaissances;
		Sujet = sujet;
		Reference = reference;
		this.datePublier = datePublier;
	}

	public JobOffer(int id, String poste, String description, String profilrecherche, String compétencesetConnaissances,
			String sujet, String reference, Date datePublier) {
		super();
		this.id = id;
		Poste = poste;
		Description = description;
		Profilrecherche = profilrecherche;
		CompétencesetConnaissances = compétencesetConnaissances;
		Sujet = sujet;
		Reference = reference;
		this.datePublier = datePublier;
	}

	public JobOffer() {
		super();
	}

	
	
}
