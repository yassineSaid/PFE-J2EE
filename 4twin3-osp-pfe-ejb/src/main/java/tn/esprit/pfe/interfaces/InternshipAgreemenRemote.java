package tn.esprit.pfe.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.InternshipAgreemen;


@Remote
public interface InternshipAgreemenRemote {

	public int addInternshipAgreemen(InternshipAgreemen internshipAgreemen);
	public List<InternshipAgreemen> getAllAgreemen();
	
}
