package ma.fstm.ilisi.projet.model.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.bson.types.ObjectId;

import ma.fstm.ilisi.projet.model.bo.*;
import ma.fstm.ilisi.projet.model.dao.DAODiagnostic;


public class SendEmail1 {

	Diagnostic diagnostic;
	
		public SendEmail1(Diagnostic diagnostic) {
			super();
			this.diagnostic = diagnostic;
		}
		public  void EnvoiEmail(String recepient)
		{
			
			Properties properties = new Properties();
	        System.out.println("preparing Email to send it");
	        
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");
	        
	        final String myAccountEmail = "doheny5000@gmail.com";
	        final String mypassword = "khadija12348765";
	        
	        
	        Session session = Session.getInstance(properties,
	        		new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(myAccountEmail,mypassword); 
	            
	            }
	        });
	        
	        Message message = prepareMessage(session,myAccountEmail,recepient);
	        
	        try {
	            Transport.send(message);
	        } catch (MessagingException ex) {
	        	ex.printStackTrace();
	        	System.out.println("magalohach!!!");
	        }
	   
	        System.out.println("Email send successfuly !!");
	        //this.diagnostic.setSent(1);
		}

		private  Message prepareMessage(Session session, String myAccountEmail, String recepient) {
			 
	        try{

	            Message message = new MimeMessage(session);

	            message.setFrom(new InternetAddress(myAccountEmail));
	            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

	            message.setSubject("A dangerous case it has been detected");
	            String msg="";
	            msg+="Patient : "+this.diagnostic.getPatient().getNom()+" "+this.diagnostic.getPatient().getPrenom()+"\n";
	            //message.setText();
	            if(this.diagnostic.getSymptomes().size()>0)
	           msg+="Ses Symptomes : " + this.diagnostic.getSymptomes()+"\n\n";
	            if(this.diagnostic.getMaladiesC().size()>0)
	            	msg+="Ses maladies chroniques : "+ this.diagnostic.getMaladiesC();
	            msg+="Le pourcentage de présence du covid 19 est : "+this.diagnostic.getPossi_presence()*100+" % \n";
	            msg+="\n\nthis application made by the innovators of ilisi 20/23";
	            message.setText(msg);
	            return message;

	        }catch(MessagingException ex){
	        	ex.printStackTrace();
	        	System.out.println("wayliiiii");
	        }
			return null;
		}
		
		
		public static void main(String[] args) {
			Diagnostic diq=new DAODiagnostic().AfficherDiagnostic(new ObjectId("624ae80fd81baf30cb2b37f7"));
			diq.fireAll();
		}

		
	}