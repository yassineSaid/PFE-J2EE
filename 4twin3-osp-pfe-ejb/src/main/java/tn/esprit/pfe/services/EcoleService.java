package tn.esprit.pfe.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import tn.esprit.pfe.entities.Admin;
import tn.esprit.pfe.entities.Ecole;
import tn.esprit.pfe.interfaces.EcoleServiceRemote;
import utilities.ValidationError;

@Stateless
@LocalBean
public class EcoleService implements EcoleServiceRemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public Set<ValidationError> addEcole(Ecole e, int idAdmin) {
		Set<ValidationError> errors = new HashSet<>();
		Admin admin = em.find(Admin.class, idAdmin);

		if (admin == null) {
			ValidationError error = new ValidationError();
			error.setClassName("Admin");
			error.setErrorMessage("Cet admin n'éxiste pas");
			error.setPropertyPath("Admin");
			errors.add(error);
			return errors;
		} else {
			try {
				e.setAdmin(admin);
				em.persist(e);
				em.flush();
				admin.setEcole(e);
				return null;
			}catch (ConstraintViolationException ex) {
				Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
				errors.addAll(ValidationError.fromViolations(violations));
				return errors;
			}catch (PersistenceException ex) {
				return errors;
			}
		}
	}

	@Override
	public Set<ValidationError> modifierEcole(Ecole ecole, int idAdmin, int idEcole) {
		Set<ValidationError> errors = new HashSet<>();
		Admin admin = em.find(Admin.class, idAdmin);
		Ecole e = em.find(Ecole.class, idEcole);
		if (admin == null) {
			ValidationError error = new ValidationError();
			error.setClassName("Admin");
			error.setErrorMessage("Cet admin n'éxiste pas");
			error.setPropertyPath("Admin");
			errors.add(error);
			return errors;
		}else if (ecole == null) {
			ValidationError error = new ValidationError();
			error.setClassName("Ecole");
			error.setErrorMessage("Cette école n'éxiste pas");
			error.setPropertyPath("Ecole");
			errors.add(error);
			return errors;
		} else {
			if (e.getAdmin()==admin) {
				e.setAdresse(ecole.getAdresse());
				e.setNom(ecole.getNom());
			}
			else {
				ValidationError error = new ValidationError();
				error.setClassName("Ecole");
				error.setErrorMessage("Cet adminj n'a pas le droit de modifier cette école");
				error.setPropertyPath("Admin");
				errors.add(error);
				return errors;
			}
			return null;
		}
		//return null;
	}

	@Override
	public Set<ValidationError> supprimerEcole(int idEcole, int idAdmin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ecole getEcoleWithIdAdmin(int idAdmin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ecole getEcoleWithIdEcole(int idEcole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ecole> getListEcole() {
		// TODO Auto-generated method stub
		return null;
	}

}
