package com.OpenPayd.pages;


import com.OpenPayd.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {

    public Login_Page() {

        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(xpath = "//*[@id=\"nav-link-accountList\"]")
    public WebElement signIn;

    @FindBy(id = "ap_email")
    public WebElement email;

    @FindBy(id = "continue")
    public WebElement continueb;

    @FindBy(id = "ap_password")
    public WebElement password;

    @FindBy(id = "signInSubmit")
    public WebElement signinbutton;



}