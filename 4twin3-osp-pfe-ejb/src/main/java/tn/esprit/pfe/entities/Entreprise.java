package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Entreprise")
public class Entreprise implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column
	private String NameEntreprise;
	
	@Column
	private String Adresse;
	
	@Column
	private String Siteweb;
	
	@Column
	private String Pays;
	
	@Column
	private String EmailEntreprise;
	
	@Column
	private String TelEntreprise;
	
	@Column 
	private String EmailResponsable;
	
	@Column
	private String NomPrenomResponsable;
	
	@Column
	private String TelResponsable;

	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String nameEntreprise, String adresse, String siteweb, String pays, String emailEntreprise,
			String telEntreprise, String emiailResponsable, String nomPrenomResponsable, String telResponsable) {
		super();
		NameEntreprise = nameEntreprise;
		Adresse = adresse;
		Siteweb = siteweb;
		Pays = pays;
		EmailEntreprise = emailEntreprise;
		TelEntreprise = telEntreprise;
		EmiailResponsable = emiailResponsable;
		NomPrenomResponsable = nomPrenomResponsable;
		TelResponsable = telResponsable;
	}

	public int getId() {
		return id;
	}

	public String getNameEntreprise() {
		return NameEntreprise;
	}

	public void setNameEntreprise(String nameEntreprise) {
		NameEntreprise = nameEntreprise;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getSiteweb() {
		return Siteweb;
	}

	public void setSiteweb(String siteweb) {
		Siteweb = siteweb;
	}

	public String getPays() {
		return Pays;
	}

	public void setPays(String pays) {
		Pays = pays;
	}

	public String getEmailEntreprise() {
		return EmailEntreprise;
	}

	public void setEmailEntreprise(String emailEntreprise) {
		EmailEntreprise = emailEntreprise;
	}

	public String getTelEntreprise() {
		return TelEntreprise;
	}

	public void setTelEntreprise(String telEntreprise) {
		TelEntreprise = telEntreprise;
	}

	public String getEmiailResponsable() {
		return EmiailResponsable;
	}

	public void setEmiailResponsable(String emiailResponsable) {
		EmiailResponsable = emiailResponsable;
	}

	public String getNomPrenomResponsable() {
		return NomPrenomResponsable;
	}

	public void setNomPrenomResponsable(String nomPrenomResponsable) {
		NomPrenomResponsable = nomPrenomResponsable;
	}

	public String getTelResponsable() {
		return TelResponsable;
	}

	public void setTelResponsable(String telResponsable) {
		TelResponsable = telResponsable;
	}
	
	
}
