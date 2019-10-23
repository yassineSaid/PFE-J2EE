package tn.esprit.webServices;

import java.util.List;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pfe.entities.Enseignant;
import tn.esprit.pfe.entities.User;
import tn.esprit.pfe.services.EnseignantService;
import tn.esprit.pfe.services.UserService;
import utilities.ValidationError;

@Path("enseignant")
@RequestScoped
public class EnseignantWebServices {

	@Inject
	EnseignantService es;
	@EJB
	UserService us;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEnseignant(Enseignant e) {
		Set<ValidationError> violations=us.addUser(e);
		if (violations==null) {
			return Response.status(Status.CREATED).entity("add successful").build();
		}
		else return Response.status(Status.INTERNAL_SERVER_ERROR).entity(violations).build();
	}
	
	
	
	/*@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void updateReclamation(Reclamation rec) {	
		rst.updateReclamation(rec);

	}
	
	
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("deleteRec/{idReclamation}")
	public void deleteClient(@QueryParam("idReclamation") int idRec) {
	 rst.deleteReclamation(idRec);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getReclamationByEtudiant")
	public List<Reclamation> getReclamation(@QueryParam("etudiant_id") int ide){
		return rst.getReclamationByEtudiant(ide);
	}*/
	

}
