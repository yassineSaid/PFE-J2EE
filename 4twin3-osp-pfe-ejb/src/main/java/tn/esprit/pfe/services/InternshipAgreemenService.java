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
   	public int addInternshipAgreemen(InternshipAgreemen internshipAgreemen,int user_id) {
		Etudiant etudiant = em.find(Etudiant.class, user_id);
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
   	public InternshipAgreemen getAgreemenByEtudiant(int user_id) {
		     Etudiant etudiant = em.find(Etudiant.class, user_id);
		     return em.createQuery(
		    		 "select i from InternshipAgreemen i join i.etudiant e where e.id=:etudiantId", 
   					  InternshipAgreemen.class)
		    		 .setParameter("etudiantId", etudiant.getId()).getSingleResult();
   	}

   	@Override
   	public boolean updateInternshipAgreemen(InternshipAgreemen internshipAgreemen) {
   		
		
   		try {
   			
   			em.merge(internshipAgreemen);
			
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

   			if(internshipAgreemen.getPdf().isEmpty()) {
	   			em.remove(internshipAgreemen);

   	   			return true;
   			}else {
   				return false;
   			}
   			
   		} catch (Exception e) {
   			return false;
   		}
   	}

	@Override
	public List<InternshipAgreemen> searchInternshipAgreemen(String email) {
		
		 return em.createQuery(
	    		 "select i from InternshipAgreemen i join i.etudiant e where  e.email LIKE :email", 
					  InternshipAgreemen.class)
	    		 .setParameter("email", "%"+email+"%").getResultList();
	}

	@Override
	public boolean exportPDE(int id) {
		
		  InternshipAgreemen internshipAgreemen = em.find(InternshipAgreemen.class, id);
			
	   		try {
	   			
	   			String filename = new PDF().generateInternshipAgreemen(internshipAgreemen);
	   			internshipAgreemen.setPdf(filename);
	   			em.merge(internshipAgreemen);
				

	   			return true;
	   			

	   		} catch (Exception e) {
	   			e.printStackTrace();
	   			return false;
	   		}
	   		
		
	}
   	
   	

}
