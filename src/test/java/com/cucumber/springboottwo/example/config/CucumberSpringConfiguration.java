package com.cucumber.springboottwo.example.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@CucumberContextConfiguration
@DirtiesContext
public class CucumberSpringConfiguration {

}
