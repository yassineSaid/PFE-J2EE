package tn.esprit.pfe.interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.entities.EntrepriseStudent;
import tn.esprit.pfe.entities.EntrepriseSupervisor;
import tn.esprit.pfe.entities.InternshipCataloge;
import tn.esprit.pfe.entities.InternshipOffer;
import tn.esprit.pfe.entities.JobOffer;
import tn.esprit.pfe.entities.ResponsableEntreprise;
import tn.esprit.pfe.entities.User;
import utilities.ValidationError;


@Remote
public interface EntrepriseServiceRemote {
	
	/* Responsable */
	//public Set<ValidationError> addResponsable(ResponsableEntreprise rs);
	//public ResponsableEntreprise login(String email,String password);
	
	/* Entreprise */
	public int addEntreprise(Entreprise ent, int id);
	public List<Entreprise> getAllEntreprise();
	public void addEntreprisetoResponsable(int idR, int idEnt);
	public void updateEntreprise(Entreprise ent);
	public void deleteEntreprise(int id);
	public Entreprise getEntrepriseDetails(int idEnt);
	
	
	/* InternshipOffer */
	public int addInternshipOffer(InternshipOffer inoff);
	public void addInternshipOffertoEntreprise(int idEnt, int idIoffer);
	public void updateInternshipOffer(InternshipOffer inoff);
	public void deleteInternshipOffer(int inoff);
	public InternshipOffer getInternshipOfferDetails(int idioff);
	public void addInternshipOffertoCataloge(int idCat, int idJoffer);

	/* Supervisor */
	public int addSupervisor(EntrepriseSupervisor es);
	public void addSupervisortoEntreprise(int idEnt, int idSuper);
	public void updateSupervisor(EntrepriseSupervisor es);
	public void deleteSupervisor(int ides);
	public EntrepriseSupervisor getEntrepriseSupervisor(int ides);
	
	/* JobOffre */
	public int addJobOffre(JobOffer jo);
	public void addJobOffretoEntreprise(int idEnt, int idJo);
	public void updateJobOffre(JobOffer jo);
	public void deleteJobOffre(int idjo);
	public JobOffer getJobOfferDetails(int idJo);
	public void addJobOffretoCataloge(int idCat, int idJo);
	
	/* InternshipCatalog */
	public int addInternshipCatalog(InternshipCataloge ic);
	public void addInternshipCatalogtoEntreprise(int idEnt, int ic);
	public void updateInternshipCatalog(InternshipCataloge ic);
	public InternshipCataloge getInternshipCatalaogeDetails(int idCat);
	
	/* Student */
	public void affectStudenttoEntreprise(int idEnt, int ids);
	
}
