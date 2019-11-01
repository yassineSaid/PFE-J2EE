package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="RequestCancelInternship")
public class RequestCancelInternship  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private SheetPFE sheetPFE;
	@Enumerated(EnumType.STRING)
	private EtatSheetPFE etat;
	@Temporal (TemporalType.DATE)
	private Date created;
	
	public RequestCancelInternship() {
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

	public EtatSheetPFE getEtat() {
		return etat;
	}

	public void setEtat(EtatSheetPFE etat) {
		this.etat = etat;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	

}
