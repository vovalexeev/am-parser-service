package com.wine.to.up.am.parser.service.service.impl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Vladimir Alexeev
 * @since : 05.10.2020
 **/

@ExtendWith(MockitoExtension.class)
public class AmClientStubTest {

    @InjectMocks
    private AmClientStub amClientStub;

    private String baseUrl = "https://amwine.ru/catalog/vino/";
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    private String referrer = "https://www.google.com/";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMainPageTestNullDocument(){
        Document document = amClientStub.getMainPage();
        assertNotNull(document);
    }

    @Test
    public void getPageTestConnection() throws IOException {
        Connection con = Jsoup
                .connect(baseUrl)
                .userAgent(userAgent)
                .referrer(referrer);
        Connection.Response res = con.execute();
        assertEquals(200, res.statusCode());
    }
}
