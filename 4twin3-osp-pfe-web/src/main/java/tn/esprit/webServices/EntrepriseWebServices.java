package tn.esprit.webServices;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.services.EntrepriseServices;

@RequestScoped
@Path("Entreprise")
public class EntrepriseWebServices {
	@Inject
	EntrepriseServices es;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RegistreEntreprise(Entreprise ent) {
		int idEnt = es.addEntreprise(ent);
		if(idEnt != 0)
		{
			return Response.status(Status.CREATED).entity("Registeration Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Registration Failed ").build();
	}

	@PUT
	@Path("updateEntreprise")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEntreprise(Entreprise ent) {
		es.updateEntreprise(ent);
		return Response.status(Status.ACCEPTED).entity("Successful Update").build();
	}
}
