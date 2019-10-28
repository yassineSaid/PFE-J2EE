package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Reponse")
public class ForumReponse implements Serializable{
	private static final long serialVersionUID = 1;
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column
	private int id_Reponse; 
	@Column
	private String Conetnu_Reponse;
	
	
	@ManyToOne
	private ForumQuestion forumquestion;
	
	
	
	public ForumReponse() {
		super();
	}
	public ForumReponse( String conetnu_Reponse) {
		super();
		
		Conetnu_Reponse = conetnu_Reponse;
	}
	
	public String getConetnu_Reponse() {
		return Conetnu_Reponse;
	}
	public void setConetnu_Reponse(String conetnu_Reponse) {
		Conetnu_Reponse = conetnu_Reponse;
	} 
	
	
	
	
}