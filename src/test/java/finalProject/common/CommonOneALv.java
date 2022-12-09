package finalProject.common;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CommonOneALv {
    public static ChromeDriver driver;
    public static Actions actions;

    public void startDriver() {
        String path = "C:\\Users\\extes\\IdeaProjects\\OneALvWebScrapper\\src\\test\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
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
