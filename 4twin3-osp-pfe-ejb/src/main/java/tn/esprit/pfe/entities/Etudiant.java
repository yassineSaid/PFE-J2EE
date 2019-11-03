package tn.esprit.pfe.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "id")
public class Etudiant extends User {

	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "etudiant")
	private Reclamation Reclamation;

	@Column
	private String identifiant;

	@JsonIgnore
	@OneToOne(mappedBy="etudiant")
	private InternshipAgreemen internshipAgreemen;
	
	@JsonIgnore
 	@OneToOne(mappedBy="etudiant")
	private SheetPFE sheetPFE;
	
	@JsonIgnore
	@OneToMany(mappedBy="etudiant",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PFENotification> pfeNotifications = new HashSet<PFENotification>();
	

	//@OneToMany(mappedBy = "etudiant", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	// private List<ForumQuestion> fq=new ArrayList<>();

	
	@ManyToOne
	@JsonBackReference
	private Classe classe;

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String lastname, String firstname, String email, String plainPassword, String identifiant) {
		super(lastname, firstname, email, plainPassword);
		this.identifiant = identifiant;
		// TODO Auto-generated constructor stub
	}

	public Reclamation getReclamation() {
		return Reclamation;
	}

	public void setReclamation(Reclamation reclamation) {
		Reclamation = reclamation;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public InternshipAgreemen getInternshipAgreemen() {
		return internshipAgreemen;
	}

	public void setInternshipAgreemen(InternshipAgreemen internshipAgreemen) {
		this.internshipAgreemen = internshipAgreemen;
	}

	public SheetPFE getSheetPFE() {
		return sheetPFE;
	}

	public void setSheetPFE(SheetPFE sheetPFE) {
		this.sheetPFE = sheetPFE;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Set<PFENotification> getPfeNotifications() {
		return pfeNotifications;
	}

	public void setPfeNotifications(Set<PFENotification> pfeNotifications) {
		this.pfeNotifications = pfeNotifications;
	}
	
	
	
	

	/*
	 * public List<ForumQuestion> getFq() { return fq; }
	 * 
	 * public void setFq(List<ForumQuestion> fq) { this.fq = fq; }
	 * 
	 */

}
