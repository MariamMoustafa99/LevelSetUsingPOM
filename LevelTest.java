package LevelSet;

import PageObjects.Home;
import PageObjects.GetPaid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class LevelTest {
    WebDriver driver;

    Home home;
    GetPaid getPaid;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.levelset.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        home = new Home(driver);
        getPaid = new GetPaid(driver);
    }

    @Test
    public void NavigateToGetPaid() {
        home.BtnClick();
    }

    @Test
    public void ValidateFreeDocuments() {
        getPaid.validateFreeDocumentsCount(2);
        List<String> expectedTexts = Arrays.asList("Exchange a Waiver", "Send a Payment Document");
        getPaid.validateFreeDocumentsText(expectedTexts);
    }

    @Test
    public void ValidatePricedDocuments() {
        getPaid.validatePricedDocumentsCount(1);
        List<String> expectedTexts = Arrays.asList("Send a Warning");
        getPaid.validatePricedDocumentsText(expectedTexts);
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
