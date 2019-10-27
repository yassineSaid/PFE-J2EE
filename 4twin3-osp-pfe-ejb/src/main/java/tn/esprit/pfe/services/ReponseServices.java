package tn.esprit.pfe.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pfe.entities.ForumReponse;
import tn.esprit.pfe.interfaces.ReponseServiceRemote;

@Stateless
@LocalBean
public class ReponseServices implements ReponseServiceRemote {
	@PersistenceContext
	EntityManager em;
	@Override
	public void addReponse(ForumReponse reponse) {
		em.persist(reponse);
		
	}

	@Override
	public void MettreajourReponse(int id_Reponse,String Conetnu_Reponse) {
		ForumReponse reponse = em.find(ForumReponse.class, id_Reponse);
		reponse.setConetnu_Reponse(Conetnu_Reponse);
		
	}

}
