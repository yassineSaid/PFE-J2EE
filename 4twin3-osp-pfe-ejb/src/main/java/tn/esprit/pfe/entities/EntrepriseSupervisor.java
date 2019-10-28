package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "EntrepriseSupervisor")
public class EntrepriseSupervisor implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String NomPrenom;
	
	@Column
	private String Tel;
	
	@Column
	private String Email;
	
	@Column
	private String Specialite;
	
	@Column
	private String SpecialiteOptionnel;

	public int getId() {
		return id;
	}


	public String getNomPrenom() {
		return NomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		NomPrenom = nomPrenom;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSpecialite() {
		return Specialite;
	}

	public void setSpecialite(String specialite) {
		Specialite = specialite;
	}

	public String getSpecialiteOptionnel() {
		return SpecialiteOptionnel;
	}

	public void setSpecialiteOptionnel(String specialiteOptionnel) {
		SpecialiteOptionnel = specialiteOptionnel;
	}


	public EntrepriseSupervisor(int id, String nomPrenom, String tel, String email, String specialite,
			String specialiteOptionnel) {
		super();
		this.id = id;
		NomPrenom = nomPrenom;
		Tel = tel;
		Email = email;
		Specialite = specialite;
		SpecialiteOptionnel = specialiteOptionnel;
	}


	public EntrepriseSupervisor(String nomPrenom, String tel, String email, String specialite,
			String specialiteOptionnel) {
		super();
		NomPrenom = nomPrenom;
		Tel = tel;
		Email = email;
		Specialite = specialite;
		SpecialiteOptionnel = specialiteOptionnel;
	}
	
	public EntrepriseSupervisor() {}
	
}
