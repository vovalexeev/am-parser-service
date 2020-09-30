package com.wine.to.up.am.parser.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tests {



    @Before
    public void init() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ServiceApplication.class);
    }

    @Test
    public void test() throws IOException {
        File file = new File("/Users/happyline/am-parser-service/src/main/resources", "docum.html");
        Document document = Jsoup.parse(file, null);

    }
}
