package finalProject.tests;

import finalProject.common.CommonOneALv;
import finalProject.pages.OneALvHomePage;
import finalProject.pages.OneALvProductPage;
import finalProject.pages.OneALvSearchPage;
import finalProject.utilities.JavaMailUtility;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

public class OneALvTest extends CommonOneALv {
    JavaMailUtility javaMailUtility = new JavaMailUtility();
    OneALvHomePage oneALvHomePage = new OneALvHomePage();
    OneALvSearchPage oneALvSearchPage = new OneALvSearchPage();
    OneALvProductPage oneALvProductPage = new OneALvProductPage();


    @Before
    public void setSearchData() {
        oneALvHomePage.searchData.setSearchQuery("RTX 3060");
    }

    @Test
    public void orderingTest() throws InterruptedException, MessagingException {
        oneALvHomePage.openBaseUrl();
        oneALvHomePage.acceptCookies();
        oneALvHomePage.selectAction("search");
        scrollPage(600);
        oneALvSearchPage.productTypeSelection("GPU");
        scrollPage(500);
        oneALvSearchPage.productBrandSelection("gigabyte");
        scrollPage(500);
        oneALvSearchPage.productBrandSelection("msi");
        scrollPage(1200);
        oneALvSearchPage.productModelMenuExpansion();
        oneALvSearchPage.productModelSelection();
        oneALvSearchPage.setMinMaxPriceToZero();
        oneALvSearchPage.setMaxPrice("500");
        oneALvSearchPage.selectFilterBy();
        scrollPage(500);
        oneALvSearchPage.openProduct();
        oneALvProductPage.setProductData();
        oneALvProductPage.emailFilingTest();
        System.out.println(oneALvProductPage.emailContent.getSubject());
        System.out.println(oneALvProductPage.emailContent.getTextBody());
        javaMailUtility.sendMail();
        stopDriver();
    }
}
