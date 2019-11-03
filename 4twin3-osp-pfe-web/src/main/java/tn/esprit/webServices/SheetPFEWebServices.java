package tn.esprit.webServices;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pfe.entities.Categorie;
import tn.esprit.pfe.entities.Enseignant;
import tn.esprit.pfe.entities.EnseignantSheetPFE;
import tn.esprit.pfe.entities.EtatSheetPFE;
import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.PFENotification;
import tn.esprit.pfe.entities.RequestCancelInternship;
import tn.esprit.pfe.entities.SheetPFE;
import tn.esprit.pfe.entities.SheetPFEModification;
import tn.esprit.pfe.interfaces.SheetPFERemote;

@Path("sheet")
public class SheetPFEWebServices {

	@EJB
	SheetPFERemote IsheetPFE;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSheetPFE(SheetPFE sheetPFE) {

		sheetPFE.setId(IsheetPFE.addSheetPFE(sheetPFE));
		return Response.status(Status.CREATED).entity(sheetPFE).build();

	}
	
	@GET
	@Path("/nostudent/{startyear}/{toyear}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllStudentNoSheetWithYear(@PathParam(value="startyear") int startyear,@PathParam(value="toyear") int toyear) {

		List<Etudiant> listetudiant = IsheetPFE.getAllStudentNoSheetWithYear(startyear,toyear);

		if (listetudiant.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listetudiant).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/nostudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllStudentNoSheetPFE() {

		List<Etudiant> listetudiant = IsheetPFE.getAllStudentNoSheet();

		if (listetudiant.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listetudiant).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@POST
	@Path("/reminder")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reminderStudentNoSheet(List<Etudiant> students) {

		IsheetPFE.reminderStudentNoSheet(students);
		return Response.status(Status.ACCEPTED).build();

	}
	
	@GET
	@Path("/{etat}/{year}/{pays}/{id_categorie}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetPFEFilter(@PathParam(value="etat") EtatSheetPFE etat,@PathParam(value="year") int year,
			@PathParam(value="pays") String pays,@PathParam(value="id_categorie") int id_categorie) {

		List<SheetPFE> listSheetPFE = IsheetPFE.getAllSheetPFEFilter(etat, year, pays, id_categorie);

		if (listSheetPFE.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listSheetPFE).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/default")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetPFEDefault() {

		List<SheetPFE> listSheetPFE = IsheetPFE.getAllSheetPFEDefault();

		if (listSheetPFE.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listSheetPFE).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/accepted")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetPFEAccepted() {

		List<SheetPFE> listsheet = IsheetPFE.getAllSheetPFEAccepted();

		if (listsheet.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listsheet).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetPFEValidate() {

		List<SheetPFE> listSheetPFE = IsheetPFE.getAllSheetValidate();

		if (listSheetPFE.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listSheetPFE).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	
	@GET
	@Path("/waitEncadreur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetWaitEncadreur() {

		List<SheetPFE> listSheetPFE = IsheetPFE.getAllSheetWaitEncadreur();

		if (listSheetPFE.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listSheetPFE).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/waitRapporter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetWaitRapporter() {

		List<SheetPFE> listSheetPFE = IsheetPFE.getAllSheetWaitRapporter();

		if (listSheetPFE.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listSheetPFE).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAgreemenById(@PathParam(value="id") int id) {
		
		if(IsheetPFE.getSheetPFEById(id) != null)
			  return Response.status(Status.ACCEPTED).entity(IsheetPFE.getSheetPFEById(id)).build();
		
		return  Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/etudiant")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAgreemenByEtudiant() {	
		
		if(IsheetPFE.getSheetPFEByEtudiant() != null)
		  return Response.status(Status.ACCEPTED).entity(IsheetPFE.getSheetPFEByEtudiant()).build();
	
		return  Response.status(Status.NOT_FOUND).build();
	
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInternshipAgreemen(SheetPFE sheetPFE) {	
		
		if(IsheetPFE.updateSheetPFE(sheetPFE))
	    	return Response.status(Status.ACCEPTED).entity(sheetPFE).build();
		
    	return Response.status(Status.NOT_MODIFIED).build();

	}
	
	@PUT
	@Path("/verification/{sheet_id}/{etat}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response verificationByDirectorSheetPFE(@PathParam(value="sheet_id") int sheet_id,@PathParam(value="etat") EtatSheetPFE etat) {	
		
		if(IsheetPFE.verificationByDirectorSheetPFE(sheet_id,etat))
	    	return Response.status(Status.ACCEPTED).entity(IsheetPFE.getSheetPFEById(sheet_id)).build();
		
    	return Response.status(Status.NOT_MODIFIED).build();

	}
	
	@POST
	@Path("/cancel/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response requestCancelInternship(@PathParam(value="id") int id) {

		IsheetPFE.requestCancelInternship(id);
		return Response.status(Status.CREATED).entity(IsheetPFE.getResquest(id)).build();

	}
	@GET
	@Path("/cancel")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCancelSheetPFE() {	
		
		List<RequestCancelInternship> listRequest = IsheetPFE.getAllRequest();

		if (listRequest.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(listRequest).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/cancel/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResquest(@PathParam(value="id") int id) {
		
		if(IsheetPFE.getResquest(id) != null)
			  return Response.status(Status.ACCEPTED).entity(IsheetPFE.getResquest(id)).build();
		
		return  Response.status(Status.NOT_FOUND).build();
	}
	
	
	@PUT
	@Path("/cancel/{request_id}/{etat}/{note}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInternshipAgreemen(@PathParam(value="request_id") int request_id,
			@PathParam(value="etat") EtatSheetPFE etat, @PathParam(value="note") String note ) {	
		
		if(IsheetPFE.updateRequest(request_id, etat, note))
	    	return Response.status(Status.ACCEPTED).entity(IsheetPFE.getResquest(request_id)).build();
		
    	return Response.status(Status.NOT_MODIFIED).build();

	}
	
	@GET
	@Path("/encadreur/{sheet_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEncardeurByCategories(@PathParam(value="sheet_id") int id) {	
		

		if (IsheetPFE.getEncardeurByCategories(id) != null) {

			return Response.status(Status.ACCEPTED).entity(IsheetPFE.getEncardeurByCategories(id)).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@POST
	@Path("/encadreur/affect/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response affectEncadreurToSheetPFEAuto(@PathParam(value="id") int id) {	
		

		if (IsheetPFE.affectEncadreurToSheetPFEAuto(id).equals("SUCCESS")) {

			return Response.status(Status.ACCEPTED).entity("SUCCESS").build();
		}
		if (IsheetPFE.affectEncadreurToSheetPFEAuto(id).equals("NO_CONTENT")) {
			
			return Response.status(Status.NO_CONTENT).entity("NO_CONTENT").build();
			
		}else {
			return Response.status(Status.NOT_MODIFIED).build();
		}

		

	}
	
	@POST
	@Path("/encadreur/affect/{idSheet}/{idEnseignant}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response affectEncadreurToSheetPFEManual(@PathParam(value="idSheet") int idSheet,@PathParam(value="idEnseignant") int idEnseignant) {	
		

		if (IsheetPFE.affectEncadreurToSheetPFEManual(idSheet, idEnseignant)) {

			return Response.status(Status.ACCEPTED).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/rapporteur/{sheet_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRapporteurByCategories(@PathParam(value="sheet_id") int id) {	
		

		if (IsheetPFE.getRapporteurByCategories(id) != null) {

			return Response.status(Status.ACCEPTED).entity(IsheetPFE.getEncardeurByCategories(id)).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@POST
	@Path("/rapporteur/affect/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response affectRapporteurToSheetPFEAuto(@PathParam(value="id") int id) {	
		

		if (IsheetPFE.affectRapporteurToSheetPFEAuto(id).equals("SUCCESS")) {
			
			return Response.status(Status.ACCEPTED).entity("SUCCESS").build();
			
		}else if(IsheetPFE.affectRapporteurToSheetPFEAuto(id).equals("NO_CONTENT")) {
			
			return Response.status(Status.NO_CONTENT).entity("NO_CONTENT").build();
			
		}else {
			
			return Response.status(Status.NOT_MODIFIED).build();

		}

	}
	
	@POST
	@Path("/rapporteur/affect/{idSheet}/{idEnseignant}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response affectRapporteurToSheetPFEManual(@PathParam(value="idSheet") int idSheet,@PathParam(value="idEnseignant") int idEnseignant) {	
		

		if (IsheetPFE.affectRapporteurToSheetPFEManual(idSheet, idEnseignant)) {

			return Response.status(Status.ACCEPTED).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@PUT
	@Path("/rapporteur/{sheetPFE_id}/{enseignant_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRapporteurSheetPFE(@PathParam(value="sheetPFE_id") int sheetPFE_id, @PathParam(value="enseignant_id") int enseignant_id) {	
		

		if(IsheetPFE.updateRapporteurSheetPFE(sheetPFE_id,enseignant_id)) 
			return Response.status(Status.ACCEPTED).build();
		
		return Response.status(Status.NOT_MODIFIED).build();
	
	}
	
	@GET
	@Path("/enseignantorderbyencadrement")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEnseignantOrderByEncadrement() {
		
		List<Enseignant> list = IsheetPFE.getAllEnseignantOrderByEncadrement();
		
		if(list.size() > 0)
			  return Response.status(Status.ACCEPTED).entity(list).build();
		
		return  Response.status(Status.NOT_FOUND).entity("efe").build();
	}
	
	@GET
	@Path("/enseignant/{startyear}/{toyear}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetByEnseignant(@PathParam(value="startyear") int startyear,@PathParam(value="toyear") int toyear) {	
		
		List<SheetPFE> list = IsheetPFE.getAllSheetByEnseignant(startyear, toyear);

		if (list.size() > 0) {

			return Response.status(Status.ACCEPTED).entity(list).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/encadreur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetByEncadreur() {	
		
		List<SheetPFE> list = IsheetPFE.getAllSheetByEncadreur();

		if (list.size() > 0) {

			return Response.status(Status.ACCEPTED).entity(list).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/rapporteur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetByRapporteur() {	
		
		List<SheetPFE> list = IsheetPFE.getAllSheetByRapporteur();

		if (list.size() > 0) {

			return Response.status(Status.ACCEPTED).entity(list).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/sheetvalidateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetByValidateur() {	
		
		List<SheetPFE> list = IsheetPFE.getAllSheetByValidateur();

		if (list.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(list).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@PUT
	@Path("/encadreur/{sheetPFE_id}/{enseignant_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEncadreurSheetPFE(@PathParam(value="sheetPFE_id") int sheetPFE_id, @PathParam(value="enseignant_id") int enseignant_id) {	
		

		if(IsheetPFE.updateEncadreurSheetPFE(sheetPFE_id,enseignant_id)) 
			return Response.status(Status.ACCEPTED).build();
		
		return Response.status(Status.NOT_MODIFIED).build();
	
	}
	

	
	@GET
	@Path("/validateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRapporteur() {	
		
		List<Enseignant> list = IsheetPFE.getAllValidateur();

		if (list.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(list).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	
	@POST
	@Path("/validateur/affect/{sheet_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response affectValidateurToSheetPFEAuto(@PathParam(value="sheet_id") int id) {	
		

		if( IsheetPFE.affectValidateurToSheetPFE(id))
			
			return Response.status(Status.ACCEPTED).build();
		

		return Response.status(Status.NOT_MODIFIED).build();

	}
	
	
	@PUT
	@Path("/accptedsheetmodifiy/{sheet_id}/{etat}/{note}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accepteSheetModify(@PathParam(value="sheet_id") int sheet_id,@PathParam(value="etat") EtatSheetPFE etat,@PathParam(value="note") String note) {	
		
		if(IsheetPFE.accepteSheetModify(sheet_id,etat,note))
	    	return Response.status(Status.ACCEPTED).build();
		
    	return Response.status(Status.NOT_MODIFIED).build();

	}
	
	@GET
	@Path("/modifiy")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getALLSheetModifyDefault() {	
		
		List<SheetPFEModification> list = IsheetPFE.getALLSheetModifyDefault();

		if (list.size() != 0) {

			return Response.status(Status.ACCEPTED).entity(list).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/modifiy/{sheet_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getALLSheetModifyDefault(@PathParam(value="sheet_id") int sheet_id) {	
		

		if (IsheetPFE.getSheetModify(sheet_id) != null) {

			return Response.status(Status.ACCEPTED).entity(IsheetPFE.getSheetModify(sheet_id)).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/notificationenseignant/{enseignant_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNotificationByEnseignant(@PathParam(value="enseignant_id") int enseignant_id) {	
		

		List<PFENotification> list = IsheetPFE.getAllNotificationByEnseignant(enseignant_id);

		if (list.size()>0) {

			return Response.status(Status.ACCEPTED).entity(list).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	@GET
	@Path("/notificationetudiant/{etudiant_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNotificationByEtudiant(@PathParam(value="etudiant_id") int etudiant_id) {	
		
		List<PFENotification> list = IsheetPFE.getAllNotificationByEtudiant(etudiant_id);
		
		if (list.size()>0) {

			return Response.status(Status.ACCEPTED).entity(list).build();
		}

		return Response.status(Status.NO_CONTENT).build();

	}
	
	
}
