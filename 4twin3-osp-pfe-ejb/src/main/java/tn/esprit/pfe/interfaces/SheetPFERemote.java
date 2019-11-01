package tn.esprit.pfe.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.RequestCancelInternship;
import tn.esprit.pfe.entities.SheetPFE;

@Remote
public interface SheetPFERemote {

	public int addSheetPFE(SheetPFE sheetPFE);
	public List<SheetPFE> getAllSheetPFE();
	public SheetPFE getSheetPFEById(int id);
	public SheetPFE getSheetPFEByEtudiant();
	public boolean updateSheetPFE(SheetPFE sheetPFE);
	public boolean validateSheetPFE(SheetPFE sheetPFE);
	public void affectSheetEnseignantToSheetPFEAuto(int id);
	public void affectSheetEnseignantToSheetPFEManual(int sheet_id,int enseignant_id);
	public int requestCancelInternship(int sheet_id);
	public List<RequestCancelInternship> getAllRequest();
	public RequestCancelInternship getResquest(int id);
	public boolean updateRequest(RequestCancelInternship request);
}
