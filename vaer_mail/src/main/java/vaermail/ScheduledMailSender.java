package vaermail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

@Component
public class ScheduledMailSender 
{
	//private final String url_lillehammer = "http://www.yr.no/sted/Norge/Oppland/Lillehammer/Lillehammer/varsel.xml";
	private final String url_svalbard = "http://www.yr.no/sted/Norge/Svalbard/Longyearbyen/varsel_time_for_time.xml";
	private final String title = "Vær Svalbard";
	private static final Logger logger = LoggerFactory.getLogger(ScheduledMailSender.class);
	//private final int HOUR = 3600000;
	private final int MINUTE = 60000;

	MailingList personService;

	@Autowired
	public ScheduledMailSender(MailingList personService) {
		this.personService = personService;
	}

	@Scheduled(fixedRate=60*MINUTE)
	public void timedMailSender() throws MalformedURLException, SAXException, IOException, ParserConfigurationException 
	{
		System.out.println("Scheduled method executed");
		int count = 0;
		for (MailInfo mail : personService.getList()) {

			String msg = WeatherRetriever.get_vaer(url_svalbard);
			if(msg.equals("")){
				logger.error("scheduled mail to " + mail + " failed because weatherRetriever returned empty");
				continue;
			}
			try {
				MailSender.Send(mail.getMail(), msg, title);
				count++;
				logger.info("Scheduled mail sendt to : " + mail);
			} catch (MessagingException e) {
				logger.info("Invalid email address in list : " + mail);
			}


		}

		logger.info("Scheduled mail sender finished, nr of sent mails: " + count);
	}

}