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
		Soutenance st = em.find(Soutenance.class, s.getId());
		st.setTitre(s.getTitre());
		st.setSalle(s.getSalle());
		st.setNoteSoutenance(s.getNoteSoutenance());
		st.setHeureSoutenance(s.getHeureSoutenance());
		st.setDescription(s.getDescription());
		st.setDateSoutenance(s.getDateSoutenance());
	}

	@Override
	public void deleteSoutenance(int id) {
		// TODO Auto-generated method stub
		Soutenance s = em.find(Soutenance.class, id);	
		em.remove(s);
	}

}
