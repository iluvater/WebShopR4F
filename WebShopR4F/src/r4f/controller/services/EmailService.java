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

import r4f.model.Order;
import r4f.model.OrderItem;
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
			emailBody = emailBody.replaceAll("!firstName!", user.getFirstName());
			emailBody = emailBody.replaceAll("!lastName!", user.getLastName());
			emailBody = emailBody.replaceAll("!code!", user.getConfirmationCode());

			sendMail(user.getEmail(), "Registrierung bei Run4Fun", emailBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendOrderConfirmation(Order order){
		try{
			String emailBody = getEmailBody("orderConfirmation");
			emailBody = emailBody.replaceAll("!orderId!", Integer.toString(order.getId()));
			emailBody = emailBody.replaceAll("!entryDate!", order.getEntryDate().toString());
			emailBody = emailBody.replaceAll("!deliveryDate!", order.getDeliveryDate().toString());
			emailBody = emailBody.replaceAll("!firstName!", order.getUser().getFirstName());
			emailBody = emailBody.replaceAll("!lastName!", order.getUser().getLastName());
			emailBody = emailBody.replaceAll("!street!", order.getDeliveryAddress().getStreet());
			emailBody = emailBody.replaceAll("!houseNumber!", order.getDeliveryAddress().getHouseNumber());
			emailBody = emailBody.replaceAll("!postCode!", order.getDeliveryAddress().getPostCode());
			emailBody = emailBody.replaceAll("!city!", order.getDeliveryAddress().getCity());
			emailBody = emailBody.replaceAll("!paymentMethod!", order.getPaymentMethod());
			emailBody = emailBody.replaceAll("!orderPrice!", Double.toString(order.getTotalPrice()));
			String orderItems = "";
			for (OrderItem item : order.getItems()) {
				orderItems = orderItems + "<tr valign=\"top\"><td width=\"35%\">"+ item.getArticle().getName() + "</td><td width=\"35%\">" + item.getArticle().getPrice() + "€</td></tr>";
			}
			emailBody = emailBody.replaceAll("!orderItems!", orderItems);
			
			sendMail(order.getUser().getEmail(), ("Bestellbestätigung für Bestellung #" + order.getId()), emailBody);
		}catch (IOException e) {
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
		case "orderConfirmation":
			filename = "OrderConfirmationEmail.txt";
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
			// establishing Mail session with recipient and subject
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			generateMailMessage.setSubject(subject, "UTF-8");

			// setting the Email text

			generateMailMessage.setContent(emailBody, "text/html; charset=UTF-8");

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
	
	/**
	 * This method sends a mail to the r4f email address
	 * @param subject the subject of the mail
	 * @param emailBody the body of the mail
	 */
	public void sendContactMail(String subject, String emailBody){
		sendMail(emailaddress, subject, emailBody);
	}

}
