package com.wine.to.up.am.parser.service.configuration;

import com.wine.to.up.am.parser.service.parse.client.Client;

import com.wine.to.up.am.parser.service.parse.service.ParseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:am-service.properties")
public class ParserConfiguration {
    @Value("${site.base.url}")
    private String baseUrl;
    @Value("${site.user.agent}")
    private String userAgent;

    @Bean
    public Client client() {
        return new Client(baseUrl, userAgent);
    }

    @Bean
    public ParseService parseService(Client client) {
        return new ParseService(client);
    }
}
