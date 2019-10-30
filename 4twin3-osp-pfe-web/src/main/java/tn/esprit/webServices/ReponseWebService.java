package tn.esprit.webServices;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.ForumQuestion;
import tn.esprit.pfe.entities.ForumReponse;
import tn.esprit.pfe.entities.User;
import tn.esprit.pfe.interfaces.ReponseServiceRemote;
import tn.esprit.pfe.services.ReponseServices;

@Path("Reponse")
public class ReponseWebService {
	@EJB
	ReponseServiceRemote qsr;
	
	
	//@POST
	//@Secured
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
     //public Response addComment(ForumReponse r,@QueryParam(value="id")int id) {
		//Etudiant e = (Etudiant) AuthenticationRessource.LoggedPerson;
		//Student s = ps.findUserbyid(LoggedPerson.getId());
		//ps.affectrUserToPost(LoggedPerson.getId(), p);
		//cp.setS(s); 
		//cpS.ajouterComment(cp, id);
		//User u = new User();
	    //u.setId(id);
	    //r.setEtudiant(u);
		//return Response.status(Status.CREATED).entity(cpS).build();

	//}


}
