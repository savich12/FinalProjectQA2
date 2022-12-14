package oneALvWebScrapper.tests;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.models.EmailContent;
import oneALvWebScrapper.pages.*;
import oneALvWebScrapper.utilities.FileUtility;
import oneALvWebScrapper.utilities.JavaMailUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

public class OneALvTest extends CommonOneALv {
    EmailContent emailContent = new EmailContent();
    FileUtility fileUtility = new FileUtility();
    JavaMailUtility javaMailUtility = new JavaMailUtility();
    OneALvHomePage oneALvHomePage = new OneALvHomePage();
    OneALvSearchPage oneALvSearchPage = new OneALvSearchPage();
    OneALvProductPage oneALvProductPage = new OneALvProductPage();
    OneALvCartPage oneALvCartPage = new OneALvCartPage();
    OneALvProductPurchaseAuthorizationPage oneALvProductPurchaseAuthorizationPage = new OneALvProductPurchaseAuthorizationPage();
    OneALvCheckoutPage oneALvCheckoutPage = new OneALvCheckoutPage();


    @Before
    public void setModelData() {
        oneALvHomePage.searchData.setSearchQuery("RTX 3060");
        oneALvSearchPage.searchFilterData.setExpandFilterMenu("Modelis");
        oneALvSearchPage.searchFilterData.setFirstType("Videokartes");
        oneALvSearchPage.searchFilterData.setFirstBrand("Gigabyte");
        oneALvSearchPage.searchFilterData.setSecondBrand("MSI");
        oneALvSearchPage.searchFilterData.setFirstModel("GeForce RTX 3060");
        oneALvSearchPage.searchFilterData.setSortingFilterComboBox("Populārākās preces");
        oneALvSearchPage.searchFilterData.setSortingFilterValue("Cenas, sākot no zemākās");
        emailContent.setFilePath("C:\\Users\\extes\\OneDrive\\Dators\\QA\\JavaGuruQA2\\scrapper_data.txt");
        emailContent.setSubject("Scrapper for: Cheapest " + oneALvHomePage.searchData.getSearchQuery());
        emailContent.setTextBody("Please find txt file with scraper data attached to email.");
    }

    @Test
    public void orderingTest() throws MessagingException {
        for (int i = 11; i > 10; i++) {
            fileUtility.createTxtFile();
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
            String url = oneALvSearchPage.getCurrentUrlWithFilters();
            for (int num = 0; num < 5; num++) {
                driver.get(url);
                oneALvSearchPage.openProduct(num);
                oneALvProductPage.setProductData();
                oneALvProductPage.addProductToCart();
                oneALvProductPage.cartPopupAction("go to cart");
                oneALvCartPage.setCartData();
                oneALvCartPage.validateCartDataWithProductData(oneALvProductPage.productData.getProductName(), oneALvProductPage.productData.getProductPrice());
                oneALvCartPage.submitCart();
                if ("https://www.1a.lv/checkout/shipping".equals(driver.getCurrentUrl())) {
                    System.out.println("You are already authorized!");
                } else if ("https://www.1a.lv/checkout_authentication/new".equals(driver.getCurrentUrl())) {
                    oneALvProductPurchaseAuthorizationPage.fillAuthorizeWithoutLogin("yoda@gmail.com");
                    oneALvProductPurchaseAuthorizationPage.submitAuthorizeWithoutLogin();
                    oneALvCheckoutPage.shippingMethodSelection("in store");
                    oneALvCheckoutPage.shippingStoreSelection("Lucavsala");
                    oneALvCheckoutPage.fillReceiverName("Obivan");
                    oneALvCheckoutPage.fillReceiverSurname("Kenobi");
                    oneALvCheckoutPage.fillReceiverPhoneNumber("22334455");
                    oneALvCheckoutPage.submitReceiverData();
                    oneALvCheckoutPage.setCustomerFullData();
                } else {
                    System.out.println("Something went wrong in authorization!");
                }
                oneALvCheckoutPage.submitShippingForm();
                oneALvCheckoutPage.setFinalCustomerData();
                oneALvCheckoutPage.validateFinalCustomerDataWithDataOnShipmentPage();
                oneALvProductPage.fileFilling();
                oneALvCheckoutPage.returnToCart();
                oneALvCartPage.deleteFirstProductFromCart();
            }
            javaMailUtility.sendMail(emailContent.getSubject(), emailContent.getTextBody(), emailContent.getFilePath());
            stopDriver();
            fileUtility.deleteFile();
            int cycleCount = 0;
            cycleCount++;
            System.out.println("Number of scrapper runs: " + cycleCount);
        }
    }
}
