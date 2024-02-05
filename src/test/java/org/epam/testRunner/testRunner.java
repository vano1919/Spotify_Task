package org.epam.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(

        features = "src/test/resources/features",
        glue = {"org.epam.stepdefinitions"}
)
public class testRunner extends AbstractTestNGCucumberTests {
}

