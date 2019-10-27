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
	public void updateSoutenance(Soutenance s) {
		// TODO Auto-generated method stub
		Soutenance en = em.find(Soutenance.class, s.getId());
		s.setTitre(s.getTitre());
		s.setSalle(s.getSalle());
		s.setNoteSoutenance(s.getNoteSoutenance());
		s.setHeureSoutenance(s.getHeureSoutenance());
		s.setDescription(s.getDescription());
		s.setDateSoutenance(s.getDateSoutenance());
	}

	@Override
	public void deleteSoutenance(int id) {
		// TODO Auto-generated method stub
		Soutenance s = em.find(Soutenance.class, id);	
		em.remove(s);
	}

}
