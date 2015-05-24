package vaermail;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {WeatherRetriever.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherRetriverUnitTest {

	
	 @Test
	  public void testGetVaer() throws Exception {
			String url_svalbard = "http://www.yr.no/sted/Norge/Svalbard/Longyearbyen/varsel_time_for_time.xml";
			//System.out.println( WeatherRetriever.get_vaer(url_svalbard));
			assertTrue("weather data returned by 'get_vaer(url)' should not be empty", WeatherRetriever.get_vaer(url_svalbard).length()>0);
			
	  }
	 
	  @Configuration
	    public static class TestConfiguration {
	 
	    }
	
}
