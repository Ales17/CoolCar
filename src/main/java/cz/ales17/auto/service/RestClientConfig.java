package cz.ales17.auto.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Value("${API_KEY}")
    private String API_KEY;
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://api.dataovozidlech.cz/api/vehicletechnicaldata")
                .defaultHeader("api_key", API_KEY)
                .build();
    }
}
