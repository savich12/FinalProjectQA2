package finalProject.pages;

import finalProject.common.CommonOneALv;
import finalProject.models.EmailContent;
import finalProject.models.ProductData;
import org.openqa.selenium.By;

public class OneALvProductPage extends CommonOneALv {
    public EmailContent emailContent = new EmailContent();
    public ProductData productData = new ProductData();
    By productNameText = By.xpath("//h1");
    By productPriceValue = By.xpath("//span[@class='price']/child::span[1]");
    By productPriceCurrency = By.xpath("//span[@class='price']/child::span[2]");

    public void setProductData() throws InterruptedException {
        Thread.sleep(2000);
        String productNameProductPage = driver.findElement(productNameText).getText();
        String productPriceProductPage = driver.findElement(productPriceValue).getText() + " " + driver.findElement(productPriceCurrency).getText();
        String productUrlProductPage = driver.getCurrentUrl();
        productData.setProductName(productNameProductPage);
        productData.setProductPrice(productPriceProductPage);
        productData.setProductUrl(productUrlProductPage);
    }

    public void emailFilingTest() {
        emailContent.setSubject("First test of scraper!");
        emailContent.setTextBody("Product: " + productData.getProductName() + ",\nProductPrice: " + productData.getProductPrice()
                + ",\nProduct URL: " + productData.getProductUrl() + ".\n--------------------");
    }
}
