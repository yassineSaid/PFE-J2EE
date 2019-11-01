package tn.esprit.pfe.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.google.zxing.WriterException;
import tn.esprit.pfe.email.Email;
import tn.esprit.pfe.entities.Categorie;
import tn.esprit.pfe.entities.Enseignant;
import tn.esprit.pfe.entities.EtatSheetPFE;
import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.RequestCancelInternship;
import tn.esprit.pfe.entities.SheetPFE;
import tn.esprit.pfe.entities.SheetPFEModification;
import tn.esprit.pfe.interfaces.SheetPFERemote;
import tn.esprit.pfe.qrcode.QRCode;

@Stateless
@LocalBean
public class SheetPFEService implements SheetPFERemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public int addSheetPFE(SheetPFE sheetPFE) {

		String code = generateRandomCode();

		Etudiant etudiant = em.find(Etudiant.class, 1);
		sheetPFE.setEtudiant(etudiant);
		sheetPFE.setEtat(EtatSheetPFE.DEFAULT);
		sheetPFE.setQrcode(code);
		
		Date from_year = new Date();
		
		Calendar date = new GregorianCalendar();
		date.setTime(from_year);
		int year = date.get(Calendar.YEAR); 
		int month = date.get(Calendar.MONTH) + 1; 
		
		if(7 <= month && month <= 12) {
			sheetPFE.setFromYear(String.valueOf(year));
			sheetPFE.setToYear(String.valueOf(year + 1));
		}else {
			sheetPFE.setFromYear(String.valueOf(year - 1));
			sheetPFE.setToYear(String.valueOf(year));
		}
	
		em.persist(sheetPFE);

		try {
			new QRCode().writeQRCode(sheetPFE);
			new Email().sendQRCodeSheetPFE(sheetPFE);

		} catch (WriterException | IOException | NamingException | MessagingException e) {
			e.printStackTrace();
		}

