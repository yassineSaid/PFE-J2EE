package tn.esprit.webServices;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tn.esprit.pfe.entities.Reclamation;
import tn.esprit.pfe.interfaces.ReclamationServiceRemote;

@Path("/reclamation")
@ManagedBean
public class ReclamationWebServices {

	

	
	@EJB
	ReclamationServiceRemote rst;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("addRec")
	public void addReclamation(Reclamation rec) {
		rst.addReclamation(rec);	
	}
	
	
	
	@PUT
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
	}
	

}
