package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GetPaid {
    WebDriver driver;

    public GetPaid(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[(@class='price-amount') and text()='Free']")
    public List<WebElement> freeDocuments;

    @FindBy(xpath = "//span[contains(text(),'Free')]//parent::div//parent::div//child::div[@class='left']")
    public List<WebElement> freeDocumentsText;

    @FindBy(xpath = "//span[contains(@class,\"price-amount\") and number(translate(text(),\"$\",\"\"))>=30 and number(translate(text(),\"$\",\"\"))<=60]")
    public List<WebElement> pricedDocuments;

    @FindBy(xpath = "//span[contains(@class,\"price-amount\") and number(translate(text(),\"$\",\"\"))>=30 and number(translate(text(),\"$\",\"\"))<=60]//parent::div//parent::div//child::div[@class='left']")
    public List<WebElement> pricedDocumentsText;


    public void validateFreeDocumentsCount(int expectedCount) {
        Assert.assertEquals(freeDocuments.size(), expectedCount, "Free documents count mismatch");
    }

    public void validateFreeDocumentsText(List<String> expectedTexts) {
        List<String> actualTexts = new ArrayList<>();
        for (WebElement element : freeDocumentsText) {
            actualTexts.add(element.getText());
        }

        Assert.assertEquals(actualTexts, expectedTexts, "Free documents text mismatch");
    }

    public void validatePricedDocumentsCount(int expectedCount) {
        Assert.assertEquals(pricedDocuments.size(), expectedCount, "Priced documents count mismatch");
    }

    public void validatePricedDocumentsText(List<String> expectedTexts) {
        List<String> actualTexts = new ArrayList<>();
        for (WebElement element : pricedDocumentsText) {
            actualTexts.add(element.getText());
        }

        Assert.assertEquals(actualTexts, expectedTexts, "Priced documents text mismatch");
    }

}
