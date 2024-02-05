package org.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatusPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"root\"]")
    private WebElement loginMessageElement;

    public StatusPage ( WebDriver webDriver ) {
        super ( webDriver );
    }
    /**
     * StatusPage extends BasePage, handling the retrieval of login status messages.

     * getLoginMessage: Waits for the 'status' URL, then extracts the login message.
     * If the URL doesn't update timely, it prompts a retry message.
     * This class ensures accurate login status communication, aiding in process verification.
     */

    public String getLoginMessage () {

        if (waitForUrl ( "status" , true ,30)) {
            return getTextFromElement ( loginMessageElement );

        }
        return "Did not find login message trying again!";
    }


}