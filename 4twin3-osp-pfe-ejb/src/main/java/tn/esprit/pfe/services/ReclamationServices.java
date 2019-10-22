package tn.esprit.pfe.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.Reclamation;
import tn.esprit.pfe.interfaces.ReclamationServiceRemote;


@Stateless
@LocalBean
public class ReclamationServices implements ReclamationServiceRemote {

	
	@PersistenceContext
	EntityManager em;
	
	
	public void addReclamation(Reclamation rec) {
	
		em.persist(rec);

		
	}


	@Override
	public void updateReclamation(Reclamation rec) {
		int query = em.createQuery("update Reclamation rec set rec.textRec :text where rec.idReclamation = :id").
				setParameter("text",rec.getTextRec()).setParameter("id",rec.getIdReclamation())
				.executeUpdate();
		
	}


	@Override
	public void deleteReclamation(int id) {
		Reclamation rec = em.find(Reclamation.class, id);
		
			em.remove(rec);
		
	}


	@Override
	public List<Reclamation> getReclamationByEtudiant(int ide) {
		Query query = em.createQuery("select r.textRec from Reclamation r where r.etudiant_id= :ide");
		query.setParameter("ide", ide);
		return query.getResultList();
	}


	
	
	

}
