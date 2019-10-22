package tn.esprit.pfe.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.interfaces.EntrepriseServiceRemote;

@Stateless
@LocalBean
public class EntrepriseServices implements EntrepriseServiceRemote {
	@PersistenceContext
	EntityManager em;
	@Override
	public void addEntreprise(Entreprise ent) {
		// TODO Auto-generated method stub
			em.persist(ent);
	}

	@Override
	public void updateEntreprise(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEntreprise(int id) {
		// TODO Auto-generated method stub
		Entreprise ent = em.find(Entreprise.class, id);	
		em.remove(ent);
	}

}
