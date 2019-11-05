package tn.esprit.pfe.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pfe.entities.Admin;
import tn.esprit.pfe.entities.Enseignant;
import tn.esprit.pfe.entities.User;
import tn.esprit.pfe.interfaces.EnseignantServiceRemote;
import utilities.ValidationError;
@Stateless
@LocalBean
public class EnseignantService implements EnseignantServiceRemote{
	@PersistenceContext(unitName = "4twin3-osp-pfe-ejb")
	EntityManager em;
	
	@EJB
	UserService us;

	@Override
	public Set<ValidationError> addEnseignant(Enseignant e, int idAdmin) {
		Set<ValidationError> errors = new HashSet<>();
		Admin admin = em.find(Admin.class, idAdmin);
		if (admin == null) {
			ValidationError error = new ValidationError();
			error.setClassName("Admin");
			error.setErrorMessage("Cet admin n'éxiste pas");
			error.setPropertyPath("Admin");
			errors.add(error);
			return errors;
		}
		else if (admin.getEcole()==null) {
			ValidationError error = new ValidationError();
			error.setClassName("Ecole");
			error.setErrorMessage("Cet admin n'a pas encore d'école");
			error.setPropertyPath("Admin");
			errors.add(error);
			return errors;
		}
		else {
			e.setEcole(admin.getEcole());
			return us.addUser(e);
		}
	}

	@Override
	public Set<Enseignant> getListEnseignant(int idUser) {
		User user=em.find(User.class, idUser);
		if (user==null) {
			return null;
		} else if (user.getRole().equals("Admin")){
			Admin admin=(Admin) user;
			return admin.getEcole().getEnseignants();
		} else {
			return null;
		}
	}

	@Override
	public Set<ValidationError> supprimerEnseignant(int idEnseignant, int idAdmin) {
		Set<ValidationError> errors = new HashSet<>();
		Admin admin = em.find(Admin.class, idAdmin);
		Enseignant enseignant = em.find(Enseignant.class, idEnseignant);
		if (admin == null) {
			ValidationError error = new ValidationError();
			error.setClassName("Admin");
			error.setErrorMessage("Cet admin n'éxiste pas");
			error.setPropertyPath("Admin");
			errors.add(error);
			return errors;
		} else if (enseignant == null) {
			ValidationError error = new ValidationError();
			error.setClassName("Enseignant");
			error.setErrorMessage("Cet enseignant n'éxiste pas");
			error.setPropertyPath("Enseignant");
			errors.add(error);
			return errors;
		} else if (enseignant.getEcole() != admin.getEcole()) {
			ValidationError error = new ValidationError();
			error.setClassName("Enseignant");
			error.setErrorMessage("Cet enseignant ne fait pas partie de votre école");
			error.setPropertyPath("Enseignant");
			errors.add(error);
			return errors;
		} else {
			em.createQuery("delete from User u where u.id=:id").setParameter("id", idEnseignant).executeUpdate();
			em.flush();
		}
		return null;
	}



}
