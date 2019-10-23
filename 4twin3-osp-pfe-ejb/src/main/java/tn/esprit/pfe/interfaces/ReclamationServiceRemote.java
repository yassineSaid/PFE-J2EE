package tn.esprit.pfe.interfaces;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import tn.esprit.pfe.entities.Reclamation;


@Remote
public interface ReclamationServiceRemote {

	
	public void addReclamation(Reclamation rec);
	public void updateReclamation(Reclamation rec);
	public void deleteReclamation(int id);
     public List<Reclamation> getReclamationByEtudiant(int ide);
	//ajouter note a soutenance
	//afficher les soutenances non noté + notifier l'enseignant affecte
	//afficher les soutenances qui attend un rapporteur
	//notifier l'etudiant lors de l'ajout d'une note
     //stat note
      
	
	
}
