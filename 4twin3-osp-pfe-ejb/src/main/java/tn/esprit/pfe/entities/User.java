package tn.esprit.pfe.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import utilities.BCrypt;

@Entity
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(columnNames = "email", name = "uniqueEmailConstraint") })
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	private String lastname;

	@Column
	private String firstname;

	@Column(nullable = false, unique = true)
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+ "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Cet email est invalide")
	private String email;

	@Column
	private String password;

	@Length(min=8, message="Le mot de passe doit contenir un minimum de 8 caractères")
	@NotNull
	@Transient
	private String plainPassword;

	public User() {
		super();
	}

	public User(String lastname, String firstname, String email, String plainPassword) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.plainPassword = plainPassword;
		this.password = this.createPwd(plainPassword);
	}

	public int getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = this.createPwd(password);
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String createPwd(String password) {
		String newPass;
		newPass = BCrypt.hashpw(password, BCrypt.gensalt());
		return newPass;
	}

}
