package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


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

	@Column
	private String Password;

	@Column
	private int xp;

	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<InternshipOffer> internshipoffers ;

	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<EntrepriseSupervisor> entreprisesupervisors ;

	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<InternshipCataloge> internshipcataloges;

	@ManyToOne
	Packs Packs;

	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<JobOffer> joboffers;

	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<EntrepriseStudent> entreprisestudents;


	@JsonBackReference
	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<InternshipAgreemen> internshipAgreemens;

	@JsonBackReference
	@OneToMany(mappedBy="entreprise", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<SheetPFE> sheetPFEs;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setEmailResponsable(String emailResponsable) {
		EmailResponsable = emailResponsable;
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

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public Set<InternshipOffer> getInternshipOffers() {
		return internshipoffers;
	}

	public void setInternshipOffers(Set<InternshipOffer> internshipOffers) {
		this.internshipoffers = internshipOffers;
	}

	public Set<EntrepriseSupervisor> getEntrepriseSupervisors() {
		return entreprisesupervisors;
	}

	public void setEntrepriseSupervisors(Set<EntrepriseSupervisor> entreprisesupervisors) {
		this.entreprisesupervisors = entreprisesupervisors;
	}

	public Set<InternshipCataloge> getInternshipcataloges() {
		return internshipcataloges;
	}

	public void setInternshipcataloges(Set<InternshipCataloge> internshipcataloges) {
		this.internshipcataloges = internshipcataloges;
	}


	public Packs getPacks() {
		return Packs;
	}

	public void setPacks(Packs packs) {
		Packs = packs;
	}

	public Set<JobOffer> getJobOffers() {
		return joboffers;
	}

	public void setJobOffers(Set<JobOffer> joboffers) {
		this.joboffers = joboffers;
	}

	public Set<EntrepriseStudent> getEntreprisestudents() {
		return entreprisestudents;
	}

	public void setEntreprisestudents(Set<EntrepriseStudent> entreprisestudents) {
		this.entreprisestudents = entreprisestudents;
	}

	public Set<InternshipAgreemen> getInternshipAgreemens() {
		return internshipAgreemens;
	}

	public void setInternshipAgreemens(Set<InternshipAgreemen> internshipAgreemens) {
		this.internshipAgreemens = internshipAgreemens;
	}

	public Set<SheetPFE> getSheetPFEs() {
		return sheetPFEs;
	}

	public void setSheetPFEs(Set<SheetPFE> sheetPFEs) {
		this.sheetPFEs = sheetPFEs;
	}

	


}
