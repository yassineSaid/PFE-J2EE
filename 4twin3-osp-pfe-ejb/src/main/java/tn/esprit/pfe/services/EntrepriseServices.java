package tn.esprit.pfe.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.entities.EntrepriseStudent;
import tn.esprit.pfe.entities.EntrepriseSupervisor;
import tn.esprit.pfe.entities.InternshipCataloge;
import tn.esprit.pfe.entities.InternshipOffer;
import tn.esprit.pfe.entities.JobOffer;
import tn.esprit.pfe.interfaces.EntrepriseServiceRemote;

@Stateless
@LocalBean
public class EntrepriseServices implements EntrepriseServiceRemote {
	@PersistenceContext(unitName = "4twin3-osp-pfe-ejb")
	EntityManager em;
	
	public Long ValidateMail(String Email) {
		TypedQuery<Long> query = em.createQuery("select count(*) from Entreprise e where e.EmailEntreprise=:emailEnt", Long.class);
		query.setParameter("emailEnt",Email);
		Long nbr=query.getSingleResult();
		return nbr;

	}
	
	@Override
	public int addEntreprise(Entreprise ent) {
		// TODO Auto-generated method stub
		if(ValidateMail(ent.getEmailEntreprise())==0) {
			em.persist(ent);
			return ent.getId();
		}
		return 0;	
			
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
		en.setEmailResponsable(ent.getEmailResponsable());
		en.setNomPrenomResponsable(ent.getNomPrenomResponsable());
		en.setTelResponsable(ent.getTelResponsable());
		
		
	}

	@Override
	public void deleteEntreprise(int id) {
		// TODO Auto-generated method stub
		Entreprise ent = em.find(Entreprise.class, id);	
		em.remove(ent);
	}

	@Override
	public void LoginEntreprise(String email, String Password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInternshipOffer(int idEnt, InternshipOffer inoff) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInternshipOffer(int idEnt, InternshipOffer inoff) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInternshipOffer(int idEnt, InternshipOffer inoff) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSupervisor(int idEnt, EntrepriseSupervisor es) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSupervisor(int idEnt, EntrepriseSupervisor es) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSupervisor(int idEnt, EntrepriseSupervisor es) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addJobOffre(int idEnt, JobOffer jo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateJobOffre(int idEnt, JobOffer jo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteJobOffre(int idEnt, JobOffer jo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInternshipCatalog(int idEnt, InternshipCataloge ic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInternshipCatalog(int idEnt, InternshipCataloge ic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void affectStudent(int idEnt, EntrepriseStudent es) {
		// TODO Auto-generated method stub
		
	}

}
