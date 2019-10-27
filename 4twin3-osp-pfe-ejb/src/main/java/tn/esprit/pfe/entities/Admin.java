package tn.esprit.pfe.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	private Ecole ecole;

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}
	
	
}
