package finalProject.pages;

import finalProject.common.CommonOneALv;
import finalProject.models.ProductData;
import finalProject.models.SearchData;
import org.openqa.selenium.By;

public class OneALvHomePage extends CommonOneALv {
    public SearchData searchData = new SearchData();
    String baseUrl = "https://www.1a.lv/";
    private By closeCookiesButton = By.cssSelector("a[onclick='Cookiebot.dialog.submitConsent()']");
    private By searchField = By.cssSelector("input[id='q'][type='text']");
//    private By closeCouponsButton = By.xpath("//img[contains(@src, 'https://img.alicdn.com/tfs/TB1a')]");

    public void openBaseUrl() {
        startDriver();
        driver.get(baseUrl);
    }

    public void acceptCookies() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(closeCookiesButton).click();
    }

    public void selectAction(String action) throws InterruptedException {
        if ("search".equals(action)) {
            driver.findElement(searchField).sendKeys(searchData.getSearchQuery());
            driver.findElement(searchField).submit();
        } else {
            System.out.println("Something went wrong in homepage action selection!!!");
        }
        Thread.sleep(1000);
    }


}
