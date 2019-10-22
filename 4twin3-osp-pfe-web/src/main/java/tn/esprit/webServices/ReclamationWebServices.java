package tn.esprit.webServices;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	public void addReclamation(Reclamation rec) {
		rst.addReclamation(rec);	
	}
}
