package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public void enterUsername(String username) {

        this.username.sendKeys(username);
    }

    public void enterPassword(String password) {

        this.password.sendKeys(password);
    }

    public void clickLoginButton() {

        loginBtn.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
