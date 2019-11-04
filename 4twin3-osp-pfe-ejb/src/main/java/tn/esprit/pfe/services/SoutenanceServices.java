package tn.esprit.pfe.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tn.esprit.pfe.interfaces.SoutenanceServiceRemote;
import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.Message;
import tn.esprit.pfe.entities.Notifications;
import tn.esprit.pfe.entities.Soutenance;
import tn.esprit.pfe.entities.User;

@Stateless
@LocalBean
public class SoutenanceServices implements SoutenanceServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public void addSoutenance(Soutenance s) {
		em.persist(s);
		
	}

	@Override
	public void updateSoutenance(Soutenance s) {
		// TODO Auto-generated method stub
		Soutenance st = em.find(Soutenance.class, s.getId());
		st.setTitre(s.getTitre());
		st.setSalle(s.getSalle());
		st.setNoteSoutenance(s.getNoteSoutenance());
		st.setHeureSoutenance(s.getHeureSoutenance());
		st.setDescription(s.getDescription());
		st.setDateSoutenance(s.getDateSoutenance());
	}

	@Override
	public void deleteSoutenance(int id) {
		// TODO Auto-generated method stub
		Soutenance s = em.find(Soutenance.class, id);	
		em.remove(s);
	}

	
	
	
	
	@Override
	public void testNote(int ids , float notee , float note)
	{
		Soutenance s = em.find(Soutenance.class, ids);
		float not = (notee+note)/2;
		
		
		System.out.println(not);
		if ( (notee - note) <= 3)
		{
			
			s.setId(ids);
			s.setNoteSoutenance(not);
			em.persist(s);
		}
		if ((notee-note) > 3 )
		{
			Notifications n = new Notifications();
		String titre = 	s.getTitre();
			String text = "il ya un conflit dans la note de la soutenance :"+titre;
			n.setText(text);	
		Etudiant u =	em.find(Etudiant.class, 1);
		    n.setUser(u);
		    em.persist(n);
		}
		
	}

	@Override
	public List<Soutenance> afficherSoutenanceNonNote() {
		Query query = em.createQuery("select   s.Titre 	, s.Description , s.dateSoutenance , s.Salle from Soutenance s where s.NoteSoutenance IS NULL ORDER BY s.dateSoutenance desc");
		return query.getResultList();
	}
	
	@Override
	public List<Soutenance> afficherSoutenanceSelonEtudiant(String titre) {
		Query query = em.createQuery("select  s.NoteSoutenance from Soutenance s where s.Titre = :titre").setParameter("titre", titre);
		return query.getResultList();
	}


	
	
	public Map<List<Long>,List<Long>> SoutenanceNoteEtNonNote()
	{
		List<Long> soutenance = em.createQuery("select   count(*)   from Soutenance s where s.NoteSoutenance IS NULL ", Long.class).getResultList();
		List<Long> soutenanceNote = em.createQuery("select   count(*)  from Soutenance s where s.NoteSoutenance IS NOT NULL", Long.class).getResultList();
		Map<List<Long>,List<Long>> maprh=new HashMap<List<Long>,List<Long>>();
		maprh.put(soutenance, soutenanceNote);
		return maprh;
		
		
		
	}
	
	@Override
	public List<Double> MoyenneNote() {
		
		List<Double> soutenance = em.createQuery("select  AVG (s.NoteSoutenance) from Soutenance s where s.NoteSoutenance IS NOT NULL ", Double.class).getResultList();
		return soutenance;
	}
	


}
