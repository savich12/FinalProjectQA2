package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.models.EmailContent;
import oneALvWebScrapper.models.ProductData;
import org.openqa.selenium.By;

public class OneALvProductPage extends CommonOneALv {
    public EmailContent emailContent = new EmailContent();
    public ProductData productData = new ProductData();
    private By productNameText = By.xpath("//h1");
    private By productPriceValue = By.xpath("//span[@class='price']/child::span[1]");
    private By productPriceCurrency = By.xpath("//span[@class='price']/child::span[2]");
    private By addToCartButton = By.id("add_to_cart_btn");
    private By goToCartFromPopupButton = By.cssSelector("[class='main-button'][href='/cart']");
    private By continueBrowsingFromPopup = By.id("continue_shopping");

    public void setProductData() {
        String productNameProductPage = driver.findElement(productNameText).getText();
        String productPriceProductPage = driver.findElement(productPriceValue).getText() + " " + driver.findElement(productPriceCurrency).getText();
        String productUrlProductPage = driver.getCurrentUrl();
        productData.setProductName(productNameProductPage);
        productData.setProductPrice(productPriceProductPage);
        productData.setProductUrl(productUrlProductPage);
    }

    public void addProductToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void cartPopupAction(String popupAction) {
        if ("go to cart".equals(popupAction)) {
            driver.findElement(goToCartFromPopupButton).click();
        } else if ("continue browsing".equals(popupAction)) {
            driver.findElement(continueBrowsingFromPopup).click();
        } else {
            System.out.println("Something went wrong in cart popup action selection!");
        }
    }

    public void emailFiling() {
        emailContent.setSubject("Scrapper: " + productData.getProductName());
        emailContent.setTextBody("Product: " + productData.getProductName() + ",\nProductPrice: " + productData.getProductPrice()
                + ",\nProduct URL: " + productData.getProductUrl() + " .");
        emailContent.setFilePath("");
    }
}
