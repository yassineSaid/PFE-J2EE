package tn.esprit.pfe.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.InternshipAgreemen;


@Remote
public interface InternshipAgreemenRemote {

	public int addInternshipAgreemen(InternshipAgreemen internshipAgreemen,int user_id);
	public List<InternshipAgreemen> getAllAgreemen();
	public InternshipAgreemen getAgreemenById(int id);
	public InternshipAgreemen getAgreemenByEtudiant(int user_id);
	public boolean updateInternshipAgreemen(InternshipAgreemen internshipAgreemen);
	public boolean removeInternshipAgreemen(int id);
	public List<InternshipAgreemen> searchInternshipAgreemen(String email);
	public boolean exportPDE(int id);
	
}
