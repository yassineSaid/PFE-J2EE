package tn.esprit.pfe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



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
	private InternshipAgreemen internshipAgreemen;
	
	@OneToOne(mappedBy="etudiant")
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

	
}
