package com.wine.to.up.am.parser.service.controller;


import com.wine.to.up.am.parser.service.parse.client.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller of the service
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/parse")
@Validated
@Slf4j
public class ParseController {

    private Client httpClient;

    @Autowired
    public ParseController(Client client) {
        this.httpClient = client;
    }

    @GetMapping
    public String getPage() {
        //var client = new Client("https://www.amwine.ru/catalog/vino", "Chrome/4.0.249.0 Safari/532.5");
        var doc = httpClient.getDocumentByPath("");
        return doc.toString();
    }
}
