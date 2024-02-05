package org.epam.pages;

import org.epam.enumeration.pageLinksEnum.LinksEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='login-username']")
    private WebElement loginInputFieldElement;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordInputFieldElement;

    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement loginButtonElement;

    @FindBy(xpath = "//div[@data-encore-id=\"banner\"]/span[contains(@class, 'Message-sc-15vkh7g-0')]")
    private WebElement wrongCredentialsErrorMessageElement;

    @FindBy(xpath = "//*[@id=\"username-error\"]")
    private WebElement emptyUsernameErrorMessageElement;

    @FindBy(xpath = "//*[@id=\"password-error\"]")
    private WebElement emptyPasswordErrorMessageElement;

    public LoginPage ( WebDriver webDriver ) {
        super ( webDriver );
    }

    public void open () {
        webDriver.get ( LinksEnum.LOGIN_PAGE_URL.getUrl ( ) );
    }

    /**
     * The LoginPage class, extending BasePage, is tailored for handling the
     * login process on websites with erratic behaviors, such as Spotify.

     * 1. loginInToSpotify: Manages login by repeatedly attempting to click the
     * login button until it recognizes a change in the page, addressing issues
     * where the button doesn't respond on the first click.

     * 2. loginWithEmptyCredentialsToSpotify: Clears username and password fields
     * to test the login validation mechanisms of the website.

     * 3. getEmptyUsernameErrorMessage, getEmptyPasswordErrorMessage, getWrongCredentialsErrorMessage:
     * Retrieves specific error messages from the login page, aiding in the validation of the UI's response to user input.

     * Overall, the class provides specialized methods to ensure reliable interactions with the login
     * functionalities of web pages, especially where standard practices fall short.
     */


    public void loginInToSpotify ( String login , String password ) {
        WebElement loginField = waitForElementCondition ( ExpectedConditions.visibilityOf ( loginInputFieldElement ),5 );
        loginField.clear ( );
        passwordInputFieldElement.clear ( );

        loginField.sendKeys ( login );
        passwordInputFieldElement.sendKeys ( password );

        do {
            try {
                loginButtonElement.click ( );
            } catch (Exception ignored) {
            }

            try {

                String wrongCredentialsMessage =  wrongCredentialsErrorMessageElement.getText ( );
                if (Objects.equals ( wrongCredentialsMessage , "Incorrect username or password." )) {
                    break;
                }

            } catch (Exception ignored) {
            }
        } while (webDriver.getCurrentUrl ( ).contains ( "login" ));
    }

    public void loginWithEmptyCredentialsToSpotify () {
        clearFieldUsingKey ( loginInputFieldElement );
        clearFieldUsingKey ( passwordInputFieldElement );
    }

    public String getEmptyUsernameErrorMessage () {
        return getTextFromElement ( emptyUsernameErrorMessageElement );
    }

    public String getEmptyPasswordErrorMessage () {
        return getTextFromElement ( emptyPasswordErrorMessageElement );
    }

    public String getWrongCredentialsErrorMessage () {
        return getTextFromElement ( wrongCredentialsErrorMessageElement );
    }

}

