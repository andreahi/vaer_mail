package vaermail;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {MailSender.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MailUnitTest {

	
	 @Test
	  public void testMailSender() throws Exception {
		 MailSender.Send("vaermail1@gmail.com", "messageText", "subject");
			
	  }
	
	
}
