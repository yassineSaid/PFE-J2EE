package tn.esprit.pfe.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
