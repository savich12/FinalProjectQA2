package oneALvWebScrapper.pages;

import oneALvWebScrapper.common.CommonOneALv;
import oneALvWebScrapper.models.CustomerData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OneALvCheckoutPage extends CommonOneALv {
    public CustomerData customerData = new CustomerData();
    private By inStoreRadio = By.cssSelector("[name='shipping_unused'][value='2']");
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

    public void shippingMethodSelection(String shippingMethod) throws InterruptedException {
        if ("in store".equals(shippingMethod)) {
            WebElement inStoreRadioElement = driver.findElement(inStoreRadio);
            inStoreRadioElement.click();
        } else {
            System.out.println("Something went wrong in shipping method selection!");
        }
        Thread.sleep(1500);
    }

    public void shippingStoreSelection(String shippingStore) throws InterruptedException {
        if ("Lucavsala".equals(shippingStore)) {
            WebElement shippingStoreRadioElement = driver.findElement(shippingToLucavsalaRadio);
            shippingStoreRadioElement.click();
        } else if ("Rumbula".equals(shippingStore)) {
            WebElement shippingStoreRadioElement = driver.findElement(shippingToRumbulaRadio);
            shippingStoreRadioElement.click();
        } else {
            System.out.println("Something went wrong in shipment store selection!");
        }
        Thread.sleep(1500);
    }

    public void fillCoupon(String coupon) {
        driver.findElement(couponField).sendKeys(coupon);
    }

    public void couponSubmit() throws InterruptedException {
        driver.findElement(couponField).submit();
        Thread.sleep(1500);
    }

    public void fillReceiverName() {
        driver.findElement(shippingNameField).sendKeys(customerData.getCustomerName());
    }

    public void fillReceiverSurname() {
        driver.findElement(shippingSurnameField).sendKeys(customerData.getCustomerSurname());
    }

    public void fillReceiverPhoneNumber() {
        driver.findElement(shippingPhoneNumberField).sendKeys(customerData.getCustomerPhoneNumber());
    }

    public void submitReceiverData() throws InterruptedException {
        WebElement submitReceiverDataElement = driver.findElements(shippingSubmitButton).get(0);
        submitReceiverDataElement.click();
        Thread.sleep(1500);
    }

    public void setCustomerFullData() {
        customerData.setCustomerFullName(driver.findElement(shippingFullNameText).getText());
        String receiverData = driver.findElement(shippingFullPhoneNumberText).getText();
        String[] receiverDataArr = receiverData.split(" ");
        customerData.setCustomerFullPhoneNumber(receiverDataArr[0]);
    }

    public void submitShippingForm() throws InterruptedException {
        WebElement submitShippingButtonElement = driver.findElements(shippingSubmitButton).get(1);
        submitShippingButtonElement.click();
        Thread.sleep(1500);
    }

    public void setFinalCustomerData() {
        String finalData = driver.findElement(billingReceiverTable).getText();
        String[] finalDataArr = finalData.split("\n");
        customerData.setFinalCustomerFullName(finalDataArr[0]);
        customerData.setFinalCustomerFullPhoneNumber(finalDataArr[1]);
    }
}
