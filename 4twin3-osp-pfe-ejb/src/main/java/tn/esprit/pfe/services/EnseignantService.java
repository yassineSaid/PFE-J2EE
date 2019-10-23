package tn.esprit.pfe.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pfe.entities.Enseignant;
import tn.esprit.pfe.interfaces.EnseignantServiceRemote;
@Stateless
@LocalBean
public class EnseignantService implements EnseignantServiceRemote{
	@PersistenceContext
	EntityManager em;

	@Override
	public void addEnseignant(Enseignant e) {
		em.persist(e);
	}

	

}
