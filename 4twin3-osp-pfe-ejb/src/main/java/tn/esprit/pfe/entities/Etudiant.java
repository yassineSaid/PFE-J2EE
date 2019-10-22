package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table
@PrimaryKeyJoinColumn(name = "id")
public class Etudiant extends User {

	private static final long serialVersionUID = 1L;
	


	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String lastname, String firstname, String email, String password) {
		super(lastname, firstname, email, password);
		// TODO Auto-generated constructor stub
	}

	
}
