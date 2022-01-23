package hodei.naiz.teammoraleemail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Hodei Eceiza
 * Date: 1/22/2022
 * Time: 22:49
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@Configuration
public class WebClientConf {
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
