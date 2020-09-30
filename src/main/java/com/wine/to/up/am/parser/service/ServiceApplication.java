package com.wine.to.up.am.parser.service;

import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.service.AmClient;
import com.wine.to.up.am.parser.service.service.impl.AmClientStub;
import com.wine.to.up.am.parser.service.service.impl.AmParserServiceStub;
import com.wine.to.up.am.parser.service.service.impl.AmWineServiceStub;
import com.wine.to.up.am.parser.service.service.impl.JsoupAmClientImpl;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@ComponentScan("com.wine.to.up")
@EnableSwagger2
public class ServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ServiceApplication.class, args);

        List<WineDto> wineDtos = context.getBean(AmParserServiceStub.class).parsePage(context.getBean(AmClientStub.class).getMainPage());
        System.out.println(wineDtos);
    }
}
