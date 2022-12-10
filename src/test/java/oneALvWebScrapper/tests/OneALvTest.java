package oneALvWebScrapper.tests;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.pages.*;
import oneALvWebScrapper.utilities.JavaMailUtility;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

public class OneALvTest extends CommonOneALv {
    JavaMailUtility javaMailUtility = new JavaMailUtility();
    OneALvHomePage oneALvHomePage = new OneALvHomePage();
    OneALvSearchPage oneALvSearchPage = new OneALvSearchPage();
    OneALvProductPage oneALvProductPage = new OneALvProductPage();
    OneALvCartPage oneALvCartPage = new OneALvCartPage();
    OneALvProductPurchaseAuthorizationPage oneALvProductPurchaseAuthorizationPage = new OneALvProductPurchaseAuthorizationPage();
    OneALvCheckoutPage oneALvCheckoutPage = new OneALvCheckoutPage();
    SoftAssertions softAssertions = new SoftAssertions();


    @Before
    public void setModelData() {
        oneALvHomePage.searchData.setSearchQuery("RTX 3060");
        oneALvCheckoutPage.customerData.setCustomerName("Obivan");
        oneALvCheckoutPage.customerData.setCustomerSurname("Kenobi");
        oneALvCheckoutPage.customerData.setCustomerPhoneNumber("22334455");
    }


    @Test
    public void orderingTest() throws InterruptedException, MessagingException {
        for (int i = 11; i > 10; i++) {
            oneALvHomePage.openBaseUrl();
            oneALvHomePage.acceptCookies();
            oneALvHomePage.selectAction("search");
            scrollPage(600);
            Thread.sleep(1500);
            oneALvSearchPage.productTypeSelection("GPU");
            scrollPage(500);
            Thread.sleep(1500);
            oneALvSearchPage.productBrandSelection("gigabyte");
            scrollPage(500);
            Thread.sleep(1500);
            oneALvSearchPage.productBrandSelection("msi");
            scrollPage(1200);
            Thread.sleep(1500);
            oneALvSearchPage.productModelMenuExpansion();
            oneALvSearchPage.productModelSelection();
            oneALvSearchPage.setMinMaxPriceToZero();
            oneALvSearchPage.setMaxPrice("500");
            oneALvSearchPage.selectFilterBy();
            scrollPage(500);
            Thread.sleep(1500);
            oneALvSearchPage.openProduct();
            oneALvProductPage.setProductData();
            oneALvProductPage.addProductToCart();
            oneALvProductPage.cartPopupAction("go to cart");
            oneALvCartPage.setCartData();
            softAssertions.assertThat(oneALvProductPage.productData.getProductName()).isEqualTo(oneALvCartPage.cartData.getCartProductName());
            softAssertions.assertThat(oneALvProductPage.productData.getProductPrice()).isEqualTo(oneALvCartPage.cartData.getCartProductPrice());
            oneALvCartPage.submitCart();
            oneALvProductPurchaseAuthorizationPage.fillAuthorizeWithoutLogin("yoda@gmail.com");
            oneALvProductPurchaseAuthorizationPage.submitAuthorizeWithoutLogin();
            oneALvCheckoutPage.shippingMethodSelection("in store");
            oneALvCheckoutPage.shippingStoreSelection("Lucavsala");
            scrollPage(1000);
            Thread.sleep(1500);
            oneALvCheckoutPage.fillReceiverName();
            oneALvCheckoutPage.fillReceiverSurname();
            oneALvCheckoutPage.fillReceiverPhoneNumber();
            oneALvCheckoutPage.submitReceiverData();
            oneALvCheckoutPage.setCustomerFullData();
            oneALvCheckoutPage.submitShippingForm();
            oneALvCheckoutPage.setFinalCustomerData();
            softAssertions.assertThat(oneALvCheckoutPage.customerData.getFinalCustomerFullName()).isEqualTo(oneALvCheckoutPage.customerData.getCustomerFullName());
            softAssertions.assertThat(oneALvCheckoutPage.customerData.getFinalCustomerFullPhoneNumber()).isEqualTo(oneALvCheckoutPage.customerData.getCustomerFullPhoneNumber());
            softAssertions.assertAll();
            oneALvProductPage.emailFiling();
            System.out.println(oneALvProductPage.emailContent.getSubject());
            System.out.println(oneALvProductPage.emailContent.getTextBody());
            javaMailUtility.sendMail(oneALvProductPage.emailContent.getSubject(), oneALvProductPage.emailContent.getTextBody(), oneALvProductPage.emailContent.getFilePath());
            stopDriver();
            int cycleCount = 0;
            cycleCount++;
            System.out.println("Number of scrapper runs: " + cycleCount);
            Thread.sleep(120000);
        }
    }

    @After
    public void cleanModelData() {
        oneALvProductPage.emailContent = null;
        oneALvProductPage.productData = null;
        oneALvCheckoutPage.customerData = null;
        oneALvCartPage.cartData = null;
        oneALvHomePage.searchData = null;
    }
}
