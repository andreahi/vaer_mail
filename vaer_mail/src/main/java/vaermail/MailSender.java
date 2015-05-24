package vaermail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MailSender  {
	final static String MAIL_PASSWORD = "vaermail";
	final static String USER_NAME = "vaermail1@gmail.com";
	final static String SMTP = "smtp.gmail.com";
	public static void Send(String to, String messageText, String subject) throws MessagingException
	{    

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", SMTP);
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_NAME, MAIL_PASSWORD);
			}
		});

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(USER_NAME));
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
		msg.setSubject(subject);
		msg.setText(messageText);

		Transport.send(msg);

	}


}
