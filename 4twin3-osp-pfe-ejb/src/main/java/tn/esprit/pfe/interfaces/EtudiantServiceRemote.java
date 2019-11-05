package tn.esprit.pfe.interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Etudiant;
import utilities.ValidationError;

@Remote
public interface EtudiantServiceRemote {
	public Set<ValidationError> addEtudiant(Etudiant e,int idClasse, int idAdmin);
	public Set<ValidationError> modifierEtudiant(Etudiant e,int idEtudiant, int idAdmin);
	public Set<ValidationError> supprimerEtudiant(int idEtudiant, int idAdmin);
	public Etudiant getEtudiant(int idEtudiant, int idAdmin);
	public List<Etudiant> getListEtudiantParDepartement(int idDepartement, int idAdmin);
	public List<Etudiant> getListEtudiantParSite(int idSite, int idAdmin);
	public List<Etudiant> getListEtudiantParEcole(int idEcole, int idAdmin);
}
