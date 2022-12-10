package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OneALvProductPurchaseAuthorizationPage extends CommonOneALv {
    private By authorizationEmailField = By.id("user_email");
    private By authorizationSubmitButton = By.cssSelector("[type='submit'][name='commit']");

    public void fillAuthorizeWithoutLogin(String email) {
        WebElement authorizeWithoutLoginEmailElement = driver.findElements(authorizationEmailField).get(1);
        authorizeWithoutLoginEmailElement.sendKeys(email);
    }

    public void submitAuthorizeWithoutLogin() throws InterruptedException {
        WebElement authorizeWithoutLoginSubmitElement = driver.findElements(authorizationSubmitButton).get(1);
        authorizeWithoutLoginSubmitElement.click();
        Thread.sleep(1500);
    }
}
