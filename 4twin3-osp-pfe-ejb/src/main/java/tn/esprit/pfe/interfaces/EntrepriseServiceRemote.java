package tn.esprit.pfe.interfaces;

import javax.ejb.Remote;
import tn.esprit.pfe.entities.Entreprise;

@Remote
public interface EntrepriseServiceRemote {
	
	public int addEntreprise(Entreprise ent);
	public void updateEntreprise(Entreprise ent);
	public void deleteEntreprise(int id);
	
}
