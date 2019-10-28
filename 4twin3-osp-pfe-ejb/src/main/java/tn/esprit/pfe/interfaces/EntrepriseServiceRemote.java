package tn.esprit.pfe.interfaces;

import javax.ejb.Remote;
import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.entities.EntrepriseStudent;
import tn.esprit.pfe.entities.EntrepriseSupervisor;
import tn.esprit.pfe.entities.InternshipCataloge;
import tn.esprit.pfe.entities.InternshipOffer;
import tn.esprit.pfe.entities.JobOffer;


@Remote
public interface EntrepriseServiceRemote {
	
	public int addEntreprise(Entreprise ent);
	public void updateEntreprise(Entreprise ent);
	public void deleteEntreprise(int id);
	public void LoginEntreprise(String email, String Password);
	public void addInternshipOffer(int idEnt,InternshipOffer inoff);
	public void updateInternshipOffer(int idEnt,InternshipOffer inoff);
	public void deleteInternshipOffer(int idEnt,InternshipOffer inoff);
	public void addSupervisor(int idEnt,EntrepriseSupervisor es);
	public void updateSupervisor(int idEnt,EntrepriseSupervisor es);
	public void deleteSupervisor(int idEnt,EntrepriseSupervisor es);
	public void addJobOffre(int idEnt,JobOffer jo);
	public void updateJobOffre(int idEnt,JobOffer jo);
	public void deleteJobOffre(int idEnt,JobOffer jo);
	public void addInternshipCatalog(int idEnt, InternshipCataloge ic);
	public void updateInternshipCatalog(int idEnt, InternshipCataloge ic);
	public void affectStudent(int idEnt, EntrepriseStudent es);
	
}
