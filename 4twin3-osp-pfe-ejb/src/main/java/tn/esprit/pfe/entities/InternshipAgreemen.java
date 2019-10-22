package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="T_InternshipAgreemen")
public class InternshipAgreemen implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date startDate;
	private Date endDate;
	
	
	public InternshipAgreemen() {
	}

	public InternshipAgreemen(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public InternshipAgreemen(int id, Date startDate, Date endDate) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal (TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal (TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	

}
