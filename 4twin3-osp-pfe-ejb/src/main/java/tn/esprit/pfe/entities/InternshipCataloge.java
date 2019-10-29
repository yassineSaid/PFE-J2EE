package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "InternshipCataloge")
public class InternshipCataloge implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String CatalogName;
	
	@Column
	private String Description;
	
	@OneToMany(mappedBy="internshipCataloge ", cascade = 	{CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<JobOffer > JobOffers;

	@OneToMany(mappedBy="internshipCataloge ", cascade = 	{CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private Set<InternshipOffer > InternshipOffers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatalogName() {
		return CatalogName;
	}

	public void setCatalogName(String catalogName) {
		CatalogName = catalogName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Set<JobOffer> getJobOffers() {
		return JobOffers;
	}

	public void setJobOffers(Set<JobOffer> jobOffers) {
		JobOffers = jobOffers;
	}

	public Set<InternshipOffer> getInternshipOffers() {
		return InternshipOffers;
	}

	public void setInternshipOffers(Set<InternshipOffer> internshipOffers) {
		InternshipOffers = internshipOffers;
	}

	public InternshipCataloge(int id, String catalogName, String description) {
		super();
		this.id = id;
		CatalogName = catalogName;
		Description = description;
	}

	public InternshipCataloge(String catalogName, String description) {
		super();
		CatalogName = catalogName;
		Description = description;
	}

	public InternshipCataloge() {
		super();
	}

	
	
	
}
