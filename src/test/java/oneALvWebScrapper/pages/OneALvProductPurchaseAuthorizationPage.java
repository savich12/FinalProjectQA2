package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OneALvProductPurchaseAuthorizationPage extends CommonOneALv {
    private By authorizationEmailField = By.id("user_email");
    private By authorizationSubmitButton = By.cssSelector("[type='submit'][name='commit']");

    public void fillAuthorizeWithoutLogin(String email) {
        driver.findElements(authorizationEmailField).get(1).sendKeys(email);
    }

    public void submitAuthorizeWithoutLogin() {
        driver.findElements(authorizationSubmitButton).get(1).click();
    }
}
