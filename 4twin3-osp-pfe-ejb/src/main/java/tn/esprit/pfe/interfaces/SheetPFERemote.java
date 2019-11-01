package tn.esprit.pfe.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Categorie;
import tn.esprit.pfe.entities.Enseignant;
import tn.esprit.pfe.entities.EtatSheetPFE;
import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.RequestCancelInternship;
import tn.esprit.pfe.entities.SheetPFE;

@Remote
public interface SheetPFERemote {

	public int addSheetPFE(SheetPFE sheetPFE);
	public List<Etudiant> getAllStudentNoSheet();
	public List<Etudiant> getAllStudentNoSheetWithYear(int year);
	public void reminderStudentNoSheet(List<Etudiant> students);
	public List<SheetPFE> getAllSheetPFEFilter(EtatSheetPFE etat, int year, String pays, int id_categorie);
	public List<SheetPFE> getAllSheetPFEDefault();
	public List<SheetPFE> getAllSheetPFEAccepted();
	public SheetPFE getSheetPFEById(int id);
	public SheetPFE getSheetPFEByEtudiant();
	public boolean updateSheetPFE(SheetPFE sheetPFE);
	public boolean verificationByDirectorSheetPFE(int sheet_id,EtatSheetPFE etat);
	public int requestCancelInternship(int sheet_id);
	public List<RequestCancelInternship> getAllRequest();
	public RequestCancelInternship getResquest(int id);
	public boolean updateRequest(int request_id, EtatSheetPFE etat, String note);
	public List<Integer> statEtatSheetPFE();
	public List<SheetPFE> getAllSheetWaitEncadreur();
	public List<SheetPFE> getAllSheetWaitRapporter();
	public List<SheetPFE> getAllSheetValidate();
	public List<Enseignant> getAllValidateur();
	public boolean affectValidateurToSheetPFE(int sheet_id);
	
	public List<Enseignant> getAllEncadreur();
	public List<Enseignant> getEncardeurByCategories(int sheet_id);
	public boolean affectEncadreurToSheetPFEAuto(int sheet_id);
	public boolean affectEncadreurToSheetPFEManual(int sheet_id,int enseignant_id);

}
