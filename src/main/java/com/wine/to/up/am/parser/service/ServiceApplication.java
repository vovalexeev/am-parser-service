package com.wine.to.up.am.parser.service;

import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.service.impl.AmClientStub;
import com.wine.to.up.am.parser.service.service.impl.AmParserServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.wine.to.up")
@EnableSwagger2
public class ServiceApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(ServiceApplication.class, args);
        File file = new File("/Users/happyline/am-parser-service/src/main/resources", "docum.html");
        Document document = Jsoup.parse(file, null);

        List<WineDto> wineDtos = context.getBean(AmParserServiceImpl.class).parsePage(document);
        System.out.println(wineDtos);
    }

}