		return sheetPFE.getId();

	}
	
	@Override
	public List<Etudiant> getAllStudentNoSheet() {
		return em.createQuery("select e from Etudiant e LEFT JOIN e.sheetPFE s ON s.etudiant.id = e.id where s.etudiant.id IS NULL", Etudiant.class).getResultList();
	}
	
	@Override
	public List<Etudiant> getAllStudentNoSheetWithYear(String startyear , String endyear) {
		return em.createQuery("select e from Etudiant e LEFT JOIN e.sheetPFE s ON s.etudiant.id = e.id where s.etudiant.id IS NULL and s.fromYear = :startyear and s.toYear = :endyear", Etudiant.class)
				.setParameter("startyear" , startyear).setParameter("endyear" , endyear).getResultList();
	}

	@Override
	public List<SheetPFE> getAllSheetPFE() {
		return em.createQuery("select s from SheetPFE s where s.etat = 'REFUSE' or s.etat = 'DEFAULT' ", SheetPFE.class).getResultList();
	}

	@Override
	public SheetPFE getSheetPFEById(int id) {
		SheetPFE sheetPFE = em.find(SheetPFE.class, id);
		if (sheetPFE.getEtat().equals(EtatSheetPFE.DEFAULT)) {
			sheetPFE.setEtat(EtatSheetPFE.VERIFICATION);
			em.merge(sheetPFE);

			try {

				new Email().progressSheetPFE(sheetPFE);

			} catch (NamingException | MessagingException e) {
				e.printStackTrace();
			}
		}
		return em.find(SheetPFE.class, id);
	}

	@Override
	public SheetPFE getSheetPFEByEtudiant() {

		Etudiant etudiant = em.find(Etudiant.class, 4);
		return em.createQuery("select i from SheetPFE i join i.etudiant e where e.id=:etudiantId", SheetPFE.class)
				.setParameter("etudiantId", etudiant.getId()).getSingleResult();
	}

	
	@Override
	public List<SheetPFE> getAllSheetPFEAccepted() {
		return em.createQuery("select s from SheetPFE s where s.etat = 'ACCEPTED' ", SheetPFE.class).getResultList();
	}
	
	@Override
	public boolean updateSheetPFE(SheetPFE sheetPFE) {
		SheetPFE oldsheet = em.find(SheetPFE.class, sheetPFE.getId());
		try {

			if (oldsheet.getEtat().equals(EtatSheetPFE.DEFAULT) || oldsheet.getEtat().equals(EtatSheetPFE.REFUSE)) {
				em.merge(sheetPFE);
			} else {
				
				SheetPFEModification sheetPFEModification = new SheetPFEModification();
				sheetPFEModification.setTitle(sheetPFE.getTitle());
				sheetPFEModification.setDescription(sheetPFE.getDescription());
				sheetPFEModification.setFeatures(sheetPFE.getFeatures());
				sheetPFEModification.setProblematic(sheetPFE.getProblematic());
				sheetPFEModification.setCreated(new Date());
				sheetPFEModification.setSheetPFE(sheetPFE);
				sheetPFEModification.setEtat(EtatSheetPFE.DEFAULT);
				sheetPFEModification.getCategories().addAll(sheetPFE.getCategories());
				sheetPFEModification.setEntreprise(sheetPFE.getEntreprise());

				em.persist(sheetPFEModification);
				
			} 
					
			

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean verificationByDirectorSheetPFE(int sheet_id, EtatSheetPFE etat) {

		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			sheetPFE.setEtat(etat);
			sheetPFE.setNote("Entreprise does not exist");
			em.merge(sheetPFE);
			if (sheetPFE.getEtat().equals(EtatSheetPFE.REFUSE))
				new Email().entrepriseNotExist(sheetPFE);

			return true;

		} catch (Exception e) {
			return false;
		}

	}
	
	@Override
	public List<Enseignant> getAllValidateur() {
		return em.createQuery("select e from Enseignant e where e.role='VALIDATEUR'",Enseignant.class).getResultList();
		
	}
	
	@Override
	public boolean affectValidateurToSheetPFE(int sheet_id) {
		
		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			Enseignant enseignant = getAllValidateur().stream().filter(e -> e.getSheetPFEs().size() == 0 ).findFirst().get();
			sheetPFE.getEnseignant().add(enseignant);
			em.merge(sheetPFE);
			return true;

		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public List<Enseignant> getAllEncadreur() {
		return em.createQuery("select e from Enseignant e where e.role='ENCADREUR'",Enseignant.class).getResultList();
	}

	@Override
	public List<Enseignant> getEncardeurByCategories(int sheet_id) {
		return em.createQuery(
				"select e from Enseignant e join e.categories c join c.sheetPFEs s where s.id=:id and e.role='ENCADREUR' group by(e) order by(count(e)) desc",
				Enseignant.class).setParameter("id", sheet_id).getResultList();
	}

	@Override
	public boolean affectEncadreurToSheetPFEAuto(int sheet_id) {

		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			Enseignant enseignant = getEncardeurByCategories(sheet_id).stream().findFirst().get();
			sheetPFE.getEnseignant().add(enseignant);
			em.merge(sheetPFE);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean affectEncadreurToSheetPFEManual(int sheet_id, int enseignant_id) {

		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			Enseignant enseignant = em.find(Enseignant.class, enseignant_id);
			sheetPFE.getEnseignant().add(enseignant);
			em.merge(sheetPFE);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	@Override
	public int requestCancelInternship(int sheet_id) {
		SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
		RequestCancelInternship req = new RequestCancelInternship();
		req.setCreated(new Date());
		req.setSheetPFE(sheetPFE);
		req.setEtat(EtatSheetPFE.DEFAULT);
		em.persist(req);
		
		return req.getId();
	}

	@Override
	public List<RequestCancelInternship> getAllRequest() {
		return em.createQuery("select r from RequestCancelInternship r where r.etat=:etat",
				RequestCancelInternship.class).setParameter("etat", EtatSheetPFE.DEFAULT).getResultList();

	}

	@Override
	public RequestCancelInternship getResquest(int id) {
		return em.find(RequestCancelInternship.class, id);
	}

	@Override
	public boolean updateRequest(int request_id, EtatSheetPFE etat, String note) {
		try {

			RequestCancelInternship req = em.find(RequestCancelInternship.class, request_id);
			req.setEtat(etat);
			req.setNote(note);
			em.merge(req);
			
			SheetPFE sheetPFE = em.find(SheetPFE.class, req.getSheetPFE().getId());

			
			if(etat.equals(EtatSheetPFE.ACCEPTED)) {
				sheetPFE.setEtat(EtatSheetPFE.CANCEL);
				em.merge(sheetPFE);

			}

			new Email().requestCancelInternship(sheetPFE);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private String generateRandomCode() {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 25) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	@Override
	public List<Integer> statEtatSheetPFE() {
		return em.createQuery(
				"select count(s) as nb from SheetPFE s  group by(s.etat) order by(nb) desc").getResultList();
	}

	
	
	

	

}
