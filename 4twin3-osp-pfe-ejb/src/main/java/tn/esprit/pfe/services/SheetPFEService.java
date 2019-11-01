package tn.esprit.pfe.services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.pfe.entities.EtatSheetPFE;
import tn.esprit.pfe.entities.RequestCancelInternship;
import tn.esprit.pfe.entities.SheetPFE;
import tn.esprit.pfe.entities.SheetPFEModification;
import tn.esprit.pfe.interfaces.SheetPFERemote;
import tn.esprit.pfe.entities.Etudiant;

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
		return sheetPFE.getId();

	}

	@Override
	public List<SheetPFE> getAllSheetPFE() {
		// new EmailSheetPFE().send("yassine.lahbib@esprit.tn", "hello", "hello");
		return em.createQuery("select s from SheetPFE s where s.etat=:etat ", SheetPFE.class)
				.setParameter("etat", EtatSheetPFE.DEFAULT).getResultList();
	}

	@Override
	public SheetPFE getSheetPFEById(int id) {
		SheetPFE sheetPFE = em.find(SheetPFE.class, id);
		if (sheetPFE.getEtat().equals(EtatSheetPFE.DEFAULT)) {
			sheetPFE.setEtat(EtatSheetPFE.VERIFICATION);
			em.merge(sheetPFE);
		}
		return em.find(SheetPFE.class, id);
	}

	@Override
	public SheetPFE getSheetPFEByEtudiant() {

		Etudiant etudiant = em.find(Etudiant.class, 1);
		return em.createQuery("select i from SheetPFE i join i.etudiant e where e.id=:etudiantId", SheetPFE.class)
				.setParameter("etudiantId", etudiant.getId()).getSingleResult();
	}

	@Override
	public boolean updateSheetPFE(SheetPFE sheetPFE) {
		SheetPFE oldsheet = em.find(SheetPFE.class, sheetPFE.getId());
		try {

			if (oldsheet.getEtat().equals(EtatSheetPFE.DEFAULT)) {
				em.merge(sheetPFE);
			} else if (sheetPFE.getFeatures().equals(oldsheet.getFeatures())
					&& sheetPFE.getProblematic().equals(oldsheet.getProblematic())) {
				em.merge(sheetPFE);
			} else {
				// Modification old sheet
				oldsheet.setTitle(sheetPFE.getTitle());
				oldsheet.setDescription(sheetPFE.getDescription());
				oldsheet.setEntreprise(sheetPFE.getEntreprise());
				oldsheet.setCategories(sheetPFE.getCategories());

				em.merge(oldsheet);

				// Add Demande modification
				SheetPFEModification sheetPFEModification = new SheetPFEModification();
				sheetPFEModification.setFeatures(sheetPFE.getFeatures());
				sheetPFEModification.setProblematic(sheetPFE.getProblematic());
				sheetPFEModification.setCreated(new Date());
				sheetPFEModification.setSheetPFE(sheetPFE);
				sheetPFEModification.setEtat(EtatSheetPFE.DEFAULT);

				em.persist(sheetPFEModification);

			}

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean validateSheetPFE(SheetPFE sheetPFE) {

		try {
			em.merge(sheetPFE);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void affectSheetEnseignantToSheetPFEAuto(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void affectSheetEnseignantToSheetPFEManual(int sheet_id, int enseignant_id) {
		// TODO Auto-generated method stub

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
	public boolean updateRequest(RequestCancelInternship request) {
		try {

			SheetPFE sheetPFE = em.find(SheetPFE.class, request.getSheetPFE().getId());
			sheetPFE.setEtat(EtatSheetPFE.CANCEL);

			em.merge(request);
			em.merge(sheetPFE);

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

}
