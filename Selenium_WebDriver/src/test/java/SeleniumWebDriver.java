import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumWebDriver {
    public static final String WEBDRIVER = "webdriver.chrome.driver";
    public static final String DRIVER_PATH = "src/main/Drivers/chromedriver.exe";
    public static final String URL = "https://www.toolsqa.com/";
    public static WebDriver webDriver;

    @BeforeClass
    public void initWebDriver() {
        System.setProperty(WEBDRIVER, DRIVER_PATH);
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
    }

    @Test
    public void testClickButton() {
        webDriver.get(URL);
        WebElement enrollButton = webDriver.findElement(By.cssSelector(".btn-primary-shadow"));
        enrollButton.click();
        String expectedUrl = "https://www.toolsqa.com/selenium-training";
        String actualUrl = webDriver.getCurrentUrl();
        assertTrue(actualUrl.contains(expectedUrl));
    }

    @Test
    public void testGetEmail() {
        webDriver.get(URL);
        WebElement contactEmail = webDriver.findElement(By.cssSelector(".contact-detail"));
        String expectedUrl = "support@toolsqa.com";
        String actualUrl = contactEmail.getText();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testSearchEngine() {
        webDriver.get(URL);
        WebElement search = webDriver.findElement(By.cssSelector("input"));
        search.sendKeys("Selenium Learn" + Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("https://www.toolsqa.com/search?keyword=Selenium+Learn"));
        WebElement element = webDriver.findElement(By.cssSelector("h1"));
        String expectedText = "Search - \"Selenium Learn\"";
        String actualText = element.getText();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void testClickOnCourse() {
        webDriver.get(URL);
        WebElement course = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/a[5]/div[2]/div[2]"));
        course.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("https://www.toolsqa.com/git/git-tutorial/"));
        WebElement element = webDriver.findElement(By.cssSelector("main > h2"));
        String expectedText = "Git Tutorial";
        String actualText = element.getText();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void testAppearingOfComponent() {
        webDriver.get(URL);
        WebElement course = webDriver.findElement(By.xpath("//*[@id=\"tns1-ow\"]/div[1]/button[2]"));
        course.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//*[@id=\"tns1-item4\"]"))));
        WebElement element = webDriver.findElement(By.xpath("//*[@id=\"tns1-item4\"]"));
        assertTrue(element.isDisplayed());
    }
}
