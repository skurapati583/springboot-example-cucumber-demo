package com.cucumber.springboottwo.example.tests;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("PHP Travels Validation")
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class CucumberRunnerTest {
}
