package oneALvWebScrapper.tests;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.pages.*;
import oneALvWebScrapper.utilities.JavaMailUtility;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.concurrent.TimeUnit;

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
        oneALvSearchPage.searchFilterData.setExpandFilterMenu("Modelis");
        oneALvSearchPage.searchFilterData.setFirstType("Videokartes");
        oneALvSearchPage.searchFilterData.setFirstBrand("Gigabyte");
        oneALvSearchPage.searchFilterData.setSecondBrand("MSI");
        oneALvSearchPage.searchFilterData.setFirstModel("GeForce RTX 3060");
        oneALvSearchPage.searchFilterData.setSortingFilterComboBox("Populārākās preces");
        oneALvSearchPage.searchFilterData.setSortingFilterValue("Cenas, sākot no zemākās");
    }

    @Test
    public void orderingTest() throws MessagingException, InterruptedException {
        for (int i = 11; i > 10; i++) {
            oneALvHomePage.openBaseUrl();
            oneALvHomePage.acceptCookies();
            oneALvHomePage.selectAction("search");
            oneALvSearchPage.productTypeSelection("GPU");
            oneALvSearchPage.productBrandSelection("gigabyte");
            oneALvSearchPage.productBrandSelection("msi");
            oneALvSearchPage.productMenuExpansion("model");
            oneALvSearchPage.productModelSelection("geforce rtx 3060");
            oneALvSearchPage.setMinMaxPriceToZero();
            oneALvSearchPage.setMaxPrice("500");
            oneALvSearchPage.selectProductSorting("prices from lowest");
            oneALvSearchPage.openProduct();
            oneALvProductPage.setProductData();
            oneALvProductPage.addProductToCart();
            oneALvProductPage.cartPopupAction("go to cart");
            oneALvCartPage.setCartData();
            oneALvCartPage.validateCartDataWithProductData(oneALvProductPage.productData.getProductName(), oneALvProductPage.productData.getProductPrice());
            oneALvCartPage.submitCart();
            oneALvProductPurchaseAuthorizationPage.fillAuthorizeWithoutLogin("yoda@gmail.com");
            oneALvProductPurchaseAuthorizationPage.submitAuthorizeWithoutLogin();
            oneALvCheckoutPage.shippingMethodSelection("in store");
            oneALvCheckoutPage.shippingStoreSelection("Lucavsala");
            oneALvCheckoutPage.fillReceiverName();
            oneALvCheckoutPage.fillReceiverSurname();
            oneALvCheckoutPage.fillReceiverPhoneNumber();
            oneALvCheckoutPage.submitReceiverData();
            oneALvCheckoutPage.setCustomerFullData();
            oneALvCheckoutPage.submitShippingForm();
            oneALvCheckoutPage.setFinalCustomerData();
            oneALvCheckoutPage.validateFinalCustomerDataWithDataOnShipmentPage();
            oneALvProductPage.emailFiling();
            System.out.println(oneALvProductPage.emailContent.getSubject());
            System.out.println(oneALvProductPage.emailContent.getTextBody());
            javaMailUtility.sendMail(oneALvProductPage.emailContent.getSubject(), oneALvProductPage.emailContent.getTextBody(), oneALvProductPage.emailContent.getFilePath());
            stopDriver();
            int cycleCount = 0;
            cycleCount++;
            System.out.println("Number of scrapper runs: " + cycleCount);
            TimeUnit.MINUTES.sleep(2);
        }
    }
}
