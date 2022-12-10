package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.models.SearchData;
import org.openqa.selenium.By;

public class OneALvHomePage extends CommonOneALv {
    public SearchData searchData = new SearchData();
    String baseUrl = "https://www.1a.lv/";
    private By closeCookiesButton = By.cssSelector("a[onclick='Cookiebot.dialog.submitConsent()']");
    private By searchField = By.cssSelector("input[id='q'][type='text']");
    private By cartBox = By.cssSelector("[class='cart-block__handle'][href='/cart']");

    public void openBaseUrl() {
        startDriver();
        driver.get(baseUrl);
    }

    public void acceptCookies() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(closeCookiesButton).click();
    }

    public void selectAction(String action) throws InterruptedException {
        if ("search".equals(action)) {
            driver.findElement(searchField).sendKeys(searchData.getSearchQuery());
            driver.findElement(searchField).submit();
        } else if ("go to cart".equals(action)) {
            driver.findElement(cartBox).click();
        } else {
            System.out.println("Something went wrong in homepage action selection!!!");
        }
        Thread.sleep(1500);
    }
}
