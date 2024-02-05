package org.epam.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.epam.enumeration.credentialsEnum.UserCredentials;
import org.epam.pages.LoginPage;
import org.epam.pages.StatusPage;
import org.junit.Assert;

public class LoginSteps {
    LoginPage loginPage = new LoginPage ( BaseSteps.webDriver );
    StatusPage statusPage = new StatusPage ( BaseSteps.webDriver );


    @Given("^I am on the Spotify Login Page$")
    public void openSpotifyLoginPage () {
        loginPage.open ( );
    }

    @When("I enter valid credentials ValidUsername and ValidPassword and click the Log In button")
    public void iEnterValidCredentialsAndClickTheLogInButton () {
        String username = UserCredentials.EMAIL_USERNAME.getValue ( );
        String password = UserCredentials.PASSWORD.getValue ( );
        loginPage.loginInToSpotify ( username , password );
    }

    @Then("My Profile Name should be displayed")
    public void myProfileNameShouldBeDisplayed () {
        String userName = UserCredentials.NAME.getValue ( );
        String loginMessage = statusPage.getLoginMessage ( ).toLowerCase ( );

        Assert.assertTrue ( "Login message did not contain the expected profile name: " + loginMessage , loginMessage.contains ( userName ) );
    }


    @When("I leave both credentials empty and click the Log In button")
    public void iLeaveBothCredentialsEmptyAndClickTheLogInButton () {
        loginPage.loginWithEmptyCredentialsToSpotify ( );
    }

    @Then("I should see error messages for both fields")
    public void iShouldSeeErrorMessagesForBothFields () {
        String usernameErrorMessage = loginPage.getEmptyUsernameErrorMessage ( );
        String passwordErrorMessage = loginPage.getEmptyPasswordErrorMessage ( );

        Assert.assertEquals ( "Please enter your Spotify username or email address." , usernameErrorMessage );
        Assert.assertEquals ( "Please enter your password." , passwordErrorMessage );
    }

    @When("I type incorrect credentials and click the Log In button")
    public void iTypeIncorrectCredentialsAndClickTheLogInButton () {
        loginPage.loginInToSpotify ( "incorrectUsername" , "incorrectPassword" );
    }

    @Then("I should see an error message for incorrect credentials")
    public void iShouldSeeAnErrorMessageForIncorrectCredentials () {
        String loginErrorMessage = loginPage.getWrongCredentialsErrorMessage ( );
        Assert.assertEquals ( "Incorrect username or password." , loginErrorMessage );
    }

}
