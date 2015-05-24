package vaermail;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {VaerMailApplication.class})
public class HomeControllerIntegrationTest {

	 @Autowired
	  private WebApplicationContext wac;

	  private MockMvc mockMvc;

	  @Before
	  public void setup() {
	    this.mockMvc = webAppContextSetup(this.wac).build();
	  }

	  @Test
	  public void testmailadd() throws Exception {
		  mockMvc.perform(get("/add?mail=test@gmail.com").accept(MediaType.ALL))
          .andExpect(status().isOk());	    
		  
		  mockMvc.perform(get("/add?mail=test(at)gmail.com").accept(MediaType.ALL))
          .andExpect(status().isBadRequest());	
	  
		  mockMvc.perform(get("/add?mail=test(krollalfa)gmail.com").accept(MediaType.ALL))
          .andExpect(status().isBadRequest());	
	  
		  mockMvc.perform(get("/add?mail=test").accept(MediaType.ALL))
          .andExpect(status().isBadRequest());	
	  
		  mockMvc.perform(get("/add?mail=").accept(MediaType.ALL))
          .andExpect(status().isBadRequest());	
	 
		  mockMvc.perform(get("/add?mail=test@mail@gmail.com").accept(MediaType.ALL))
          .andExpect(status().isBadRequest());	
	  }
	  
	  @Test
	  public void testmailget() throws Exception {
		  mockMvc.perform(get("/add?mail=test@gmail.com").accept(MediaType.ALL))
          .andExpect(status().isOk());	    
		  mockMvc.perform(get("/add?mail=test2@gmail.com").accept(MediaType.ALL))
          .andExpect(status().isOk());	    

		  mockMvc.perform(get("/get?id=0").accept(MediaType.ALL))
          .andExpect(status().isOk());	 
		  
		  mockMvc.perform(get("/get?id=1").accept(MediaType.ALL))
          .andExpect(status().isOk());	    
		  mockMvc.perform(get("/get?id=10").accept(MediaType.ALL))
          .andExpect(status().isBadRequest());
	  }
}

