package tn.esprit.pfe.interfaces;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Soutenance;
@Remote
public interface SoutenanceServiceRemote {
	public void addSoutenance(Soutenance s);
	public void updateSoutenance(Soutenance s);
	public void deleteSoutenance(int id);
	
}
