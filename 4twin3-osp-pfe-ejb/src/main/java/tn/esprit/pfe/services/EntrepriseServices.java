package tn.esprit.pfe.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.interfaces.EntrepriseServiceRemote;

@Stateless
@LocalBean
public class EntrepriseServices implements EntrepriseServiceRemote {
	@PersistenceContext(unitName = "4twin3-osp-pfe-ejb")
	EntityManager em;
	@Override
	public int addEntreprise(Entreprise ent) {
		// TODO Auto-generated method stub
			em.persist(ent);
			return ent.getId();
			
	}

	@Override
	public void updateEntreprise(Entreprise ent) {
		// TODO Auto-generated method stub
		Entreprise en = em.find(Entreprise.class, ent.getId());
		en.setNameEntreprise(ent.getNameEntreprise());
		en.setAdresse(ent.getAdresse());
		en.setSiteweb(ent.getSiteweb());
		en.setPays(ent.getPays());
		en.setEmailEntreprise(ent.getEmailEntreprise());
		en.setTelEntreprise(ent.getTelEntreprise());
		en.setEmiailResponsable(ent.getEmiailResponsable());
		en.setNomPrenomResponsable(ent.getNomPrenomResponsable());
		en.setTelResponsable(ent.getTelResponsable());
		
		
	}

	@Override
	public void deleteEntreprise(int id) {
		// TODO Auto-generated method stub
		Entreprise ent = em.find(Entreprise.class, id);	
		em.remove(ent);
	}

}
