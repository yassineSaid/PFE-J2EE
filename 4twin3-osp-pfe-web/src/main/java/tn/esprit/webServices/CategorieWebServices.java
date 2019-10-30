package tn.esprit.webServices;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.pfe.entities.Categorie;
import tn.esprit.pfe.interfaces.CategorieServiceRemote;


@RequestScoped
@Path("Categorie")
public class CategorieWebServices {

	@EJB
	CategorieServiceRemote csr;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addCategorie(Categorie c) {
		csr.addCategorie(c);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCat(@PathParam("id") int idcat) {
	 csr.deleteCategorie(idcat);
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllcategorie()
	{
		List<Categorie> c=csr.getAllcategorie();
		return Response.status(Status.ACCEPTED).entity(c).build();
	}
	@GET
	@Path("/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public void rendrecatcommemodule(@PathParam("name") String name) {
	 csr.addCategoriecommemodule(name);
	}
	@GET
	@Path("/catprefere/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getcatprefere(@PathParam("id") int idens )
	{
		List<Categorie> c=csr.getCategorielesplusdemandées(idens);
		return Response.status(Status.ACCEPTED).entity(c).build();
	}
}
