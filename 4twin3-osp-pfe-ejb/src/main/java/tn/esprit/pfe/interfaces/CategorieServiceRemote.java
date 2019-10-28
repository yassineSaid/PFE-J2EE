package tn.esprit.pfe.interfaces;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.Categorie;

@Remote
public interface CategorieServiceRemote {
	public void addCategorie(Categorie c);

	public void deleteCategorie(int id);

	public List<Categorie> getAllcategorie();
	
	public List<String>getCategorielesplusdemandées();
	
	
	public void addCategoriecommemodule(Categorie c, String name);
	

}
