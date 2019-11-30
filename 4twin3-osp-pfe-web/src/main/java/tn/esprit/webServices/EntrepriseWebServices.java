package tn.esprit.webServices;

import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import rest.utilities.authentication.Secure;
import tn.esprit.pfe.entities.Admin;
import tn.esprit.pfe.entities.Entreprise;
import tn.esprit.pfe.entities.EntrepriseSupervisor;
import tn.esprit.pfe.entities.InternshipCataloge;
import tn.esprit.pfe.entities.InternshipOffer;
import tn.esprit.pfe.entities.JobOffer;
import tn.esprit.pfe.entities.ResponsableEntreprise;
import tn.esprit.pfe.services.EntrepriseServices;
import tn.esprit.pfe.services.UserService;
import utilities.ValidationError;

@RequestScoped
@Path("Entreprises")
public class EntrepriseWebServices {
	@Inject
	EntrepriseServices es;
	@EJB
	UserService us;

	@Context
	private HttpHeaders headers;

	/* Entreprise */
	
	@POST
	@Path("addEntreprise/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RegistreEntreprise(@PathParam("id") int id , Entreprise ent) {
		int idEnt = es.addEntreprise(ent,id);
		es.addEntreprisetoResponsable(id, idEnt);
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

	@GET
	@Path("Detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEntrepriseDetails(@PathParam("id") int id)
	{
		return Response.status(Status.ACCEPTED).entity(es.getEntrepriseDetails(id)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEntreprises()
	{
		return Response.status(Status.ACCEPTED).entity(es.getAllEntreprise()).build();
	}
	
	@DELETE
	@Path("Delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RemoveEntreprise(@PathParam("id") int id)
	{	
		es.deleteEntreprise(id);
	    return Response.status(Status.ACCEPTED).entity("Entreprise Deleted").build();
	}
	
	/* Internship */
	
	@POST
	@Path("addInternshipOffer/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addInternshipOffer(@PathParam("id") int idEnt,InternshipOffer inoff) {
		int idioff = es.addInternshipOffer(inoff);
		es.addInternshipOffertoEntreprise(idEnt, idioff);
		if(idioff != 0)
		{
			return Response.status(Status.CREATED).entity("Internship added Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Internship added Failed ").build();
	}
	
	@GET
	@Path("internshipOfferDetail/{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getInternshipOffer(@PathParam("id") int idioff)
	{
		return Response.status(Status.ACCEPTED).entity(es.getInternshipOfferDetails(idioff)).build();
	}
	
	@PUT
	@Path("updateinternshipOffer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateinternshipOffer(InternshipOffer inoff) {
		es.updateInternshipOffer(inoff);
		return Response.status(Status.ACCEPTED).entity("Successful internshipOffer Update").build();
	}
	
	@DELETE
	@Path("DeleteinternshipOffer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RemoveInternshipoffer(@PathParam("id") int inoff)
	{	
		es.deleteInternshipOffer(inoff);
	    return Response.status(Status.ACCEPTED).entity("InternshipOffer Deleted").build();
	}
	
	/* Supervisor */
	@POST
	@Path("addSupervisor/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSupervisor(@PathParam("id") int idEnt,EntrepriseSupervisor sup) {
		int idsup = es.addSupervisor(sup);
		es.addSupervisortoEntreprise(idEnt, idsup);
		if(idsup != 0)
		{
			return Response.status(Status.CREATED).entity("Supervisor added Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("Supervisor added Failed ").build();
	}
	
	@PUT
	@Path("updateSupervisor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSupervisor(EntrepriseSupervisor sup) {
		es.updateSupervisor(sup);;
		return Response.status(Status.ACCEPTED).entity("Successful Supervisor Update").build();
	}
	
	@DELETE
	@Path("DeleteSupervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RemoveSupervisor(@PathParam("id") int idsup)
	{	
		es.deleteSupervisor(idsup);
	    return Response.status(Status.ACCEPTED).entity("Supervisor Deleted").build();
	}
	
	@GET
	@Path("SupervisorDetail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSupervisorDetail(@PathParam("id") int idsup)
	{
		return Response.status(Status.ACCEPTED).entity(es.getEntrepriseSupervisor(idsup)).build();
	}

	/* JobOffre */
	
	@POST
	@Path("addJobOffer/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addJobOffre(@PathParam("id") int idEnt,JobOffer jo) {
		int idjo = es.addJobOffre(jo);
		es.addJobOffretoEntreprise(idEnt, idjo);
		if(idjo != 0)
		{
			return Response.status(Status.CREATED).entity("JobOffer added Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("JobOffer added Failed ").build();
	}

	@GET
	@Path("JobOfferDetail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJobOfferDetail(@PathParam("id") int idJo)
	{
		return Response.status(Status.ACCEPTED).entity(es.getJobOfferDetails(idJo)).build();
	}
	
	@PUT
	@Path("updateJobOffer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateJobOffer(JobOffer jo) {
		es.updateJobOffre(jo);
		return Response.status(Status.ACCEPTED).entity("Successful JobOffer Update").build();
	}

	@DELETE
	@Path("DeleteJobOffre/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RemoveJobOffre(@PathParam("id") int idjo)
	{	
		es.deleteJobOffre(idjo);
	    return Response.status(Status.ACCEPTED).entity("JobOffre Deleted").build();
	}

	/* InternshipCatalog */

	@POST
	@Path("InternshipCatalog/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addInternshipCatalog(@PathParam("id") int idEnt,InternshipCataloge ic) {
		int idic = es.addInternshipCatalog(ic);
		es.addInternshipCatalogtoEntreprise(idEnt, idic);
		if(idic != 0)
		{
			return Response.status(Status.CREATED).entity("InternshipCatalog added Successful").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE).entity("InternshipCatalog added Failed ").build();
	}

	@GET
	@Path("InternshipCatalog/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInternshipCatalogDetail(@PathParam("id") int idCat)
	{
		return Response.status(Status.ACCEPTED).entity(es.getInternshipCatalaogeDetails(idCat)).build();
	}

	@PUT
	@Path("updateInternshipCatalog")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInternshipCatalog(InternshipCataloge ic) {
		es.updateInternshipCatalog(ic);
		return Response.status(Status.ACCEPTED).entity("Successful InternshipCataloge Update").build();
	}

	@POST
	@Path("responsable")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEnseignant(ResponsableEntreprise re) {
		Set<ValidationError> violations=us.addUser(re);
		if (violations==null) {
			return Response.status(Status.CREATED).entity("add successful").build();
		}
		else return Response.status(Status.INTERNAL_SERVER_ERROR).entity(violations).build();
	}
}
