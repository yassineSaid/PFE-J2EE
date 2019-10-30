package tn.esprit.webServices;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import rest.utilities.authentication.AuthenticationFilter;
import rest.utilities.authentication.Secure;
import tn.esprit.pfe.entities.Categorie;
import tn.esprit.pfe.entities.ForumQuestion;
import tn.esprit.pfe.interfaces.QuestionServiceRemote;

@Path("Question")
public class QuestionWebService {
	@EJB
	QuestionServiceRemote qsr;
	@Context
	private HttpHeaders headers;
	
	
	@POST
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addQuestion(ForumQuestion Q) {
		//AuthenticationFilter af=new AuthenticationFilter();
		qsr.addQuestion(Q/*,af.getIdUser(headers)*/);
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllquestion()
	{
		List<ForumQuestion> q=qsr.getAllquestion();
		return Response.status(Status.ACCEPTED).entity(q).build();
	}
	
	@GET
	//@Secure(role={"etudiant"})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id_Question}/{question_resolu}")
	public void Mettreajourquestion(@PathParam("Question_resolu") boolean question_resolu ,@PathParam("id_Question") int idquestion) {
		qsr.MetreajourQuestion(question_resolu, idquestion);
		

	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void deletequestion(@PathParam("id") int idquestion) {
	 qsr.deleteQuestion(idquestion);	}

}
