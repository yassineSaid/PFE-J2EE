package tn.esprit.pfe.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table
public class Enseignant extends User {
	
	@OneToMany(mappedBy="enseignant",cascade = CascadeType.ALL, 
			fetch=FetchType.EAGER)
	private Set<Categorie> c=new HashSet<>();
	
	
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
