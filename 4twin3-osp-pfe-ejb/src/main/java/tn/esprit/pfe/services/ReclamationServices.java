package tn.esprit.pfe.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.Reclamation;
import tn.esprit.pfe.entities.User;
import tn.esprit.pfe.interfaces.ReclamationServiceRemote;


@Stateless
@LocalBean
public class ReclamationServices implements ReclamationServiceRemote {

	
	@PersistenceContext
	EntityManager em;
	
	
	public int addReclamation(Reclamation rec) {
	
	
		em.persist(rec);
		return rec.getIdReclamation();

		
	}
	

	@Override
	public void updateReclamation(Reclamation rec) {
		try
		{
			int query = em.createQuery("update Reclamation rec set rec.textRec =  :text where rec.idReclamation = :id").
					setParameter("text",rec.getTextRec()).setParameter("id",rec.getIdReclamation())
					.executeUpdate();
		}
		catch (Exception e )
		{
			System.out.println(e);
		}
	
		
	}


	@Override
	public void deleteReclamation(int idReclamation) {
		
		try {
		Reclamation r = em.find(Reclamation.class, idReclamation);
		System.out.println(r);
		em.remove(r);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}


	@Override
	public List<Reclamation> getAllReclamation() {
		Query query = em.createQuery("select   r.dateAjout ,r.textRec ,r.etudiant.nom , r.etudiant.prenom ,  r.soutenance.Description  from Reclamation r ORDER BY r.dateAjout desc");
		return query.getResultList();
	}
	
	@Override
	public List<Reclamation> getReclamationByEtudiant(String nom , String prenom ) {
		Query query = em.createQuery("select  r.textRec , r.dateAjout, r.soutenance.Titre from Reclamation r where r.etudiant.nom = :nom AND r.etudiant.prenom = :prenom ").setParameter("nom",nom)
				.setParameter("prenom", prenom);
		return query.getResultList();
	}
	
	
	
	@Override
	public List<Reclamation> getReclamationBySoutenance(int id ) {
		Query query = em.createQuery("select  r.textRec , r.dateAjout, r.soutenance.Titre from Reclamation r where r.etudiant.id = :id ").setParameter("id",id);
		return query.getResultList();
	}
	
	
	//stat
	
	@Override
	public List<Object[]> nombreDeReclamationSelonDateAjout() {
		// TODO Auto-generated method stub
		List<Object[]> reclamation = em.createQuery("select  dateAjout , count(*)  from Reclamation group by  dateAjout", Object[].class).getResultList();
		return reclamation;
	}
	
	@Override
	public List<Object[]> nombreDeReclamationParMois() {
		// TODO Auto-generated method stub
		List<Object[]> reclamation = em.createQuery("select  dateAjout , count(*)  from Reclamation group by Month (dateAjout) ", Object[].class).getResultList();
		return reclamation;
	}
	
   
	


	
	
	

}
