package tn.esprit.pfe.services;

import java.util.Date;

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
			ent.setXp(0);
			em.persist(ent);
			return ent.getId();
		}
		return 0;	
			
	}

	@Override
	public void updateEntreprise(Entreprise ent) {
		// TODO Auto-generated method stub
		Entreprise en = em.find(Entreprise.class, ent.getId());
		en.setAdresse(ent.getAdresse());
		en.setEmailEntreprise(ent.getEmailEntreprise());
		en.setEmailResponsable(ent.getEmailResponsable());
		en.setNameEntreprise(ent.getNameEntreprise());
		en.setNomPrenomResponsable(ent.getNomPrenomResponsable());
		en.setPassword(ent.getPassword());
		en.setPays(ent.getPays());
		en.setSiteweb(ent.getSiteweb());	
		en.setTelEntreprise(ent.getTelEntreprise());		
		en.setTelResponsable(ent.getTelResponsable());
		
		
	}

	@Override
	public void deleteEntreprise(int id) {
		// TODO Auto-generated method stub
		Entreprise ent = em.find(Entreprise.class, id);	
		em.remove(ent);
	}

	@Override
	public Entreprise getEntrepriseDetails(int idEnt) {
		// TODO Auto-generated method stub
		return em.find(Entreprise.class, idEnt);
	
	}

	@Override
	public int addInternshipOffer(InternshipOffer inoff) {
		// TODO Auto-generated method stub
		Date date = new Date();
		inoff.setDatePublier(date);
		em.persist(inoff);
		
		return inoff.getId();
		
	}
	
	@Override
	public void addInternshipOffertoEntreprise(int idEnt, int idIoffer) {
		// TODO Auto-generated method stub
		Entreprise ide = em.find(Entreprise.class, idEnt);
		
		InternshipOffer ioffer = em.find(InternshipOffer.class, idIoffer);
		ioffer.setEntreprise(ide);
	}

	@Override
	public void updateInternshipOffer(InternshipOffer inoff) {
		// TODO Auto-generated method stub
		InternshipOffer io = em.find(InternshipOffer.class, inoff.getId());
		io.setOffreName(inoff.getOffreName());
		io.setCompétencesetConnaissances(inoff.getCompétencesetConnaissances());
		io.setDescription(inoff.getDescription());
		io.setMission(inoff.getMission());
		io.setObjectifdustage(inoff.getObjectifdustage());
		io.setProfilrecherche(inoff.getProfilrecherche());
		io.setReference(inoff.getReference());
		io.setSujet(inoff.getSujet());
		io.setDateDebut(inoff.getDateDebut());
		io.setDateFin(inoff.getDateFin());
		
	}

	@Override
	public int addJobOffre(JobOffer jo) {
		// TODO Auto-generated method stub
		Date date = new Date();
		jo.setDatePublier(date);
		em.persist(jo);		
		return jo.getId();
		
	}

	@Override
	public void addEntreprisetoResponsable(int idR, int idEnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInternshipOffer(InternshipOffer inoff) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addSupervisor(EntrepriseSupervisor es) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addSupervisortoEntreprise(int idEnt, int idSuper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSupervisor(EntrepriseSupervisor es) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSupervisor(EntrepriseSupervisor es) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addJobOffretoEntreprise(int idEnt, int idJo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateJobOffre(JobOffer jo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteJobOffre(JobOffer jo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInternshipCatalog(InternshipCataloge ic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInternshipCatalogtoEntreprise(int idEnt, int ic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInternshipCatalog(InternshipCataloge ic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void affectStudenttoEntreprise(int idEnt, int ids) {
		// TODO Auto-generated method stub
		
	}

}
