package tn.esprit.pfe.interfaces;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Soutenance;
@Remote
public interface SoutenanceServiceRemote {
	public void addSoutenance(Soutenance ent);
	public void updateSoutenance(int id);
	public void deleteSoutenance(int id);
	
}
