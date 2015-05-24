package vaermail;

import javax.activation.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableAsync
@EnableScheduling
public class ScheduledMailSenderConfig {
    @Bean
    public DataSource dataSource() {
		return null;
        // instantiate, configure and return DataSource
    }
}