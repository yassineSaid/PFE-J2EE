package tn.esprit.pfe.interfaces;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.ForumReponse;

@Remote
public interface ReponseServiceRemote {
	public void addReponse(ForumReponse reponse);
	public void MettreajourReponse(int id_Reponse, String Conetnu_Reponse);

}
