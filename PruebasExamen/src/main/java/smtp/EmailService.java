package smtp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailService {
	private String user = null;
	private String pass = null;
	
	private String smtp_host = null;
	private int smtp_port =0;
	
	private EmailService() {
		
	}
	
	public static void main(String[] args) {
		String user = "juliocesar.silvadi@elorrieta-errekamari.com";
		String pass = "juliocesar.silvadi";
		String to = "amandaeescorsin@gmail.com";
		String subject = "prohibicion mundial del sexo";
		String message ="Ja ta podendo gozar!!!!!!";
		
		EmailService emailService = new EmailService(user, pass, "smtp.gmail.com", 465);
		try {
			emailService.sendMail(to, subject, message);
			System.out.println("Ok, mail sent!");
		}catch(Exception e) {
			System.out.println("Error!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public EmailService(String user, String pass, String host, int port) {
		this.user = user;
		this.pass = pass;
		this.smtp_host = host;
		this.smtp_port = port;
	}
	
	public void sendMail(String receiver, String subject, String text) throws AddressException, MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", smtp_host);
		properties.put("mail.smtp.port", smtp_port);
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.ssl.trust", smtp_host);
		properties.put("mail.imap.partialfetch", false);
		
		Session session = Session .getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
		message.setSubject(subject);
		
		Multipart multipart = new MimeMultipart();
		
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(text, "text/html");
		multipart.addBodyPart(mimeBodyPart);
		
		message.setContent(multipart);
		Transport.send(message);
		
		
	}
}
