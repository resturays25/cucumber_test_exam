package com.juaracoding;

import com.juaracoding.pages.CartPage;
import com.juaracoding.pages.CheckoutPage;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.ProductPage;
import com.juaracoding.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutTest {

    private LoginPage loginPage = new LoginPage(Hooks.driver);
    private ProductPage productPage = new ProductPage(Hooks.driver);
    private CartPage cartPage = new CartPage(Hooks.driver);
    private CheckoutPage checkoutPage = new CheckoutPage(Hooks.driver);

    @Given("User has added two products to the cart")
    public void userHasAddedTwoProductsToTheCart() {
        Hooks.driver.get("https://www.saucedemo.com/");
        loginPage.enterUsername("standard_user");
        delay(1);
        loginPage.enterPassword("secret_sauce");
        delay(2);
        loginPage.clickLoginButton();
        productPage.addTshirtToCart();
        delay(1);
        productPage.addSweaterToCart();
        delay(1);
        productPage.goToShoppingCart();
    }

    @When("User proceeds to checkout")
    public void user_Proceeds_To_Checkout() {
        cartPage.proceedToCheckout();
    }

    @And("User enters valid checkout information")
    public void user_EntersVal_id_Checkout_Information() {
        checkoutPage.enterFirstName("Restu");
        delay(1);
        checkoutPage.enterLastName("Ray Sandy");
        delay(2);
        checkoutPage.enterPostalCode("8812388");
        delay(1);
    }

    // Mengosongkan Informasi
    @And("User leaves the checkout information empty")
    public void user_Leaves_The_Checkout_Information_Empty() {

    }

    @And("User clicks the continue button")
    public void user_Clicks_The_Continue_Button() {
        delay(1);
        checkoutPage.clickContinueButton();
    }

    // Verifikasi melanjutkan proses
    @And("User should proceed to the next step in checkout")
    public void user_Should_Proceed_To_The_Next_Step_In_Checkout() {
        delay(1);
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two.html"));
    }

    // Verifikasi Error
    @Then("User should see an error message on checkout page")
    public void user_Should_See_An_Error_Message_On_Checkout_Page() {

        delay(1);
        String errorMessage = checkoutPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Error: First Name is required"));
    }

    public static void delay(long detik) {
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
