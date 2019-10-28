package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public int getId() {
		return id;
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
