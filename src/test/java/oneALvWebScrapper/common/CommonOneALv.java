package oneALvWebScrapper.common;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class CommonOneALv {
    public static ChromeDriver driver;
    public static Actions actions;

    public void startDriver() {
        String driverPath = "C:\\Users\\extes\\IdeaProjects\\OneALvWebScrapper\\src\\test\\resources\\chromedriver.exe";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver(); //send options for headless
        actions = new Actions(driver);
        driver.manage().window().setSize(new Dimension(1500, 1000));
    }

    public void scrollPage(int verticalPixels) {
        driver.executeScript("window.scrollBy(0," + verticalPixels + ")", "");
    }

    public void stopDriver() {
        driver.close();
    }
}
