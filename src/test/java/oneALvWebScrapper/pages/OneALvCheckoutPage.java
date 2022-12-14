package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.models.CustomerData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OneALvCheckoutPage extends CommonOneALv {
    public CustomerData customerData = new CustomerData();
    private By inStoreRadio = By.cssSelector("[name='shipping_unused'][value='2']");
    private By parcelMachine = By.cssSelector("[name='shipping_unused'][value='3']");
    private By shippingToLucavsalaRadio = By.cssSelector("[name='pickup_point_id'][value='3210']");
    private By shippingToRumbulaRadio = By.cssSelector("[name='pickup_point_id'][value='3666']");
    private By couponField = By.cssSelector("[class='checkout-order-summary-coupon__input']");
    private By shippingNameField = By.id("address_first_name");
    private By shippingSurnameField = By.id("address_last_name");
    private By shippingPhoneNumberField = By.id("address_phone_number");
    private By shippingSubmitButton = By.xpath("//button[@name='button'][@type='submit']");
    private By shippingFullNameText = By.xpath("//div[@class='name']");
    private By shippingFullPhoneNumberText = By.xpath("//div[@class='address']");
    private By billingReceiverTable = By.xpath("//td[@class='checkout-order-summary__table-shipping-info']");
    private By returnToCartButton = By.className("checkout-order-summary-visit-cart__link-label");

    public void shippingMethodSelection(String shippingMethod) {
        if ("in store".equals(shippingMethod)) {
            driver.findElement(inStoreRadio).click();
        } else if ("parcel machine".equals(shippingMethod)) {
            driver.findElement(parcelMachine).click();

        } else {
            System.out.println("Something went wrong in shipping method selection!");
        }
    }

    public void shippingStoreSelection(String shippingStore) {
        if ("Lucavsala".equals(shippingStore)) {
            driver.findElement(shippingToLucavsalaRadio).click();
        } else if ("Rumbula".equals(shippingStore)) {
            driver.findElement(shippingToRumbulaRadio).click();
        } else {
            System.out.println("Something went wrong in shipment store selection!");
        }
    }

    public void fillCoupon(String coupon) {
        driver.findElement(couponField).sendKeys(coupon);
    }

    public void couponSubmit() {
        driver.findElement(couponField).submit();
    }

    public void fillReceiverName(String name) {
        customerData.setCustomerName(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingNameField));
        scrollUntilLocatorFoundAndClick(shippingNameField);
        driver.findElement(shippingNameField).sendKeys(customerData.getCustomerName());
    }

    public void fillReceiverSurname(String surname) {
        customerData.setCustomerSurname(surname);
        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingSurnameField));
        scrollUntilLocatorFoundAndClick(shippingNameField);
        driver.findElement(shippingSurnameField).sendKeys(customerData.getCustomerSurname());
    }

    public void fillReceiverPhoneNumber(String phoneNumber) {
        customerData.setCustomerPhoneNumber(phoneNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingPhoneNumberField));
        scrollUntilLocatorFoundAndClick(shippingNameField);
        driver.findElement(shippingPhoneNumberField).sendKeys(customerData.getCustomerPhoneNumber());
    }

    public void submitReceiverData() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingSubmitButton));
        WebElement submitReceiverDataElement = driver.findElements(shippingSubmitButton).get(0);
        scrollUntilElementFoundAndClick(submitReceiverDataElement);
    }

    public void setCustomerFullData() {
        customerData.setCustomerFullName(driver.findElement(shippingFullNameText).getText());
        String receiverData = driver.findElement(shippingFullPhoneNumberText).getText();
        String[] receiverDataArr = receiverData.split(" ");
        customerData.setCustomerFullPhoneNumber(receiverDataArr[0]);
    }

    public void submitShippingForm() {
        WebElement submitShippingButtonElement = driver.findElements(shippingSubmitButton).get(1);
        scrollUntilElementFoundAndClick(submitShippingButtonElement);
    }

    public void setFinalCustomerData() {
        String finalData = driver.findElement(billingReceiverTable).getText();
        String[] finalDataArr = finalData.split("\n");
        customerData.setFinalCustomerFullName(finalDataArr[0]);
        customerData.setFinalCustomerFullPhoneNumber(finalDataArr[1]);
    }

    public void validateFinalCustomerDataWithDataOnShipmentPage() {
        softAssertions.assertThat(customerData.getFinalCustomerFullName()).isEqualTo(customerData.getCustomerFullName());
        softAssertions.assertThat(customerData.getFinalCustomerFullPhoneNumber()).isEqualTo(customerData.getCustomerFullPhoneNumber());
        softAssertions.assertAll();
    }

    public void returnToCart() {
        scrollUntilLocatorFoundAndClick(returnToCartButton);
    }
}
