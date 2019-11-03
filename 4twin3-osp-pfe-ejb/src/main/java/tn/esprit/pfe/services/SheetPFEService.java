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
import javax.persistence.Query;

import com.google.zxing.WriterException;
import tn.esprit.pfe.email.Email;
import tn.esprit.pfe.entities.Categorie;
import tn.esprit.pfe.entities.Enseignant;
import tn.esprit.pfe.entities.EnseignantSheetPFE;
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
		return em.createQuery(
				"select e from Etudiant e LEFT JOIN e.sheetPFE s ON s.etudiant.id = e.id where s.etudiant.id IS NULL",
				Etudiant.class).getResultList();
	}

	@Override
	public List<Etudiant> getAllStudentNoSheetWithYear(int startyear, int toyear) {

		if (toyear == 0) {

			return em.createQuery(
					"select e from Etudiant e LEFT JOIN e.sheetPFE s ON s.etudiant.id = e.id where e.classe.anneeDeDebut = :year   and s.etudiant.id IS NULL ",
					Etudiant.class).setParameter("year", startyear).getResultList();
		} else {

			return em.createQuery(
					"select e from Etudiant e LEFT JOIN e.sheetPFE s ON s.etudiant.id = e.id where e.classe.anneeDeDebut BETWEEN :startyear and :toyear   and s.etudiant.id IS NULL ",
					Etudiant.class).setParameter("startyear", startyear).setParameter("toyear", toyear).getResultList();
		}

	}

	@Override
	public void reminderStudentNoSheet(List<Etudiant> students) {

		for (Etudiant etudiant : students) {
			try {
				new Email().reminderStudent(etudiant);
			} catch (NamingException | MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<SheetPFE> getAllSheetPFEFilter(EtatSheetPFE etat, int year, String pays, int id_categorie) {

		if (etat.equals(EtatSheetPFE.ALL)) {

			if (year == 0) {

				if (pays.equals("ALL")) {

					if (id_categorie == 0) {

						return em.createQuery("select s from SheetPFE s", SheetPFE.class).getResultList();

					} else {

						return em.createQuery("select s from SheetPFE s join s.categories c where c.id= :idcategorie",
								SheetPFE.class).setParameter("idcategorie", id_categorie).getResultList();
					}

				} else {

					if (id_categorie == 0) {

						return em.createQuery("select s from SheetPFE s join s.entreprise p where p.Pays= :pays",
								SheetPFE.class).setParameter("pays", pays).getResultList();

					} else {

						return em.createQuery(
								"select s from SheetPFE s join s.entreprise p join s.categories c where p.Pays= :pays and c.id= :idcategorie",
								SheetPFE.class).setParameter("pays", pays).setParameter("idcategorie", id_categorie)
								.getResultList();
					}

				}

			} else {

				if (pays.equals("ALL")) {

					if (id_categorie == 0) {

						return em.createQuery(
								"select s from SheetPFE s join s.etudiant e where e.classe.anneeDeDebut = :year",
								SheetPFE.class).setParameter("year", year).getResultList();

					} else {

						return em.createQuery(
								"select s from SheetPFE s join s.etudiant e join s.categories c where c.id= :idcategorie  and e.classe.anneeDeDebut = :year",
								SheetPFE.class).setParameter("year", year).setParameter("idcategorie", id_categorie)
								.getResultList();
					}

				} else {

					if (id_categorie == 0) {

						return em.createQuery(
								"select s from SheetPFE s join s.etudiant e join s.entreprise p where p.Pays= :pays and e.classe.anneeDeDebut = :year",
								SheetPFE.class).setParameter("year", year).setParameter("pays", pays).getResultList();

					} else {

						return em.createQuery(
								"select s from SheetPFE s join s.etudiant e join s.entreprise p join s.categories c where p.Pays= :pays and  c.id= :idcategorie  and e.classe.anneeDeDebut = :year",
								SheetPFE.class).setParameter("year", year).setParameter("idcategorie", id_categorie)
								.setParameter("pays", pays).getResultList();

					}

				}

			}

		} else {

			if (year == 0) {

				if (pays.equals("ALL")) {

					if (id_categorie == 0) {

						return em.createQuery("select s from SheetPFE s where s.etat = :etat ", SheetPFE.class)
								.setParameter("etat", etat).getResultList();

					} else {

						return em.createQuery(
								"select s from SheetPFE s join s.categories c where s.etat = :etat and  c.id= :idcategorie",
								SheetPFE.class).setParameter("etat", etat).setParameter("idcategorie", id_categorie)
								.getResultList();
					}

				} else {

					if (id_categorie == 0) {

						return em.createQuery(
								"select s from SheetPFE s join s.entreprise p where s.etat = :etat and  p.Pays= :pays",
								SheetPFE.class).setParameter("etat", etat).setParameter("pays", pays).getResultList();

					} else {

						return em.createQuery(
								"select s from SheetPFE s join s.entreprise p join s.categories c where s.etat = :etat and  p.Pays= :pays and c.id= :idcategorie",
								SheetPFE.class).setParameter("etat", etat).setParameter("pays", pays)
								.setParameter("idcategorie", id_categorie).getResultList();
					}

				}

			} else {

				if (pays.equals("ALL")) {

					if (id_categorie == 0) {

						return em.createQuery(
								"select s from SheetPFE s join s.etudiant e where s.etat = :etat and  e.classe.anneeDeDebut = :year",
								SheetPFE.class).setParameter("etat", etat).setParameter("year", year).getResultList();

					} else {

						return em.createQuery(
								"select s from SheetPFE s join s.etudiant e join s.categories c where s.etat = :etat and  c.id= :idcategorie  and e.classe.anneeDeDebut = :year",
								SheetPFE.class).setParameter("etat", etat).setParameter("year", year)
								.setParameter("idcategorie", id_categorie).getResultList();
					}

				} else {

					if (id_categorie == 0) {

						return em.createQuery(
								"select s from SheetPFE s join s.etudiant e join s.entreprise p where s.etat = :etat and  p.Pays= :pays and e.classe.anneeDeDebut = :year",
								SheetPFE.class).setParameter("etat", etat).setParameter("year", year)
								.setParameter("pays", pays).getResultList();

					} else {

						return em.createQuery(
								"select s from SheetPFE s join s.etudiant e join s.entreprise p join s.categories c where s.etat = :etat and  p.Pays= :pays and  c.id= :idcategorie  and e.classe.anneeDeDebut = :year",
								SheetPFE.class).setParameter("etat", etat).setParameter("year", year)
								.setParameter("idcategorie", id_categorie).setParameter("pays", pays).getResultList();

					}

				}

			}

		}

	}

	@Override
	public List<SheetPFE> getAllSheetPFEDefault() {

		Date date = new Date();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		if (1 <= month && month <= 5) {
			year = year - 1;
		}

		return em.createQuery(
				"select s from SheetPFE s join s.etudiant e where s.etat ='DEFAULT' and e.classe.anneeDeDebut = :year order by(s.id)",
				SheetPFE.class).setParameter("year", year).getResultList();
	}

	@Override
	public boolean verificationByDirectorSheetPFE(int sheet_id, EtatSheetPFE etat) {
		Enseignant enseignant = em.find(Enseignant.class, 3);
		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			sheetPFE.setEtat(etat);
			sheetPFE.setNote("Entreprise does not exist");
			em.merge(sheetPFE);
			if (sheetPFE.getEtat().equals(EtatSheetPFE.REFUSE))

				new Email().entrepriseNotExist(sheetPFE);
//			NotificationEtudiant notification = new NotificationEtudiant();
//				notification.setNote("Entreprise does not exist");
//				notification.setTitle("Validated Sheet PFE");
//				notification.setEnseignant(enseignant);
//				notification.setEtudiant(sheetPFE.getEtudiant());
//				notification.setCreated(new Date());
//				notification.setVu(0);
//				notification.setBy("DIRECTEUR");
//				em.persist(notification);

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

		Enseignant enseignant = em.find(Enseignant.class, 3);

		try {

			RequestCancelInternship req = em.find(RequestCancelInternship.class, request_id);
			req.setEtat(etat);
			req.setNote(note);
			em.merge(req);

			SheetPFE sheetPFE = em.find(SheetPFE.class, req.getSheetPFE().getId());

			if (etat.equals(EtatSheetPFE.ACCEPTED)) {
				sheetPFE.setEtat(EtatSheetPFE.CANCEL);
				em.merge(sheetPFE);

//				NotificationEtudiant notification = new NotificationEtudiant();
//				notification.setNote("Your request to cancel the internship accepted");
//				notification.setTitle("Internship CANCEL");
//				notification.setEnseignant(enseignant);
//				notification.setEtudiant(sheetPFE.getEtudiant());
//				notification.setCreated(new Date());
//				notification.setVu(0);
//				notification.setBy("DIRECTEUR");
//				em.persist(notification);

			} else {

//				NotificationEtudiant notification = new NotificationEtudiant();
//				notification.setNote("Your request to cancel the internship has been refused");
//				notification.setTitle("Your request Refuse");
//				notification.setEnseignant(enseignant);
//				notification.setEtudiant(sheetPFE.getEtudiant());
//				notification.setCreated(new Date());
//				notification.setVu(0);
//				notification.setBy("DIRECTEUR");
//				em.persist(notification);

			}

			new Email().requestCancelInternship(sheetPFE);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<SheetPFE> getAllSheetValidate() {
		return em.createQuery("select s from SheetPFE s where s.etat = 'VALIDATE' ", SheetPFE.class).getResultList();
	}

	@Override
	public List<SheetPFE> getAllSheetPFEAccepted() {
		return em.createQuery("select s from SheetPFE s where s.etat = 'ACCEPTED' ", SheetPFE.class).getResultList();
	}

	@Override
	public boolean affectValidateurToSheetPFE(int sheet_id) {

		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			// Enseignant enseignant = getAllValidateur().stream().filter(e ->
			// e.getEnseignantsheet().getT.size() == 0).findFirst().get();

			EnseignantSheetPFE enseignantSheetPFE = new EnseignantSheetPFE();
			// enseignantSheetPFE.setEnseignant(enseignant);
			enseignantSheetPFE.setSheetPFE(sheetPFE);
			enseignantSheetPFE.setType("VALIDATEUR");
			em.merge(enseignantSheetPFE);

			sheetPFE.setEtat(EtatSheetPFE.PRE_VALIDATE);
			em.merge(sheetPFE);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean validateSheetPFE(int sheet_id, EtatSheetPFE etat, String note) {

		Enseignant enseignant = em.find(Enseignant.class, 3);

		try {

			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);

			if (etat.equals(EtatSheetPFE.VALIDATE)) {

				note = "Your subject of PFE has been accepted";
				sheetPFE.setEtat(etat);
				em.merge(sheetPFE);

//				NotificationEtudiant notification = new NotificationEtudiant();
//				notification.setNote(note);
//				notification.setTitle("Validated Sheet PFE");
//				notification.setEnseignant(enseignant);
//				notification.setEtudiant(sheetPFE.getEtudiant());
//				notification.setCreated(new Date());
//				notification.setVu(0);
//				notification.setBy("ENSEIGNANT");
//				em.persist(notification);

				new Email().validateSheetPFE(sheetPFE, etat, note);

			} else {

				sheetPFE.setEtat(etat);
				em.merge(sheetPFE);
//				NotificationEtudiant notification = new NotificationEtudiant();
//				notification.setNote(note);
//				notification.setTitle("Refused subject PFE");
//				notification.setEnseignant(enseignant);
//				notification.setEtudiant(sheetPFE.getEtudiant());
//				notification.setCreated(new Date());
//				notification.setVu(0);
//				notification.setBy("ENSEIGNANT");
//				em.persist(notification);

				new Email().validateSheetPFE(sheetPFE, etat, note);

			}

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<SheetPFE> getAllSheetWaitEncadreur() {
		return em.createQuery(
				"select s from SheetPFE s where s.id not in (select s.id from EnseignantSheetPFE es Left join es.sheetPFE s  where es.type = 'ENCADREUR') ",
				SheetPFE.class).getResultList();
	}

	@Override
	public List<Integer> getAllEnseignantOrderByEncadrement() {
		return em
				.createQuery("select e.id from Enseignant e JOIN e.enseignantsheet es order by(select count(e1) from Enseignant e1 JOIN e1.enseignantsheet es  where es.type='ENCADREUR' and e1.id = e.id)")
				.getResultList();
	}

	@Override
	public List<Enseignant> getEncardeurByCategories(int sheet_id) {


		return em.createQuery(
				"select e from Enseignant e join  e.categories c join c.sheetPFEs s where s.id=:id and e.id not in (select e.id from  Enseignant e join e.enseignantsheet es where es.type= 'ENCADREUR' group by(e) having count(e) = 0) group by(e) order by(count(e)) desc",Enseignant.class)
				.setParameter("id", sheet_id).getResultList();
	}
	
	

	@Override
	public boolean affectEncadreurToSheetPFEAuto(int sheet_id) {

		try {

			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			Enseignant enseignant = getEncardeurByCategories(sheet_id).stream().findFirst().get();
			EnseignantSheetPFE enseignantSheetPFE = new EnseignantSheetPFE();
			enseignantSheetPFE.setEnseignant(enseignant);
			enseignantSheetPFE.setSheetPFE(sheetPFE);
			enseignantSheetPFE.setType("ENCADREUR");
			em.merge(enseignantSheetPFE);
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
			EnseignantSheetPFE enseignantSheetPFE = new EnseignantSheetPFE();
			enseignantSheetPFE.setEnseignant(enseignant);
			enseignantSheetPFE.setSheetPFE(sheetPFE);
			enseignantSheetPFE.setType("ENCADREUR");
			em.merge(enseignantSheetPFE);
			
			
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	@Override
	public boolean updateEncadreurSheetPFE(int sheetPFE_id, int enseignant_id) {

	    	Enseignant encadreur = em.find(Enseignant.class, enseignant_id);

		try {

			EnseignantSheetPFE enseignantSheetPFE = em.createQuery(
					"select e from EnseignantSheetPFE e left join e.sheetPFE s where s.id = :sheetPFE_id and e.type='ENCADREUR' ",
					EnseignantSheetPFE.class).setParameter("sheetPFE_id", sheetPFE_id).getSingleResult();
			Enseignant enseignant = em.find(Enseignant.class, enseignant_id);
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheetPFE_id);
			
			enseignantSheetPFE.setEnseignant(enseignant);
			em.merge(enseignantSheetPFE);
			
//			Notification notification = new Notification();
//			notification.setNote("Modification encadreur");
//			notification.setTitle("Modification encadreur of sheet PFE");
//			notification.setEtudiant(sheetPFE.getEtudiant());
//			notification.setEnseignant(enseignant);
//			notification.setCreated(new Date());
//			notification.setVu(0);
//			notification.setBy("DIRECTEUR");
//			em.persist(notification);
//			
			return true;

		} catch (Exception e) {

			return false;
		}

	}
	
	@Override
	public List<Enseignant> getRapporteurByCategories(int sheet_id) {

		return em.createQuery(
				"select e from Enseignant e join  e.categories c join c.sheetPFEs s where s.id=:id and e.id not in (select e.id from  Enseignant e join e.enseignantsheet es where es.type= 'RAPPORTEUR' group by(e) having count(e) = 0) group by(e) order by(count(e)) desc",Enseignant.class)
				.setParameter("id", sheet_id).getResultList();
	}
	
	@Override
	public boolean affectRapporteurToSheetPFEAuto(int sheet_id) {

		try {

		
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			Enseignant enseignant = getRapporteurByCategories(sheet_id).stream().findFirst().get();
			EnseignantSheetPFE enseignantSheetPFE = new EnseignantSheetPFE();
			enseignantSheetPFE.setEnseignant(enseignant);
			enseignantSheetPFE.setSheetPFE(sheetPFE);
			enseignantSheetPFE.setType("RAPPORTEUR");
			em.merge(enseignantSheetPFE);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean affectRapporteurToSheetPFEManual(int sheet_id, int enseignant_id) {
						
		    Enseignant encadreur = em.find(Enseignant.class, enseignant_id);
		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			Enseignant enseignant = em.find(Enseignant.class, enseignant_id);
			EnseignantSheetPFE enseignantSheetPFE = new EnseignantSheetPFE();
			enseignantSheetPFE.setEnseignant(enseignant);
			enseignantSheetPFE.setSheetPFE(sheetPFE);
			enseignantSheetPFE.setType("RAPPORTEUR");
			em.merge(enseignantSheetPFE);
			
			
			return true;

		} catch (Exception e) {
			return false;

		}
	}
	
	@Override
	public boolean updateRapporteurSheetPFE(int sheetPFE_id, int enseignant_id) {

	    	Enseignant encadreur = em.find(Enseignant.class, enseignant_id);

		try {

			EnseignantSheetPFE enseignantSheetPFE = em.createQuery(
					"select e from EnseignantSheetPFE e left join e.sheetPFE s where s.id = :sheetPFE_id and e.type='RAPPORTEUR' ",
					EnseignantSheetPFE.class).setParameter("sheetPFE_id", sheetPFE_id).getSingleResult();
			Enseignant enseignant = em.find(Enseignant.class, enseignant_id);
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheetPFE_id);
			
			enseignantSheetPFE.setEnseignant(enseignant);
			em.merge(enseignantSheetPFE);
			
//			Notification notification = new Notification();
//			notification.setNote("Modification encadreur");
//			notification.setTitle("Modification encadreur of sheet PFE");
//			notification.setEtudiant(sheetPFE.getEtudiant());
//			notification.setEnseignant(enseignant);
//			notification.setCreated(new Date());
//			notification.setVu(0);
//			notification.setBy("DIRECTEUR");
//			em.persist(notification);
//			
			return true;

		} catch (Exception e) {

			return false;
		}

	}

	@Override
	public List<SheetPFE> getAllSheetWaitRapporter() {
		return em.createQuery(
				"select s from SheetPFE s where s.id not in (select s.id from EnseignantSheetPFE es Left join es.sheetPFE s  where es.type = 'RAPPORTEUR') ",
				SheetPFE.class).getResultList();
	}

	@Override
	public List<Enseignant> getAllValidateur() {
		return em.createQuery("select e from Enseignant e where e.role='VALIDATEUR'", Enseignant.class).getResultList();

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
	public List<SheetPFE> getAllSheetPFEWaitNote() {
		
		return em.createQuery("select s from SheetPFE  where noteRapporteur= 0 or noteEncadreur= 0 and etat='VALIDATE'", SheetPFE.class).getResultList();
	}

	
	public List<SheetPFE> getAllSheetPFEWaitPlaning(){
		return em.createQuery("select s from SheetPFE  where noteRapporteur > 0 and noteEncadreur > 0 ", SheetPFE.class).getResultList();
	}
	
	@Override
	public boolean accepteSheetModify(int sheet_id,EtatSheetPFE etat,String note) {
		
		try {
			
			SheetPFEModification sheet = em.find(SheetPFEModification.class, sheet_id);
			SheetPFE oldsheet = em.find(SheetPFE.class, sheet.getSheetPFE().getId());
			if(etat.equals(EtatSheetPFE.ACCEPTED)) {
				
				SheetPFE sheetPFE = em.find(SheetPFE.class, sheet.getSheetPFE().getId());
				sheetPFE.setCategories(sheet.getCategories());
				sheetPFE.setDescription(sheet.getDescription());
				sheetPFE.setTitle(sheet.getTitle());
				sheetPFE.setFeatures(sheet.getFeatures());
				sheetPFE.setProblematic(sheet.getProblematic());
				em.merge(sheetPFE);
				
				sheet.setCategories(oldsheet.getCategories());
				sheet.setDescription(oldsheet.getDescription());
				sheet.setTitle(oldsheet.getTitle());
				sheet.setFeatures(oldsheet.getFeatures());
				sheet.setProblematic(oldsheet.getProblematic());
				sheet.setEtat(etat);
				
			}else {
				
				sheet.setEtat(etat);
				sheet.setNote(note);
			}
			
			em.merge(sheet);
			return true;
			
		}catch (Exception e) {
			return false;

		}
	}

	@Override
	public List<SheetPFE> getAllSheetByEnseignant(int startyear, int toyear) {

		Enseignant enseignant = em.find(Enseignant.class, 2);

		if(toyear == 0) {
			
			return em.createQuery("select s from SheetPFE s join s.enseignantsheet es join es.enseignant e where s.etudiant.classe.anneeDeDebut = :year and e.id=:id",SheetPFE.class)
					.setParameter("id", enseignant.getId()).setParameter("year", startyear).getResultList();
		}else {
			
			return em.createQuery("select s from SheetPFE s join s.enseignantsheet es join es.enseignant e where e.id = :id and  s.etudiant.classe.anneeDeDebut BETWEEN :startyear and :toyear  ",SheetPFE.class)
					.setParameter("id", enseignant.getId()).setParameter("startyear", startyear ).setParameter("toyear", toyear ).getResultList();
		}
		

	}
	
	
	@Override
	public List<SheetPFE> getAllSheetByValidateur() {
		
		Enseignant enseignant = em.find(Enseignant.class, 2);
		
		return em.createQuery("select s from SheetPFE s join s.enseignantsheet es join es.enseignant e where e.id = :id and es.type='VALIDATEUR' ",SheetPFE.class)
				.setParameter("id", enseignant.getId()).getResultList();
		
	}
	@Override
	public List<SheetPFE> getAllSheetByEncadreur() {
		
		Enseignant enseignant = em.find(Enseignant.class, 2);
		
		return em.createQuery("select s from SheetPFE s join s.enseignantsheet es join es.enseignant e where e.id = :id and es.type='ENCADREUR' ",SheetPFE.class)
				.setParameter("id", enseignant.getId()).getResultList();
		
	}
	
	@Override
	public List<SheetPFE> getAllSheetByRapporteur() {
		
		Enseignant enseignant = em.find(Enseignant.class, 2);
		
		return em.createQuery("select s from SheetPFE s join s.enseignantsheet es join es.enseignant e where e.id = :id and es.type='RAPPORTEUR' ",SheetPFE.class)
				.setParameter("id", enseignant.getId()).getResultList();
		
	}
	
	
	@Override
	public boolean addNoteEncadreur(int note, int sheet_id) {
		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			sheetPFE.setNoteEncadreur(note);
			return true;
		}catch (Exception e) {
			
			return false;
			
		}
	}

	@Override
	public boolean addNoteRapporteur(int note, int sheet_id) {
		
		try {
			SheetPFE sheetPFE = em.find(SheetPFE.class, sheet_id);
			sheetPFE.setNoteRapporteur(note);
			return true;
		}catch (Exception e) {
			
			return false;
			
		}
	}

	@Override
	public List<SheetPFEModification> getALLSheetModifyDefault() {
		
		return em.createQuery("select s from SheetPFEModification s  where s.etat = 'DEFAULT'  ",SheetPFEModification.class).getResultList();
		
	}

	@Override
	public SheetPFEModification getSheetModify(int sheet_id) {
		
		return em.find(SheetPFEModification.class, sheet_id);
	}
	
	
	

	
}
