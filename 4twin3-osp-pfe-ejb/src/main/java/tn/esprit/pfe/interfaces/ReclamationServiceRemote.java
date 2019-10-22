package tn.esprit.pfe.interfaces;

import java.util.List;


import tn.esprit.pfe.entities.Reclamation;

public interface ReclamationServiceRemote {

	
	public void addReclamation(Reclamation rec);
	public void updateReclamation(Reclamation rec);
	public void deleteReclamation(int id);
     public List<Reclamation> getReclamationByEtudiant(int ide);
	//ajouter note a soutenance
	//afficher les soutenances non noté + notifier l'enseignant affecte
	//afficher les soutenances qui attend un rapporteur
	//notifier l'etudiant lors de l'ajout d'une note

	
	
}
