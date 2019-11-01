package tn.esprit.webServices;

import java.util.List;

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
import tn.esprit.pfe.entities.EtatSheetPFE;
import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.RequestCancelInternship;
import tn.esprit.pfe.entities.SheetPFE;
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
	@Path("/nostudent/{startyear}/{endyear}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllStudentNoSheetWithYear(@PathParam(value="startyear") String start , @PathParam(value="endyear") String end ) {

		List<Etudiant> listetudiant = IsheetPFE.getAllStudentNoSheetWithYear(start,end);

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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSheetPFE() {

		List<SheetPFE> listSheetPFE = IsheetPFE.getAllSheetPFE();

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
	@Consumes(MediaType.APPLICATION_JSON)
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
		

		if (IsheetPFE.affectEncadreurToSheetPFEAuto(id)) {

			return Response.status(Status.ACCEPTED).build();
		}

		return Response.status(Status.NO_CONTENT).build();

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
	@Path("/dashboard")
	@Produces(MediaType.APPLICATION_JSON)
	public Response statEtatSheetPFE() {	
		

		if (IsheetPFE.statEtatSheetPFE() != null) {

			return Response.status(Status.ACCEPTED).entity(IsheetPFE.statEtatSheetPFE()).build();
		}

		return Response.status(Status.NO_CONTENT).build();

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
		

		return Response.status(Status.NO_CONTENT).build();

	}
	
}
