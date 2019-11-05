package tn.esprit.webServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import rest.utilities.ExcelApiTest;
import rest.utilities.authentication.AuthenticationFilter;
import rest.utilities.authentication.Secure;
import tn.esprit.pfe.entities.Admin;
import tn.esprit.pfe.entities.Classe;
import tn.esprit.pfe.entities.Departement;
import tn.esprit.pfe.entities.Ecole;
import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.Site;
import tn.esprit.pfe.entities.Specialite;
import tn.esprit.pfe.services.AdminService;
import tn.esprit.pfe.services.EcoleService;
import tn.esprit.pfe.services.UserService;
import utilities.ValidationError;

@Path("import")
@RequestScoped
public class ImportWebServices {

	@EJB
	UserService us;

	@EJB
	AdminService as;

	@EJB
	EcoleService es;

	@Context
	private HttpHeaders headers;

	private XSSFWorkbook workbook;

	@POST
	@Secure(role = { "Admin" })
	@Consumes("multipart/form-data")
	@Produces(MediaType.APPLICATION_JSON)
	public Response importData(MultipartFormDataInput form) throws Exception {
		try {
			File uploadedInputStream = form.getFormDataPart("file", File.class, null);
			workbook = new XSSFWorkbook(uploadedInputStream);
			ExcelApiTest eat = new ExcelApiTest(uploadedInputStream);
			XSSFSheet sheet = workbook.getSheet("Site");
			Ecole e = new Ecole();
			e.setAdresse(form.getFormDataPart("adresse", String.class, null));
			e.setNom(form.getFormDataPart("nom", String.class, null));
			Set<Site> sites = new HashSet<>();
			for (int i = 2; i < sheet.getLastRowNum()+2; i++) {
				Site s = new Site();
				s.setEcole(e);
				s.setAdresse(eat.getCellData("Site", "Adresse", i));
				s.setNom(eat.getCellData("Site", "Nom", i));
				String id = eat.getCellData("Site", "Id", i);
				Set<Departement> departements = new HashSet<>();
				XSSFSheet sheetDep = workbook.getSheet("Departement");
				System.out.println(sheetDep.getLastRowNum());
				for (int j = 2; j < sheetDep.getLastRowNum()+2; j++) {
					if (eat.getCellData("Departement", "Site", j).equals(id)) {
						Departement d = new Departement();
						d.setSite(s);
						d.setNom(eat.getCellData("Departement", "Nom", j));
						String idDep = eat.getCellData("Departement", "Id", j);
						Set<Specialite> specialites = new HashSet<>();
						XSSFSheet sheetSpec = workbook.getSheet("Specialite");
						for (int k = 2; k < sheetSpec.getLastRowNum()+2; k++) {
							if (eat.getCellData("Specialite", "Departement", k).equals(idDep)) {
								Specialite spec = new Specialite();
								spec.setDepartement(d);
								spec.setNom(eat.getCellData("Specialite", "Nom", k));
								Set<Classe> classes = new HashSet<Classe>();
								String idSpec = eat.getCellData("Specialite", "Id", k);
								XSSFSheet sheetClas = workbook.getSheet("Classe");
								for (int h=2; h < sheetClas.getLastRowNum()+2; h++) {
									if (eat.getCellData("Classe", "Specialite", h).equals(idSpec)) {
										Classe c = new Classe();
										c.setAnneeDeDebut(Calendar.getInstance().get(Calendar.YEAR));
										c.setNumero((int) Double.parseDouble(eat.getCellData("Classe", "Numero", h)));
										Set<Etudiant> etudiants = new HashSet<Etudiant>();
										String idClas = eat.getCellData("Classe", "Id", k);
										XSSFSheet sheetEtud = workbook.getSheet("Etudiant");
										for (int l=0;l<sheetEtud.getLastRowNum()+2;l++) {
											if (eat.getCellData("Etudiant", "Classe", l).equals(idClas)) {
												Etudiant et = new Etudiant();
												et.setClasse(c);
												et.setEmail(eat.getCellData("Etudiant", "Email", l));
												et.setPrenom(eat.getCellData("Etudiant", "Prenom", l));
												et.setNom(eat.getCellData("Etudiant", "Nom", l));
												et.setPlainPassword(eat.getCellData("Etudiant", "Identifiant", l));
												et.setIdentifiant(eat.getCellData("Etudiant", "Identifiant", l));
												etudiants.add(et);
											}
										}
										c.setEtudiants(etudiants);
										c.setSpecialite(spec);
										classes.add(c);
									}
								}
								spec.setClasses(classes);
								specialites.add(spec);
							}
						}
						d.setSpecialites(specialites);
						departements.add(d);
					}
				}
				s.setDepartements(departements);
				sites.add(s);
			}
			e.setSites(sites);
			AuthenticationFilter af=new AuthenticationFilter();
			
			return Response.status(Status.OK).entity(es.addEcole(e, af.getIdUser(headers))).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

}
