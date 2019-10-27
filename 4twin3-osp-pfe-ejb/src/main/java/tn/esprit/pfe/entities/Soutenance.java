package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Soutenance")
public class Soutenance implements Serializable {
	

	public Soutenance(List<Jury> jury) {
		super();
		this.jury = jury;
	}

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;

	public Soutenance(String titre, String description, Date dateSoutenance, String salle, Date heureSoutenance,int noteSoutenance) {
		Titre = titre;
		Description = description;
		this.dateSoutenance = dateSoutenance;
		Salle = salle;
		HeureSoutenance = heureSoutenance;
		NoteSoutenance = noteSoutenance;
	}

	public Soutenance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	private String Titre;

	@Column
	private String Description;

	@Temporal(TemporalType.DATE)
	private Date dateSoutenance;

	@Column
	private String Salle;
	@Temporal(TemporalType.DATE)
	private Date HeureSoutenance;
	@Column
	private int NoteSoutenance;
	@Column
	private List<Jury> jury;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getDateSoutenance() {
		return dateSoutenance;
	}

	public void setDateSoutenance(Date dateSoutenance) {
		this.dateSoutenance = dateSoutenance;
	}

	public String getSalle() {
		return Salle;
	}

	public void setSalle(String salle) {
		Salle = salle;
	}

	public Date getHeureSoutenance() {
		return HeureSoutenance;
	}

	public void setHeureSoutenance(Date heureSoutenance) {
		HeureSoutenance = heureSoutenance;
	}

	public int getNoteSoutenance() {
		return NoteSoutenance;
	}

	public void setNoteSoutenance(int noteSoutenance) {
		NoteSoutenance = noteSoutenance;
	}

	public List<Jury> getJury() {
		return jury;
	}

	public void setJury(List<Jury> jury) {
		this.jury = jury;
	}

}
