/**
 * 
 */
package r4f.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import r4f.model.ErrorMessage;
import r4f.model.User;

/**
 * @author Ture Class that sends emails to the customers or the shop owner
 */
public class EmailService {

	private Properties mailServerProperties;
	private Session getMailSession;
	private MimeMessage generateMailMessage;
	private final String mailserver = "smtp.gmail.com";
	private final String emailaddress = "webshop.run4fun@gmail.com";
	private final String password = "Run4Fun99";

	/**
	 * Constructor that sets the server properties
	 */
	public EmailService() {
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
	}

	/**
	 * This method sends a registration confirmation to a user
	 * 
	 * @param user
	 *            the user who should receive the confirmation
	 */
	public void sendRegistrationConfirmation(User user) {
		try {
			String emailBody = getEmailBody("registrationConfirmation");
			emailBody = emailBody.replaceAll("firstName", user.getFirstName());
			emailBody = emailBody.replaceAll("lastName", user.getLastName());

			sendMail(user.getEmail(), "Registrierung bei Run4Fun", emailBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method reads the email body for an email from a file.
	 * 
	 * @param email
	 *            The email for which the body should be returned.
	 *            "registrationConfirmation" for the body of the registration
	 *            confirmation
	 * @return the body of the email
	 * @throws IOException
	 *             throws an IOException if there was any problem with reading
	 *             the file of the body
	 */
	private String getEmailBody(String email) throws IOException {
		BufferedReader bReader;
		String filename = "";

		switch (email) {
		case "registrationConfirmation":
			filename = "RegistrationConfirmationEmail.txt";
			break;
		}
		bReader = new BufferedReader(new InputStreamReader(EmailService.class.getResourceAsStream(filename)));
		String line = "";
		String body = "";
		boolean temp = true;
		while ((line = bReader.readLine()) != null && temp) {
			body = body + line;
		}
		bReader.close();

		return body;
	}

	/**
	 * This method sends an email
	 * 
	 * @param recipient
	 *            the email address of the recipient
	 * @param subject
	 *            the subject of the email
	 * @param emailBody
	 *            the body of the email
	 */
	private void sendMail(String recipient, String subject, String emailBody) {
		try {
			// establishing Mailsession with recipient and subject
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			generateMailMessage.setSubject(subject);

			// setting the Email text

			generateMailMessage.setContent(emailBody, "text/html");

			// transport of the email
			Transport transport = getMailSession.getTransport("smtp");
			transport.connect(mailserver, emailaddress, password);
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}