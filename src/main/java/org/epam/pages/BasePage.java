package org.epam.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {


    protected static WebDriver webDriver;
    protected static Actions actions;


    protected BasePage ( WebDriver webDriver ) {
        BasePage.webDriver = webDriver;

        actions = new Actions ( webDriver );

        PageFactory.initElements ( webDriver , this );
    }


    /**
     * This abstract BasePage class serves as a foundation for page objects, providing common Selenium WebDriver operations.
     * Special attention is given to handling pages with unconventional behavior, like Spotify, necessitating bespoke solutions:

     * 1. waitForElementCondition: A generic method to wait for any condition on an element, enhancing code reusability and readability.

     * 2. waitForUrl: Custom method to wait until the URL contains (or does not contain) a specific text. This is part of our strategy
     *    to implement explicit waits based on URL changes, avoiding the pitfalls of implicit waits and ensuring more reliable and
     *    predictable Selenium interactions.

     * 3. getTextFromElement: Addresses challenges with text retrieval from elements in pages with dynamic content (like Spotify).
     *    It includes a retry mechanism, refreshing the page when necessary, to overcome issues related to AJAX or JavaScript-heavy pages
     *    where elements might not be immediately visible or interactable.

     * 4. clearFieldUsingKey: A specific workaround for issues encountered on the Spotify page, where input fields require character entry
     *    before deletion to trigger certain behaviors (e.g., display of error messages). This method ensures a character is entered before
     *    it's deleted, aligning with the page's unique interaction model.

     * Overall, this class embodies a tailored approach to handling specific, non-standard behaviors of web pages, ensuring robustness and
     * stability in automated web interactions.
     */


    public static WebElement waitForElementCondition ( ExpectedCondition<WebElement> condition , int timeout ) {
        WebDriverWait wait = new WebDriverWait ( webDriver , Duration.ofSeconds ( timeout ) );
        return wait.until ( condition );
    }


    public boolean waitForUrl ( String expectedUrlPart , boolean shouldContain ,int duration) {
        WebDriverWait wait = new WebDriverWait ( webDriver , Duration.ofSeconds ( duration ) );

        try {
            if (shouldContain) {
                return wait.until ( ExpectedConditions.urlContains ( expectedUrlPart ) );
            } else {
                return wait.until ( ExpectedConditions.not ( ExpectedConditions.urlContains ( expectedUrlPart ) ) );
            }
        } catch (Exception ignored) {
            return !shouldContain;
        }


    }

    public String getTextFromElement ( WebElement element ) {
        int maxRetries = 15;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                return waitForElementCondition ( ExpectedConditions.visibilityOf ( element ) , 5 ).getText ( );
            } catch (Exception e) {
                retryCount++;
                webDriver.navigate ( ).refresh ( );
            }
        }
        return "";
    }


    public void clearFieldUsingKey ( WebElement element ) {
        waitForElementCondition ( ExpectedConditions.visibilityOf ( element ) , 10 ).sendKeys ( "1" , Keys.BACK_SPACE );
    }


}