package tn.esprit.pfe.entities;


import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table
@PrimaryKeyJoinColumn(name = "id")
public class Etudiant extends User {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy="etudiant")
	private Reclamation Reclamation;
	
	@Column
	private String identifiant;
	
	@OneToOne(mappedBy="etudiant")
	@JsonIgnore
	private InternshipAgreemen internshipAgreemen;
	
	 @OneToMany(mappedBy="etudiant", cascade = {CascadeType.ALL}, 
		fetch=FetchType.EAGER)
      Set<ForumQuestion> fq;
	
	
	@OneToOne
	private SheetPFE sheetPFE;

	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String lastname, String firstname, String email, String password, String identifiant) {
		super(lastname, firstname, email, password);
		this.identifiant=identifiant;
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

	public Set<ForumQuestion> getFq() {
		return fq;
	}

	public void setFq(Set<ForumQuestion> fq) {
		this.fq = fq;
	}	
}
