package tn.esprit.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pfe.entities.InternshipAgreemen;
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
//	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAllSheetPFE() {
//
//		List<SheetPFE> listSheetPFE = IsheetPFE.getAllSheetPFE();
//
//		if (listSheetPFE.size() != 0) {
//
//			return Response.status(Status.ACCEPTED).entity(listSheetPFE).build();
//		}
//
//		return Response.status(Status.NO_CONTENT).build();
//
//	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAgreemenById(@PathParam(value="id") int id) {
		
		if(IsheetPFE.getSheetPFEById(id) != null)
			  return Response.status(Status.ACCEPTED).entity(IsheetPFE.getSheetPFEById(id)).build();
		
		return  Response.status(Status.NOT_FOUND).build();
	}
//
//	@GET
//	@Path("/etudiant")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAgreemenByEtudiant() {	
//		
//		if(IsheetPFE.getSheetPFEByEtudiant() != null)
//		  return Response.status(Status.ACCEPTED).entity(IsheetPFE.getSheetPFEByEtudiant()).build();
//	
//		return  Response.status(Status.NOT_FOUND).build();
//	
//	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInternshipAgreemen(SheetPFE sheetPFE) {	
		
		if(IsheetPFE.updateSheetPFE(sheetPFE))
	    	return Response.status(Status.ACCEPTED).entity(sheetPFE).build();
		
    	return Response.status(Status.NOT_MODIFIED).build();

	}
	
	@PUT
	@Path("/validate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateSheetPFE(SheetPFE sheetPFE) {	
		
		if(IsheetPFE.validateSheetPFE(sheetPFE))
	    	return Response.status(Status.ACCEPTED).entity(sheetPFE).build();
		
    	return Response.status(Status.NOT_MODIFIED).build();

	}
	
//	@POST
//	@Path("/cancel")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response requestCancelInternship(SheetPFE sheetPFE) {
//
//		IsheetPFE.requestCancelInternship(sheetPFE.getId());
//		return Response.status(Status.CREATED).entity(IsheetPFE.getResquest(sheetPFE.getId())).build();
//
//	}
//	@GET
//	@Path("/cancel")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAllCancelSheetPFE() {	
//		
//		List<RequestCancelInternship> listRequest = IsheetPFE.getAllRequest();
//
//		if (listRequest.size() != 0) {
//
//			return Response.status(Status.ACCEPTED).entity(listRequest).build();
//		}
//
//		return Response.status(Status.NO_CONTENT).build();
//
//	}
	
//	@GET
//	@Path("/cancel/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getResquest(@PathParam(value="id") int id) {
//		
//		if(IsheetPFE.getResquest(id) != null)
//			  return Response.status(Status.ACCEPTED).entity(IsheetPFE.getResquest(id)).build();
//		
//		return  Response.status(Status.NOT_FOUND).build();
//	}
	
	
//	@PUT
//	@Path("/cancel")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateInternshipAgreemen(RequestCancelInternship request) {	
//		
//		if(IsheetPFE.updateRequest(request))
//	    	return Response.status(Status.ACCEPTED).entity(request).build();
//		
//    	return Response.status(Status.NOT_MODIFIED).build();
//
//	}
//	
	
}
