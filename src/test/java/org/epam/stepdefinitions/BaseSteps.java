package org.epam.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.epam.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class BaseSteps {
    public static final Map<String, String> PAGES_STORAGE = new HashMap<> ( );
    public static WebDriver webDriver;

    @Before
    public void initWebDriver () {
        webDriver = new WebDriverFactory ( ).getWebDriver ( );
        webDriver.manage ( ).window ( ).maximize ( );
    }

    @After
    public void afterScenario () {
        try {
         // Added this part for reviewer to see actual result on page
            Thread.sleep ( 3000 );
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }

        webDriver.quit ( );
        PAGES_STORAGE.clear ( );
    }

}
