package tn.esprit.pfe.email;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.pfe.entities.EtatSheetPFE;
import tn.esprit.pfe.entities.Etudiant;
import tn.esprit.pfe.entities.InternshipAgreemen;
import tn.esprit.pfe.entities.SheetPFE;

@Stateless
public class Email {
	// @Resource(lookup = "java:jboss/mail/Default")
	private Session session;

	public void sendQRCodeSheetPFE(SheetPFE sheetPFE) throws NamingException, AddressException, MessagingException {

		InitialContext ic = new InitialContext();
		session = (Session) ic.lookup("java:jboss/mail/Default");
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sheetPFE.getEtudiant().getEmail()));
		message.setSubject("QRCode Sheet PFE");

		Multipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		String content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
				+ "<br><br>" + "<p>\r\n"
				+ "It's QRCode will allow you to see the progress of the processing of the sheet PFE.</p>";

		messageBodyPart.setContent(content, "text/html");

		multipart.addBodyPart(messageBodyPart);

		messageBodyPart = new MimeBodyPart();

		String filename = "C:\\Users\\lhbya\\git\\4twin3-osp-pfe\\4twin3-osp-pfe-ejb\\src\\main\\resources\\QRCode\\"
				+ sheetPFE.getQrcode() + ".png";
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);

		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);
	}

	public void sendAgreemen(InternshipAgreemen internshipAgreemen)
			throws NamingException, AddressException, MessagingException {

		InitialContext ic = new InitialContext();
		session = (Session) ic.lookup("java:jboss/mail/Default");
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(internshipAgreemen.getEtudiant().getEmail()));
		message.setSubject("Internship agreement");

		Multipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		String content = "<h3>Hello,</h3>" + internshipAgreemen.getEtudiant().getPrenom() + " "
				+ internshipAgreemen.getEtudiant().getNom() + "<br><br>" + "<p>\r\n"
				+ "Please you should come to the internship management to retrieve the agreement signed by ESPRIT.</p>";

		messageBodyPart.setContent(content, "text/html");

		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);
	}

	public void progressSheetPFE(SheetPFE sheetPFE) throws NamingException, AddressException, MessagingException {

		InitialContext ic = new InitialContext();
		session = (Session) ic.lookup("java:jboss/mail/Default");
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sheetPFE.getEtudiant().getEmail()));
		message.setSubject("Progress of sheet PFE");

		Multipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		String content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
				+ "<br><br>" + "<p>\r\n" + "Your sheet PFE being processed.</p>";

		messageBodyPart.setContent(content, "text/html");

		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);
	}

	public void etatSheetPFE(SheetPFE sheetPFE) throws NamingException, AddressException, MessagingException {

		InitialContext ic = new InitialContext();
		session = (Session) ic.lookup("java:jboss/mail/Default");
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sheetPFE.getEtudiant().getEmail()));

		if (sheetPFE.getEtat().equals(EtatSheetPFE.CANCEL)) {
			message.setSubject("Internship Cancel");
		} else if (sheetPFE.getEtat().equals(EtatSheetPFE.REFUSE)) {
			message.setSubject("Sheet PFE Refuse");
		} else {
			message.setSubject("Sheet PFE Accepted");
		}

		Multipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		String content = "";

		if (sheetPFE.getEtat().equals(EtatSheetPFE.CANCEL)) {
			content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
					+ "<br><br>" + "<p>\r\n" + "Internship CANCEL.</p>";
		} else if (sheetPFE.getEtat().equals(EtatSheetPFE.REFUSE)) {
			content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
					+ "<br><br>" + "<p>\r\n"
					+ "Sorry, your sheet PFE <span style=\"color:red\">REFUSE</span>.</p><p>Problem of refusing <br>"
					+ sheetPFE.getNote() + "</p>";
		} else if (sheetPFE.getEtat().equals(EtatSheetPFE.ACCEPTED)) {
			content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
					+ "<br><br>" + "<p>\r\n" + "Sorry, your sheet PFE ACCEPTED.</p>";

		} 

		messageBodyPart.setContent(content, "text/html");

		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);
	}

	public void entrepriseNotExist(SheetPFE sheetPFE) throws NamingException, AddressException, MessagingException {

		InitialContext ic = new InitialContext();
		session = (Session) ic.lookup("java:jboss/mail/Default");
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sheetPFE.getEtudiant().getEmail()));

		message.setSubject("Sheet PFE Refuse");

		Multipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		String content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
				+ "<br><br>" + "<p>\r\n"
				+ "Sorry, your sheet PFE Refuse.</p><p>Problem of refusing <br>"
				+ sheetPFE.getNote() + "</p>";

		messageBodyPart.setContent(content, "text/html");

		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);
	}

	public void requestCancelInternship(SheetPFE sheetPFE) throws NamingException, AddressException, MessagingException {

		InitialContext ic = new InitialContext();
		session = (Session) ic.lookup("java:jboss/mail/Default");
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sheetPFE.getEtudiant().getEmail()));

		if(sheetPFE.getRequest().getEtat().equals(EtatSheetPFE.ACCEPTED)) {
			   message.setSubject("Internship CANCEL");

		}else {
			   message.setSubject("Your request Refuse");
		}

		Multipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		String content = "";
		if(sheetPFE.getRequest().getEtat().equals(EtatSheetPFE.ACCEPTED)) {
			 content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
					+ "<br><br>" + "<p>\r\n" + "Your request to cancel the internship accepted.</p>";
		}else {
			 content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
					+ "<br><br>" + "<p>\r\n" + "Your request to cancel the internship has been refused.</p>";

			
		}
		
		messageBodyPart.setContent(content, "text/html");

		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);
	}
	
	public void reminderStudent(Etudiant etudiant) throws NamingException, MessagingException {
		
		InitialContext ic = new InitialContext();
		session = (Session) ic.lookup("java:jboss/mail/Default");
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(etudiant.getEmail()));

		message.setSubject("Reminder to create sheet PFE");

		Multipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		String content = "<h3>Hello,</h3>" + etudiant.getPrenom() + " " + etudiant.getNom()
				+ "<br><br>" + "<p>\r\n"
				+ "Your must create a sheet PFE</p>";

		messageBodyPart.setContent(content, "text/html");

		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);
	}
	
	public void validateSheetPFE(SheetPFE sheetPFE,EtatSheetPFE etat, String note) throws NamingException, AddressException, MessagingException {
		
		InitialContext ic = new InitialContext();
		session = (Session) ic.lookup("java:jboss/mail/Default");
		Message message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sheetPFE.getEtudiant().getEmail()));

		if(etat.equals(EtatSheetPFE.VALIDATE)) {
			   message.setSubject("Validated Sheet PFE");
		}else {
			
			   message.setSubject("Refused subject PFE");
		}

		Multipart multipart = new MimeMultipart();

		BodyPart messageBodyPart = new MimeBodyPart();

		String content = "";
		if(etat.equals(EtatSheetPFE.VALIDATE)) {
			 content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
					+ "<br><br>" + "<p>\r\n" + "Your subject of PFE has been accepted.</p>";
		}else {
			 content = "<h3>Hello,</h3>" + sheetPFE.getEtudiant().getPrenom() + " " + sheetPFE.getEtudiant().getNom()
					+ "<br><br>" + "<p>\r\n" + "Your subject of PFE has been refused</p><p><br>"+note+"</p>";

		}
		
		messageBodyPart.setContent(content, "text/html");

		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);

		Transport.send(message);
		
	}
	
	

}
