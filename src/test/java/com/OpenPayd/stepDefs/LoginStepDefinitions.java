package com.OpenPayd.stepDefs;


import com.OpenPayd.pages.Login_Page;
import com.OpenPayd.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;




public class LoginStepDefinitions {


    @Given("I am a user of amazon.com")
    public void i_am_a_user_of_amazon_com() throws InterruptedException {
        Driver.get().get("https://amazon.com");


        Login_Page loginPage = new Login_Page();
        loginPage.signIn.click();
        Thread.sleep(4000);


    }

    @When("I log in using valid credentials")
    public void i_log_in_using_valid_credentials() throws InterruptedException {
        Login_Page loginPage = new Login_Page();
        loginPage.email.sendKeys("megeka2416@prowerl.com");
        loginPage.continueb.click();
        Thread.sleep(4000);
        loginPage.password.sendKeys("H12345");
        loginPage.signinbutton.click();


    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        String text = Driver.get().findElement(By.xpath("//*[@id=\"cvf-page-content\"]/div[1]/div/div/form/div[1]/h1")).getText();
        Assert.assertEquals(text, "Authentication required");
    }

    @When("I log in using invalid credentials")
    public void i_log_in_using_invalid_credentials() throws InterruptedException {
        Login_Page loginPage = new Login_Page();
        loginPage.email.sendKeys("anyemail@prowerl.com");
        loginPage.continueb.click();
        Thread.sleep(4000);

    }

    @Then("I should not be logged in")
    public void i_should_not_be_logged_in() throws InterruptedException {

        String expectedMessage = "We cannot find an account with that email address";
        String actualMessage = Driver.get().findElement(By.xpath("//span[@class='a-list-item']")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);

    }
}


