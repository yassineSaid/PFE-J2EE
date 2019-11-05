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
	public int addCategorie(Categorie c) {
		em.persist(c);
		return c.getId();

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
	public List<Categorie> getCategorielesplusdemandées(int idens) {
		List<Categorie> cat = em.createQuery("selectT c from Categorie c left join Enseignant e where c.e.id=idens ", Categorie.class).getResultList();
		return cat;
		/*List<String> categorieNames = new ArrayList<>();
		for(Categorie categorie : cat){
			categorieNames.add(categorie.getName());
		}*/

		//return ls;
		//return categorieNames;
	}

	@Override
	public void addCategoriecommemodule(String name) {
		int var=1;
		TypedQuery<Categorie> query  =em.createQuery("select c from Categorie c where c.name=name",Categorie.class);
		List<Categorie>ls=new ArrayList<>();
		ls=query.getResultList();
		if (ls.size() >var) {
			List<Categorie> cats = em.createQuery("select c from Categorie c ", Categorie.class).getResultList();
			for(Categorie categorie : cats){
				categorie.setExixtecommemodule(true);
			}
		}	else {System.out.println("la categorie n'est plus utliseé pour etre module");		}
	}


}
