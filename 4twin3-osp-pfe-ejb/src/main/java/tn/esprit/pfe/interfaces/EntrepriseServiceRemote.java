package tn.esprit.pfe.interfaces;

import tn.esprit.pfe.entities.Entreprise;;

public interface EntrepriseServiceRemote {
	
	public void addEntreprise(Entreprise ent);
	public void updateEntreprise(int id);
	public void deleteEntreprise(int id);
	
}
