package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name= "Entreprise")
public class Entreprise implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String NameEntreprise;
	
	@Column
	private String Adresse;
	
	@Column
	private String Siteweb;
	
	@Column
	private String Pays;
	
	@Column
	private String EmailEntreprise;
	
	@Column
	private String TelEntreprise;
	
	@Column 
	private String EmailResponsable;
	
	@Column
	private String NomPrenomResponsable;
	
	@Column
	private String TelResponsable;
	
	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.LAZY)
	private List<InternshipAgreemen> internshipAgreemens = new ArrayList<>();
	
	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private List<SheetPFE> sheetPFEs = new ArrayList<>();


	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String nameEntreprise, String adresse, String siteweb, String pays, String emailEntreprise,
			String telEntreprise, String emiailResponsable, String nomPrenomResponsable, String telResponsable) {
		super();
		NameEntreprise = nameEntreprise;
		Adresse = adresse;
		Siteweb = siteweb;
		Pays = pays;
		EmailEntreprise = emailEntreprise;
		TelEntreprise = telEntreprise;
		EmailResponsable = emiailResponsable;
		NomPrenomResponsable = nomPrenomResponsable;
		TelResponsable = telResponsable;
	}

	public int getId() {
		return id;
	}

	public String getNameEntreprise() {
		return NameEntreprise;
	}

	public void setNameEntreprise(String nameEntreprise) {
		NameEntreprise = nameEntreprise;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getSiteweb() {
		return Siteweb;
	}

	public void setSiteweb(String siteweb) {
		Siteweb = siteweb;
	}

	public String getPays() {
		return Pays;
	}

	public void setPays(String pays) {
		Pays = pays;
	}

	public String getEmailEntreprise() {
		return EmailEntreprise;
	}

	public void setEmailEntreprise(String emailEntreprise) {
		EmailEntreprise = emailEntreprise;
	}

	public String getTelEntreprise() {
		return TelEntreprise;
	}

	public void setTelEntreprise(String telEntreprise) {
		TelEntreprise = telEntreprise;
	}

	public String getEmailResponsable() {
		return EmailResponsable;
	}

	public void setEmailResponsable(String emiailResponsable) {
		EmailResponsable = emiailResponsable;
	}

	public String getNomPrenomResponsable() {
		return NomPrenomResponsable;
	}

	public void setNomPrenomResponsable(String nomPrenomResponsable) {
		NomPrenomResponsable = nomPrenomResponsable;
	}

	public String getTelResponsable() {
		return TelResponsable;
	}

	public void setTelResponsable(String telResponsable) {
		TelResponsable = telResponsable;
	}

	public List<InternshipAgreemen> getInternshipAgreemens() {
		return internshipAgreemens;
	}

	public void setInternshipAgreemens(List<InternshipAgreemen> internshipAgreemens) {
		this.internshipAgreemens = internshipAgreemens;
	}

	public List<SheetPFE> getSheetPFEs() {
		return sheetPFEs;
	}

	public void setSheetPFEs(List<SheetPFE> sheetPFEs) {
		this.sheetPFEs = sheetPFEs;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
