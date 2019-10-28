package tn.esprit.pfe.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name="Question")
public class ForumQuestion implements Serializable{
	private static final long serialVersionUID = 1;


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private int id_Question; 
	@Column
	private String Conetnu_Question; 	
	@Column
	private boolean Question_resolu;
	
	
	
	
	@OneToMany(mappedBy="forumquestion", cascade = {CascadeType.ALL}, 
			fetch=FetchType.EAGER)
	private List<ForumReponse> ForumReponse = new ArrayList<>();
	
	public ForumQuestion() {
		super();
	}
	public ForumQuestion(int id_Question, String conetnu_Question, boolean question_resolu) {
		super();
		this.id_Question = id_Question;
		Conetnu_Question = conetnu_Question;
		Question_resolu = question_resolu;
	}
	public int getId_Question() {
		return id_Question;
	}
	public void setId_Question(int id_Question) {
		this.id_Question = id_Question;
	}
	public String getConetnu_Question() {
		return Conetnu_Question;
	}
	public void setConetnu_Question(String conetnu_Question) {
		Conetnu_Question = conetnu_Question;
	}
	public boolean isQuestion_resolu() {
		return Question_resolu;
	}
	public void setQuestion_resolu(boolean question_resolu) {
		Question_resolu = question_resolu;
	}
	
	
	
	
	
	
	

	
	}
