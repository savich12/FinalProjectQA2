package finalProject.pages;

import finalProject.common.CommonOneALv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class OneALvSearchPage extends CommonOneALv {
    By gpuCheckbox = By.xpath("//span[contains(text(), 'Videokartes')]/parent::a");
    By gigabyteCheckbox = By.xpath("//span[contains(text(), 'Gigabyte')]/parent::a");
    By msiCheckbox = By.xpath("//span[contains(text(), 'MSI')]/parent::a");
    By productModelMenu = By.xpath("//div[contains(text(), 'Modelis')]");
    By modelCheckbox = By.xpath("//span[text() = 'GeForce RTX 3060']/parent::a");
    By maxPriceField = By.id("attr-to-priceLoyalty");
    By minPriceField = By.id("attr-from-priceLoyalty");
    By filterComboBox = By.cssSelector("span[title='Populārākās preces']");
    By pricesFromLowestButton = By.xpath("//li[text()='Cenas, sākot no zemākās']");
    By allProducts = By.xpath("//div[@class='sn-product-inner ks-gtm-categories']/a[@class='ks-new-product-name']");

    public void productTypeSelection(String productType) throws InterruptedException {
        Thread.sleep(1500);
        if ("GPU".equals(productType)) {
            WebElement gpuCheckboxElement = driver.findElement(gpuCheckbox);
            gpuCheckboxElement.click();
        } else {
            System.out.println("Something went wrong in product type selection on search screen!");
        }
        Thread.sleep(1000);
    }

    public void productBrandSelection(String productBrand) throws InterruptedException {
        Thread.sleep(1500);
        if ("gigabyte".equals(productBrand)) {
            WebElement gigabyteCheckboxElement = driver.findElement(gigabyteCheckbox);
            gigabyteCheckboxElement.click();
        } else if ("msi".equals(productBrand)) {
            WebElement msiCheckboxElement = driver.findElement(msiCheckbox);
            msiCheckboxElement.click();
        }
        Thread.sleep(1000);
    }

    public void productModelMenuExpansion() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(productModelMenu).click();
        Thread.sleep(1000);
    }

    public void productModelSelection() throws InterruptedException {
        Thread.sleep(1000);
        WebElement modelCheckboxElement = driver.findElement(modelCheckbox);
        modelCheckboxElement.click();
        Thread.sleep(1000);
    }

    public void setMaxPrice(String maxPrice) {
        driver.findElement(maxPriceField).sendKeys(maxPrice);
        driver.findElement(maxPriceField).submit();
    }

    public void setMinMaxPriceToZero() throws InterruptedException {
        driver.findElement(maxPriceField).clear();
        Thread.sleep(1000);
        driver.findElement(maxPriceField).clear();
        Thread.sleep(1000);
    }

    public void selectFilterBy() throws InterruptedException {
        Thread.sleep(1000);
        WebElement filterComboBoxElements = driver.findElements(filterComboBox).get(1);
        filterComboBoxElements.click();
        Thread.sleep(1000);
        driver.findElement(pricesFromLowestButton).click();
        Thread.sleep(1000);
    }

    public void openProduct() throws InterruptedException {
        Thread.sleep(1000);
        WebElement productElement = driver.findElements(allProducts).get(0);
        productElement.click();
        Thread.sleep(1000);
    }
}
