package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.models.SearchData;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OneALvHomePage extends CommonOneALv {
    public SearchData searchData = new SearchData();
    String baseUrl = "https://www.1a.lv/";
    private By closeCookiesButton = By.cssSelector("a[onclick='Cookiebot.dialog.submitConsent()']");
    private By searchField = By.cssSelector("input[id='q'][type='text']");
    private By cartBox = By.cssSelector("[class='cart-block__handle'][href='/cart']");
    private By cookieBanner = By.id("cookiebanner");

    public void openBaseUrl() {
        startDriver();
        driver.get(baseUrl);
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cookieBanner));
        checkLocatorAvailabilityAndClick(closeCookiesButton);
    }

    public void selectAction(String action) {
        if ("search".equals(action)) {
            driver.findElement(searchField).sendKeys(searchData.getSearchQuery());
            driver.findElement(searchField).submit();
        } else if ("go to cart".equals(action)) {
            driver.findElement(cartBox).click();
        } else {
            System.out.println("Something went wrong in homepage action selection!!!");
        }
    }
}
