package tn.esprit.pfe.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pfe.entities.Categorie;
import tn.esprit.pfe.entities.InternshipAgreemen;
import tn.esprit.pfe.entities.Reclamation;
import tn.esprit.pfe.interfaces.CategorieServiceRemote;

@Stateless
@LocalBean
public class CategorieServices implements CategorieServiceRemote{
	@PersistenceContext
	EntityManager em;

	@Override
	public void addCategorie(Categorie c) {
		em.persist(c);
		
	}
	
	@Override
	public void deleteCategorie(int id) {
		Categorie c = em.find(Categorie.class, id);
			em.remove(c);
		
	}
	
	@Override
	public List<Categorie> getAllcategorie() {

		System.out.println("In findAllCategorie : ");
		List<Categorie> c=em.createQuery("from Categorie", Categorie.class).getResultList();
		return c;
	}

	@Override
	public List<String> getCategorielesplusdemandées() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCategoriecommemodule(Categorie c) {
		// TODO Auto-generated method stub
		
	}


}
