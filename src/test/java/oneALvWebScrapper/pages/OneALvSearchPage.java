package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.models.SearchFilterData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OneALvSearchPage extends CommonOneALv {
    public SearchFilterData searchFilterData = new SearchFilterData();
    private By maxPriceField = By.id("attr-to-priceLoyalty");
    private By minPriceField = By.id("attr-from-priceLoyalty");
    private By allProducts = By.xpath("//div[@class='sn-product-inner ks-gtm-categories']/a[@class='ks-new-product-name']");
    private By sortingTable = By.cssSelector("[class='ks-row sn-filter-toolbox-row']");

    public void productTypeSelection(String productType) {
        By firstTypeCheckbox = By.xpath("//span[contains(text(), '" + searchFilterData.getFirstType() + "')]/parent::a");
        if ("GPU".equals(productType)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
            scrollUntilLocatorFoundAndClick(firstTypeCheckbox);
        } else {
            System.out.println("Something went wrong in product type selection on search page!");
        }
    }

    public void productBrandSelection(String productBrand) {
        By firstBrandCheckbox = By.xpath("//span[contains(text(), '" + searchFilterData.getFirstBrand() + "')]/parent::a");
        By secondBrandCheckbox = By.xpath("//span[contains(text(), '" + searchFilterData.getSecondBrand() + "')]/parent::a");
        if ("gigabyte".equals(productBrand)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
            scrollUntilLocatorFoundAndClick(firstBrandCheckbox);
        } else if ("msi".equals(productBrand)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
            scrollUntilLocatorFoundAndClick(secondBrandCheckbox);
        } else {
            System.out.println("Something went wrong in product brand selection on search page!");
        }
    }

    public void productMenuExpansion(String menu) {
        By firstFilterMenu = By.xpath("//div[contains(text(), '" + searchFilterData.getExpandFilterMenu() + "')]");
        if ("model".equals(menu)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
            scrollUntilLocatorFoundAndClick(firstFilterMenu);
        } else {
            System.out.println("Something went wrong in menu to be expanded selection on search page!");
        }
    }

    public void productModelSelection(String model) {
        By firstModelCheckbox = By.xpath("//span[text() = '" + searchFilterData.getFirstModel() + "']/parent::a");
        if ("geforce rtx 3060".equals(model)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
            scrollUntilLocatorFoundAndClick(firstModelCheckbox);
        } else {
            System.out.println("Something went wrong in product model selection on search page!");
        }
    }

    public void setMaxPrice(String maxPrice) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
        scrollUntilLocatorFoundAndClick(minPriceField);
        driver.findElement(maxPriceField).sendKeys(maxPrice);
        driver.findElement(maxPriceField).submit();
    }

    public void setMinMaxPriceToZero() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
        scrollUntilLocatorFoundAndClick(sortingTable);
        driver.findElement(maxPriceField).clear();
        scrollUntilLocatorFoundAndClick(sortingTable);
        driver.findElement(maxPriceField).clear();
    }

    public void selectProductSorting(String sorting) {
        By sortingFilterComboBox = By.cssSelector("span[title='" + searchFilterData.getSortingFilterComboBox() + "']");
        By pricesFromLowestSorting = By.xpath("//li[text()='" + searchFilterData.getSortingFilterValue() + "']");
        if ("prices from lowest".equals(sorting)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
            WebElement filterComboBoxElements = driver.findElements(sortingFilterComboBox).get(1);
            scrollUntilElementFoundAndClick(filterComboBoxElements);
            scrollUntilLocatorFoundAndClick(pricesFromLowestSorting);
        } else {
            System.out.println("Something went wrong in sorting selection on search page!");
        }
    }

    public void openProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortingTable));
        WebElement productElement = driver.findElements(allProducts).get(0);
        scrollUntilElementFoundAndClick(productElement);
    }
}
