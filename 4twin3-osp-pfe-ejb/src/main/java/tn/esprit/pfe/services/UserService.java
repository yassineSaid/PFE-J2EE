package tn.esprit.pfe.services;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import tn.esprit.pfe.entities.User;
import tn.esprit.pfe.interfaces.UserServiceRemote;
import utilities.BCrypt;
import utilities.ValidationError;
@Stateless
@LocalBean
public class UserService implements UserServiceRemote{
	@PersistenceContext
	EntityManager em;

	@Override
	public Set<ValidationError> addUser(User u) {
		Set<ValidationError> errors=new HashSet<>();
		if ((long) em.createQuery("select count(u) from User u where u.email=:email").setParameter("email", u.getEmail()).getSingleResult()>0) {
			ValidationError error=new ValidationError();
			error.setClassName(u.getClass().getSimpleName());
			error.setErrorMessage("Cet Email éxiste déjà");
			error.setPropertyPath("email");
			errors.add(error);
		}
		u.setPassword(u.getPlainPassword());
		u.setRole(u.getClass().getSimpleName());
		System.out.println();
		if (u.getRole().equals("SuperAdmin") && (long) em.createQuery("select count(u) from User u where u.role='SuperAdmin'").getSingleResult()>0) {
			ValidationError error=new ValidationError();
			error.setClassName(u.getClass().getSimpleName());
			error.setErrorMessage("Il ne peut y avoir qu'un seul super administrateur");
			error.setPropertyPath("SuperAdmin");
			errors.add(error);
			return errors;
		}
		try {
			em.persist(u);
			em.flush();
			return null;
		}catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			errors.addAll(ValidationError.fromViolations(violations));
			return errors;
		}catch (PersistenceException e) {
			return errors;
		}
	}

	@Override
	public User login(String email, String password) {
		User u=(User) em.createQuery("select u from User u where email=:email").setParameter("email", email).getSingleResult();
		if (BCrypt.checkpw(password, u.getPassword())) return u;
		else return null;
	}



}
