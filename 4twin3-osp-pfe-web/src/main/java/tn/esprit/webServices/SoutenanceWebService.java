package tn.esprit.webServices;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.pfe.entities.Soutenance;
import tn.esprit.pfe.interfaces.SoutenanceServiceRemote;

@Path("soutenance")
public class SoutenanceWebService {
	@EJB
	SoutenanceServiceRemote rst;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void addReclamation(Soutenance s) {
		rst.addSoutenance(s);	
	}
	
}
