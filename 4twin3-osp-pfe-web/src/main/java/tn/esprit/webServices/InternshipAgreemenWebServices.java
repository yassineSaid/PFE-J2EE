package tn.esprit.webServices;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pfe.entities.InternshipAgreemen;
import tn.esprit.pfe.interfaces.InternshipAgreemenRemote;


@Path("/Agreemen")
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
	public Response getAllAgreemen() {		
		System.out.print(" rg"+Iagreemen.getAllAgreemen());
	     return Response.status(Status.CREATED).entity(Iagreemen.getAllAgreemen()).build();
	
	}
	
}
