package tn.esprit.pfe.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public void updateReclamation(int id, String text) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteReclamation(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<String> getallReclamation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
