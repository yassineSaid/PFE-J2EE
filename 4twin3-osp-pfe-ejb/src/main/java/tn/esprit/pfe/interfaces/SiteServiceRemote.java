package tn.esprit.pfe.interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Site;
import utilities.ValidationError;

@Remote
public interface SiteServiceRemote {
	public Set<ValidationError> addSite(Site s,int idAdmin);
	public Set<ValidationError> modifierSite(Site s, int idAdmin, int idSite);
	public Set<ValidationError> supprimerSite(int idSite, int idAdmin);
	Site getSite(int idSite);
	public Set<Site> getListSite(int idEcole);
}
