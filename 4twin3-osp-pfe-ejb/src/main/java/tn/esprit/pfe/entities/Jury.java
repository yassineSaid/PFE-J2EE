package tn.esprit.pfe.entities;

import javax.persistence.Column;

public class Jury  extends Enseignant{
	private static final long serialVersionUID = 1L;

	public Jury() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jury(String lastname, String firstname, String email, String password) {
		super(lastname, firstname, email, password);
		// TODO Auto-generated constructor stub
	}

	public Jury(tn.esprit.pfe.entities.Enseignant enseignant, String poste, Boolean disponibilité) {
		super();
		Enseignant = enseignant;
		Poste = poste;
		Disponibilité = disponibilité;
	}

	@Column
	private Enseignant Enseignant;

	@Column
	private String Poste;
	
	@Column
	private Boolean Disponibilité;

	public Enseignant getEnseignant() {
		return Enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		Enseignant = enseignant;
	}

	public String getPoste() {
		return Poste;
	}

	public void setPoste(String poste) {
		Poste = poste;
	}

	public Boolean getDisponibilité() {
		return Disponibilité;
	}

	public void setDisponibilité(Boolean disponibilité) {
		Disponibilité = disponibilité;
	}

}
