package tn.esprit.pfe.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table
public class Enseignant extends User {
	@OneToMany(mappedBy="enseignant",cascade = CascadeType.ALL, 
			fetch=FetchType.EAGER)
	
	//private List<Categorie> c=new ArrayList<>();
	
	
	private static final long serialVersionUID = 1L;
	@Column(nullable=false)
	private boolean enabled;
	
	 

	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enseignant(String lastname, String firstname, String email, String password) {
		super(lastname, firstname, email, password);
		this.enabled=false;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	
}
