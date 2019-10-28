package tn.esprit.pfe.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.pfe.entities.Categorie;
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
		List<Categorie> cats = em.createQuery("select count(c) from Categorie c,Enseignant e where c.id=:e.id", Categorie.class).getResultList();
		List<String> categorieNames = new ArrayList<>();
		for(Categorie categorie : cats){
			categorieNames.add(categorie.getName());
		}

		return categorieNames;
	}


	@Override
	public void addCategoriecommemodule(Categorie c,String name) {
		int max=0;
		TypedQuery<Long> query = em.createQuery("select count(c) from Categorie c where c.name=name", Long.class);
		if (query.getSingleResult()>max) {
			Categorie cat = em.find(Categorie.class, c.getId());
			cat.setExixtecommemodule(true);
		}			
	}


}
