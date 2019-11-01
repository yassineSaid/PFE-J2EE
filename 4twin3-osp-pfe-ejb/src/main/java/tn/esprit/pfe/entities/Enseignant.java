package tn.esprit.pfe.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "Enseignant")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Enseignant extends User {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	private boolean enabled;
	
	@OneToOne(mappedBy="directeurDesStages")
	private Site directeurDesStages;

	@ManyToOne
	private Site site;
	
	@ManyToOne
	private Ecole ecole;

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

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}

	public Site getDirecteurDesStages() {
		return directeurDesStages;
	}

	public void setDirecteurDesStages(Site directeurDesStages) {
		if (directeurDesStages==null) {
			this.setRole("Enseignant");
		}
		else {
			this.setRole("DirecteurDesStages");
		}
		this.directeurDesStages = directeurDesStages;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	
	
	
}
