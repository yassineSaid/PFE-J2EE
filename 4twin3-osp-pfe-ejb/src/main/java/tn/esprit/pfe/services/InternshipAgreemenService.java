package tn.esprit.pfe.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.entities.InternshipAgreemen;
import tn.esprit.pfe.interfaces.InternshipAgreemenRemote;

@Stateless
public class InternshipAgreemenService implements InternshipAgreemenRemote {
    @PersistenceContext
    EntityManager em;
    
	@Override
	public int addInternshipAgreemen(InternshipAgreemen internshipAgreemen) {
		em.persist(internshipAgreemen);
		return internshipAgreemen.getId();
	}

	@Override
	public List<InternshipAgreemen> getAllAgreemen() {
		return em.createQuery("select i from InternshipAgreemen i ",InternshipAgreemen.class).getResultList();
	}

	@Override
	public InternshipAgreemen getAgreemenById(int id) {
		return em.find(InternshipAgreemen.class, id);
	}

	@Override
	public InternshipAgreemen getAgreemenByEtudiant(int etudiantId) {
			 TypedQuery<InternshipAgreemen> query = em.createQuery(
					  "select i from InternshipAgreemen i join i.etudiant e where e.id=:etudiantId", 
					  InternshipAgreemen.class);
					  query.setParameter("etudiantId", etudiantId);
					  return query.getSingleResult();
	}

	@Override
	public boolean updateInternshipAgreemen(int id) {
		
		Query query = em.createQuery("update InternshipAgreemen i set etat='ok' where id=:id");
		query.setParameter("id", id);
		int modified = query.executeUpdate();
		if(query.executeUpdate()>0){
			return true;
		}
		return false;
	}

	@Override
	public void removeInternshipAgreemen(int id) {
		em.remove(em.find(InternshipAgreemen.class, id));
	}

}
