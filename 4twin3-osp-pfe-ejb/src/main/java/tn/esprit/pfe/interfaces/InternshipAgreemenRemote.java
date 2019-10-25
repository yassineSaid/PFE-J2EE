package tn.esprit.pfe.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.InternshipAgreemen;


@Remote
public interface InternshipAgreemenRemote {

	public int addInternshipAgreemen(InternshipAgreemen internshipAgreemen);
	public List<InternshipAgreemen> getAllAgreemen();
	public InternshipAgreemen getAgreemenById(int id);
	public InternshipAgreemen getAgreemenByEtudiant(int etudiantId);
	public boolean updateInternshipAgreemen(int id);
	public void removeInternshipAgreemen(int id);
	
	
}
