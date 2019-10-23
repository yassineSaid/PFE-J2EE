package tn.esprit.pfe.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		return em.createQuery("select i from InternshipAgreemen i", InternshipAgreemen.class).getResultList();
	}

}
