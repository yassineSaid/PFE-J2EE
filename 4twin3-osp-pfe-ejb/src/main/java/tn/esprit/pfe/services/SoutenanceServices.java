package tn.esprit.pfe.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.pfe.interfaces.SoutenanceServiceRemote;
import tn.esprit.pfe.entities.Soutenance;

@Stateless
@LocalBean
public class SoutenanceServices implements SoutenanceServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public void addSoutenance(Soutenance s) {
		em.persist(s);
		
	}

	@Override
	public void updateSoutenance(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSoutenance(int id) {
		// TODO Auto-generated method stub
		
	}

}
