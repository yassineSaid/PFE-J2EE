package tn.esprit.pfe.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "Enseignant")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Enseignant extends User {

	private static final long serialVersionUID = 1L;
	@Column(nullable=false)
	private boolean enabled;

	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enseignant(String lastname, String firstname, String email, String password) {
		super(lastname, firstname, email, password);
		this.enabled=false;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	
}
