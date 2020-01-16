package tn.esprit.pfe.entities;

/*<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
=======*/
import java.util.HashSet;
//>>>>>>> 8610228526b0e04c6b937d08f0a91a08c6a69bb3
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
/*<<<<<<< HEAD

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

=======*/
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
//>>>>>>> 8610228526b0e04c6b937d08f0a91a08c6a69bb3
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Enseignant")

public class Enseignant extends User {
	
	@OneToMany(mappedBy="enseignant",cascade = CascadeType.ALL, 
			fetch=FetchType.EAGER)
	private Set<Categorie> c=new HashSet<>();
	
	
//	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	private boolean enabled;

	@OneToOne(mappedBy="directeurDesStages")
	@JsonIgnore
	private Site directeurDesStages;
	
	@OneToOne(mappedBy="chefDeDepartement")
	@JsonIgnore
	private Departement chefDeDepartement;
	
	/*@JsonIgnore
	@ManyTo(fetch= FetchType.EAGER)
	private Set<Categorie> categories = new HashSet<Categorie>();
	*/
	@JsonIgnore
	@OneToMany(mappedBy="enseignant",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<EnseignantSheetPFE> enseignantsheet = new HashSet<EnseignantSheetPFE>();

	@ManyToOne
	@JsonIgnore
	private Site site;
	
	@ManyToOne
	@JsonIgnore
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

	public Departement getChefDeDepartement() {
		return chefDeDepartement;
	}

	public void setChefDeDepartement(Departement chefDeDepartement) {
		if (chefDeDepartement==null) {
			this.setRole("Enseignant");
		}
		else {
			this.setRole("ChefDeDepartement");
		}
		this.chefDeDepartement = chefDeDepartement;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	/*public Set<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}*/

	public Set<EnseignantSheetPFE> getEnseignantsheet() {
		return enseignantsheet;
	}

	public void setEnseignantsheet(Set<EnseignantSheetPFE> enseignantsheet) {
		this.enseignantsheet = enseignantsheet;
	}

	
	
	
	
	
}
