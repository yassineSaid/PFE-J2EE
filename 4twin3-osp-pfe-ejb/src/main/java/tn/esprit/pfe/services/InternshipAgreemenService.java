package tn.esprit.pfe.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.pfe.email.Email;
//import tn.esprit.pfe.email.Email;
import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.InternshipAgreemen;
import tn.esprit.pfe.interfaces.InternshipAgreemenRemote;
import tn.esprit.pfe.pdf.PDF;

@Stateless
public class InternshipAgreemenService implements InternshipAgreemenRemote {
    @PersistenceContext
    EntityManager em;
    
    @Override
   	public int addInternshipAgreemen(InternshipAgreemen internshipAgreemen) {
		Etudiant etudiant = em.find(Etudiant.class, 1);
		internshipAgreemen.setEtudiant(etudiant);
   		em.persist(internshipAgreemen);
   		return internshipAgreemen.getId();
   	}

   	@Override
   	public List<InternshipAgreemen> getAllAgreemen() {
   		return em.createQuery("select i from InternshipAgreemen i",InternshipAgreemen.class).getResultList();
   	}

   	@Override
   	public InternshipAgreemen getAgreemenById(int id) {
   		return em.find(InternshipAgreemen.class, id);
   	}

   	@Override
   	public InternshipAgreemen getAgreemenByEtudiant() {
		     Etudiant etudiant = em.find(Etudiant.class, 1);
		     return em.createQuery(
		    		 "select i from InternshipAgreemen i join i.etudiant e where e.id=:etudiantId", 
   					  InternshipAgreemen.class)
		    		 .setParameter("etudiantId", etudiant.getId()).getSingleResult();
   	}

   	@Override
   	public boolean updateInternshipAgreemen(int id) {
   		
   		  InternshipAgreemen internshipAgreemen = em.find(InternshipAgreemen.class, id);
		
   		try {
   			
   			String filename = new PDF().generateInternshipAgreemen(internshipAgreemen);
   			internshipAgreemen.setPdf(filename);
   			em.merge(internshipAgreemen);
   			new Email().sendAgreemen(internshipAgreemen);
			

   			return true;
   			

   		} catch (Exception e) {
   			e.printStackTrace();
   			return false;
   		}
   		

   		
   	}

   	@Override
   	public boolean removeInternshipAgreemen(int id) {
   		
   		InternshipAgreemen internshipAgreemen = em.find(InternshipAgreemen.class,id);
   		
   		try {
   			em.remove(internshipAgreemen);
   			return true;
   		} catch (Exception e) {
   			return false;
   		}
   	}

}
