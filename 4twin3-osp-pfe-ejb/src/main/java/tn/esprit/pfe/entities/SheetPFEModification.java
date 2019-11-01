package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="SheetPFEModification")
public class SheetPFEModification implements Serializable  {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	@ManyToOne
	private SheetPFE sheetPFE;
	private String problematic;
	private String features;
	@Enumerated(EnumType.STRING)
	private EtatSheetPFE etat;
	private String note;
	@Temporal (TemporalType.DATE)
	private Date created;
	
	
	public SheetPFEModification() {
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SheetPFE getSheetPFE() {
		return sheetPFE;
	}
	public void setSheetPFE(SheetPFE sheetPFE) {
		this.sheetPFE = sheetPFE;
	}
	public String getProblematic() {
		return problematic;
	}
	public void setProblematic(String problematic) {
		this.problematic = problematic;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public EtatSheetPFE getEtat() {
		return etat;
	}
	public void setEtat(EtatSheetPFE etat) {
		this.etat = etat;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	

}
