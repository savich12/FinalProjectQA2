package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OneALvSearchPage extends CommonOneALv {
    private By gpuCheckbox = By.xpath("//span[contains(text(), 'Videokartes')]/parent::a");
    private By gigabyteCheckbox = By.xpath("//span[contains(text(), 'Gigabyte')]/parent::a");
    private By msiCheckbox = By.xpath("//span[contains(text(), 'MSI')]/parent::a");
    private By productModelMenu = By.xpath("//div[contains(text(), 'Modelis')]");
    private By modelCheckbox = By.xpath("//span[text() = 'GeForce RTX 3060']/parent::a");
    private By maxPriceField = By.id("attr-to-priceLoyalty");
    private By minPriceField = By.id("attr-from-priceLoyalty");
    private By filterComboBox = By.cssSelector("span[title='Populārākās preces']");
    private By pricesFromLowestButton = By.xpath("//li[text()='Cenas, sākot no zemākās']");
    private By allProducts = By.xpath("//div[@class='sn-product-inner ks-gtm-categories']/a[@class='ks-new-product-name']");

    public void productTypeSelection(String productType) throws InterruptedException {
        if ("GPU".equals(productType)) {
            WebElement gpuCheckboxElement = driver.findElement(gpuCheckbox);
            gpuCheckboxElement.click();
        } else {
            System.out.println("Something went wrong in product type selection on search screen!");
        }
        Thread.sleep(1500);
    }

    public void productBrandSelection(String productBrand) throws InterruptedException {
        if ("gigabyte".equals(productBrand)) {
            WebElement gigabyteCheckboxElement = driver.findElement(gigabyteCheckbox);
            gigabyteCheckboxElement.click();
        } else if ("msi".equals(productBrand)) {
            WebElement msiCheckboxElement = driver.findElement(msiCheckbox);
            msiCheckboxElement.click();
        }
        Thread.sleep(1500);
    }

    public void productModelMenuExpansion() throws InterruptedException {
        driver.findElement(productModelMenu).click();
        Thread.sleep(1500);
    }

    public void productModelSelection() throws InterruptedException {
        WebElement modelCheckboxElement = driver.findElement(modelCheckbox);
        modelCheckboxElement.click();
        Thread.sleep(1500);
    }

    public void setMaxPrice(String maxPrice) throws InterruptedException {
        driver.findElement(maxPriceField).sendKeys(maxPrice);
        driver.findElement(maxPriceField).submit();
        Thread.sleep(1500);
    }

    public void setMinMaxPriceToZero() throws InterruptedException {
        driver.findElement(maxPriceField).clear();
        Thread.sleep(1500);
        driver.findElement(maxPriceField).clear();
        Thread.sleep(1500);
    }

    public void selectFilterBy() throws InterruptedException {
        WebElement filterComboBoxElements = driver.findElements(filterComboBox).get(1);
        filterComboBoxElements.click();
        Thread.sleep(1500);
        driver.findElement(pricesFromLowestButton).click();
        Thread.sleep(1500);
    }

    public void openProduct() throws InterruptedException {
        WebElement productElement = driver.findElements(allProducts).get(0);
        productElement.click();
        Thread.sleep(1500);
    }
}
