package tn.esprit.pfe.interfaces;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.EtatSheetPFE;
import tn.esprit.pfe.entities.SheetPFE;

@Remote
public interface SheetPFERemote {

	public int addSheetPFE(SheetPFE sheetPFE);
	public SheetPFE getSheetPFEById(int id);
	public SheetPFE getSheetPFE();
	public boolean updateSheetPFE(SheetPFE sheetPFE);
	public boolean validateSheetPFE(SheetPFE sheetPFE);
}
