package tn.esprit.webServices;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.pfe.entities.Soutenance;
import tn.esprit.pfe.interfaces.SoutenanceServiceRemote;

@Path("/soutenance")
public class SoutenanceWebService {
	@EJB
	SoutenanceServiceRemote rst;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void addReclamation(Soutenance s) {
		rst.addSoutenance(s);	
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addNote(Soutenance s ) {	
		rst.ajouterNote(s);

	}
	

	
	@Path("/note/nbrNote")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<List<Long>,List<Long>>  NombreSoutenanceNoteEtNonNote() {
	
	   return rst.SoutenanceNoteEtNonNote();
		
	}
	
	@Path("/note/moyenne")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Double>  MoyenneNote() {
	
	   return rst.MoyenneNote();
		
	}
	
	
	
	
	
}