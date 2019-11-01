package tn.esprit.pfe.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table
public class Enseignant extends User {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	private boolean enabled;
	
	@OneToOne(mappedBy="directeurDesStages")
	@JsonIgnore
	private Site directeurDesStages;
	
	@OneToOne(mappedBy="chefDeDepartement")
	@JsonIgnore
	private Departement chefDeDepartement;
	
	@JsonIgnore
	@ManyToMany(fetch= FetchType.EAGER)
	private Set<Categorie> categories = new HashSet<Categorie>();
	
	@JsonIgnore
	@ManyToMany(mappedBy="enseignant",fetch = FetchType.EAGER)
	private Set<SheetPFE> sheetPFEs = new HashSet<SheetPFE>();

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

	public Set<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}

	public Set<SheetPFE> getSheetPFEs() {
		return sheetPFEs;
	}

	public void setSheetPFEs(Set<SheetPFE> sheetPFEs) {
		this.sheetPFEs = sheetPFEs;
	}

	
	
	
	
}
