package tn.esprit.pfe.interfaces;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Enseignant;

@Remote
public interface EnseignantServiceRemote {
	public void addEnseignant(Enseignant e);

}
