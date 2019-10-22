package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;






@Entity
public class Reclamation implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReclamation;
	
	private String textRec;
	
	@OneToOne
	private Etudiant etudiant;

	public int getIdReclamation() {
		return idReclamation;
	}

	

	public String getTextRec() {
		return textRec;
	}

	public void setTextRec(String textRec) {
		this.textRec = textRec;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	

	

	
	
}