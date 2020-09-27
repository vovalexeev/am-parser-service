package com.wine.to.up.am.parser.service.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GeneralPurposesBeansConfig {
    /**
     * Model mapper bean
     */
    @get:Bean
    val modelMapper: ModelMapper
        get() = ModelMapper()

    /**
     * Object mapper bean
     */
    @get:Bean
    val objectMapper: ObjectMapper
        get() = ObjectMapper()
}