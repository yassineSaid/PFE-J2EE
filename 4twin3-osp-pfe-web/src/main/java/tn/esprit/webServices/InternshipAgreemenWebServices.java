package tn.esprit.webServices;

import java.util.ArrayList;
import java.util.Date;
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
import tn.esprit.pfe.interfaces.InternshipAgreemenRemote;


@Path("Agreemen")
public class InternshipAgreemenWebServices {
	
	@EJB
	InternshipAgreemenRemote Iagreemen;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAgreemen(InternshipAgreemen internshipAgreemen) {
		
	  if(Iagreemen.addInternshipAgreemen(internshipAgreemen) > 0)
		  
	     return Response.status(Status.CREATED).entity(internshipAgreemen.getId()).build();
	  
	    return Response.status(Status.BAD_REQUEST).entity("erreur").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<InternshipAgreemen> getAllAgreemen() {		
	     return Iagreemen.getAllAgreemen();
	
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAgreemenById(@PathParam(value="id") int id) {	
		
		return Response.status(Status.CREATED).entity(Iagreemen.getAgreemenById(id)).build();
	
	}
	
	@GET
	@Path("/etudiant/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAgreemenByEtudiant(@PathParam(value="id") int id) {	
		
		return Response.status(Status.CREATED).entity(Iagreemen.getAgreemenByEtudiant(id)).build();
	
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInternshipAgreemen(@PathParam(value="id") int id) {	
		
		if(Iagreemen.updateInternshipAgreemen(id))
	    	return Response.status(Status.ACCEPTED).entity("success").build();
		
    	return Response.status(Status.BAD_REQUEST).entity("erreur").build();

	}
	
	@DELETE
	@Path("/{id}")
    public Response removeInternshipAgreemen(@PathParam(value="id") int id) {	
		
		Iagreemen.removeInternshipAgreemen(id);
	    	return Response.status(Status.ACCEPTED).entity("success").build();
	}
	
	
	
}
