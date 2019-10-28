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
import tn.esprit.pfe.entities.Soutenance;

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
		Soutenance en = em.find(Soutenance.class, s.getId());
		s.setTitre(s.getTitre());
		s.setSalle(s.getSalle());
		s.setNoteSoutenance(s.getNoteSoutenance());
		s.setHeureSoutenance(s.getHeureSoutenance());
		s.setDescription(s.getDescription());
		s.setDateSoutenance(s.getDateSoutenance());
	}

	@Override
	public void deleteSoutenance(int id) {
		// TODO Auto-generated method stub
		Soutenance s = em.find(Soutenance.class, id);	
		em.remove(s);
	}

	@Override
	public void ajouterNote(Soutenance so) {
		try
		{
			int query = em.createQuery("update Soutenance so set so.NoteSoutenance =  :note  where so.id = :id").
					setParameter("note",so.getNoteSoutenance()).setParameter("id",so.getId())
					.executeUpdate();
		}
		catch (Exception e )
		{
			System.out.println(e);
		}
	
		
	}

	@Override
	public List<Soutenance> afficherSoutenanceNonNote() {
		Query query = em.createQuery("select   s.Titre 	, s.Description , s.dateSoutenance , s.Salle from Soutenance s where s.NoteSoutenance IS NULL ORDER BY s.dateSoutenance desc");
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
