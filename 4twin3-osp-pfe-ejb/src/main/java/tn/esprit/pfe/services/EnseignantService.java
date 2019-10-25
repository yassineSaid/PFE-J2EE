package tn.esprit.pfe.services;

import java.util.Set;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import tn.esprit.pfe.entities.Enseignant;
import tn.esprit.pfe.interfaces.EnseignantServiceRemote;
@Stateless
@LocalBean
public class EnseignantService implements EnseignantServiceRemote{
	@PersistenceContext(unitName = "4twin3-osp-pfe-ejb")
	EntityManager em;

	@Override
	public void addEnseignant(Enseignant e) {
		
		try {
			em.persist(e);
		} catch (Exception re) {
			System.out.println(re);
			Set<ConstraintViolation<?>> violations = ((ConstraintViolationException)re.getCause()).getConstraintViolations();
			for (ConstraintViolation v : violations) {
				System.out.println(v);
			}
		}
		
	}



}
