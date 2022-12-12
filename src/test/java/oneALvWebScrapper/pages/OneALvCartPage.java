package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.models.CartData;
import org.openqa.selenium.By;

public class OneALvCartPage extends CommonOneALv {
    public CartData cartData = new CartData();

    private By cartProductNameText = By.xpath("//p[@class='detailed-cart-item__name']/a");
    private By cartProductPriceText = By.id("cart-full-total-price");
    private By cartSubmitButton = By.cssSelector("[type='submit'][name='commit']");

    public void setCartData() {
        cartData.setCartProductName(driver.findElement(cartProductNameText).getText());
        cartData.setCartProductPrice(driver.findElement(cartProductPriceText).getText());
    }

    public void validateCartDataWithProductData(String productName, String productPrice) {
        softAssertions.assertThat(cartData.getCartProductName()).isEqualTo(productName);
        softAssertions.assertThat(cartData.getCartProductPrice()).isEqualTo(productPrice);
        softAssertions.assertAll();
    }

    public void submitCart() {
        driver.findElement(cartSubmitButton).click();
    }
}
