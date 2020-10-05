package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.model.dto.WineDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

/**
 * @author : Vladimir Alexeev
 * @since : 05.10.2020
 **/

@ExtendWith(MockitoExtension.class)
public class AmParserServiceImplTest {

    @InjectMocks
    private AmParserServiceImpl amParserServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parsePageTest() throws IOException {
        Document document1 = Jsoup.connect("https://amwine.ru/catalog/vino/").get();
        List<WineDto> wineDtoList = amParserServiceImpl.parsePage(document1);
        Assert.assertEquals(18, wineDtoList.size());
    }

    @Test
    public void parseCatalogTest(){
    }
}
