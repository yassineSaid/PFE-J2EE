package tn.esprit.webServices;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import rest.utilities.authentication.AuthenticationFilter;
import rest.utilities.authentication.Secure;
import tn.esprit.pfe.entities.Ecole;
import tn.esprit.pfe.services.EcoleService;
import utilities.ValidationError;

@Path("ecole")
@RequestScoped
public class EcoleWebServices {

	@EJB
	EcoleService es;
	
	@Context
	private HttpHeaders headers;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secure(role={"Admin"})
	public Response addEcole(Ecole e) {
		//String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		AuthenticationFilter af=new AuthenticationFilter();
		Set<ValidationError> violations=es.addEcole(e, af.getIdUser(headers));
		if (violations==null) {
			return Response.status(Status.CREATED).entity("add successful").build();
		}
		else return Response.status(Status.INTERNAL_SERVER_ERROR).entity(violations).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secure(role={"Admin"})
	@Path ("{id}")
	public Response modifierEcole(Ecole e, @PathParam(value="id") int idEcole) {
		//String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		AuthenticationFilter af=new AuthenticationFilter();
		Set<ValidationError> violations=es.modifierEcole(e, af.getIdUser(headers), idEcole);
		if (violations==null) {
			return Response.status(Status.OK).entity("modify successful").build();
		}
		else return Response.status(Status.INTERNAL_SERVER_ERROR).entity(violations).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secure(role={"Admin"})
	@Path ("{id}")
	public Response supprimerEcole(@PathParam(value="id") int idEcole) {
		//String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		AuthenticationFilter af=new AuthenticationFilter();
		Set<ValidationError> violations=es.supprimerEcole(idEcole, af.getIdUser(headers));
		if (violations==null) {
			return Response.status(Status.OK).entity("delete successful").build();
		}
		else return Response.status(Status.INTERNAL_SERVER_ERROR).entity(violations).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secure(role={"Admin"})
	@Path ("{id}")
	public Response getMyEcole(@PathParam(value="id") int idEcole) {
		//String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		AuthenticationFilter af=new AuthenticationFilter();
		Ecole ecole=es.getEcole(idEcole, af.getIdUser(headers));
		if (ecole!=null) {
			return Response.status(Status.OK).entity(ecole).build();
		}
		else return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Cette ecole n'existe pas ou vous n'etes pas autorisé à la consulter").build();
	}
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secure(role={"Admin","SuperAdmin"})
	@GET
	public Response getListEcole() {
		List<Ecole> liste = es.getListEcole();
		return Response.status(Status.OK).entity(liste).build();
	}
	

}
