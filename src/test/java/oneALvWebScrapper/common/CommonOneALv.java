package oneALvWebScrapper.common;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.security.Key;
import java.time.Duration;
import java.util.NoSuchElementException;

public class CommonOneALv {
    public static ChromeDriver driver;
    public static Actions actions;
    public static Wait<WebDriver> wait;
    public SoftAssertions softAssertions = new SoftAssertions();

    public void startDriver() {
        String driverPath = "C:\\Users\\extes\\IdeaProjects\\OneALvWebScrapper\\src\\test\\resources\\chromedriver.exe";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver(); //send options for headless
        actions = new Actions(driver);
        wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class, IndexOutOfBoundsException.class);
        driver.manage().window().setSize(new Dimension(1500, 1000));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    public void scrollPage(int verticalPixels) {
        driver.executeScript("window.scrollBy(0," + verticalPixels + ")", "");
    }

    public void scrollIntoView(By locator) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        Thread.sleep(500);
    }

    public void scrollUntilLocatorFoundAndClick(By locator) {
        actions.sendKeys(Keys.HOME).build().perform();
        for (int i = 0; i < 500; i++) {
            try {
                driver.findElement(locator).click();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                actions.sendKeys(Keys.DOWN);
            }
        }
    }

    public void scrollUntilElementFoundAndClick(WebElement element) {
        actions.sendKeys(Keys.HOME).build().perform();
        for (int i = 0; i < 500; i++) {
            try {
                element.click();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                actions.sendKeys(Keys.DOWN);
            }
        }
    }

    public void checkLocatorAvailabilityAndClick(By locator) {
        for (int i = 0; i < 500; i++) {
            try {
                driver.findElement(locator).click();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stopDriver() {
        driver.close();
    }
}
