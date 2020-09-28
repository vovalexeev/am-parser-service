package com.wine.to.up.am.parser.service.parse;

import com.wine.to.up.am.parser.service.domain.entity.Wine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller of the service
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
@Validated
@Slf4j
public class Parser {

    @GetMapping
    public List<Wine> getWines() {
        return new ArrayList<>();
    }
}
