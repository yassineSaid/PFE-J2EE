package tn.esprit.pfe.pdf;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import tn.esprit.pfe.entities.InternshipAgreemen;

public class PDF {

	public String generateInternshipAgreemen(InternshipAgreemen internshipAgreemen) throws JRException{
		
		Map<String, Object> params = new HashMap<>();
		JRDataSource jrDataSource = new JREmptyDataSource();
		params.put("nameEntreprise", internshipAgreemen.getEntreprise().getNameEntreprise());
		params.put("nameResponsable", internshipAgreemen.getEntreprise().getNomPrenomResponsable());
		params.put("tel", internshipAgreemen.getEntreprise().getTelResponsable());
		params.put("fax", internshipAgreemen.getEntreprise().getTelEntreprise());		
		params.put("emailEntreprise", internshipAgreemen.getEntreprise().getEmailEntreprise());
		params.put("emailResponsable", internshipAgreemen.getEntreprise().getEmailResponsable());
		params.put("siteweb", internshipAgreemen.getEntreprise().getSiteweb());
		params.put("pays", internshipAgreemen.getEntreprise().getPays());		
		params.put("adresse", internshipAgreemen.getEntreprise().getAdresse());
		params.put("nameEtudiant", internshipAgreemen.getEtudiant().getPrenom() + " "+internshipAgreemen.getEtudiant().getNom());
        params.put("emailEtudiant", internshipAgreemen.getEtudiant().getEmail());
		//InputStream inputStream = this.getClass().getResourceAsStream("Internship.jrxml");
        
        String filename = "Internship"+internshipAgreemen.getId()+".pdf";
        
		JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\lhbya\\git\\4twin3-osp-pfe\\4twin3-osp-pfe-ejb\\src\\main\\resources\\PDF\\Internship.jrxml");
		JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, params ,jrDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\lhbya\\git\\4twin3-osp-pfe\\4twin3-osp-pfe-ejb\\src\\main\\resources\\PDF\\"+filename);

		return filename;
	}
}
