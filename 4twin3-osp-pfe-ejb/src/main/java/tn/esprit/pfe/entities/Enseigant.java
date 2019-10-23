package tn.esprit.pfe.entities;

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
public class Enseigant extends User {

	private static final long serialVersionUID = 1L;
	
	@Column
	private boolean enabled;

	public Enseigant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enseigant(String lastname, String firstname, String email, String password) {
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
