import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.Keys.ENTER;

public class DmswlpkTest extends BaseTest {
    @Test
    public void testTC_11_04() throws InterruptedException {
        String url = "https://openweathermap.org/";

        getDriver().get(url);
        String expectedResult = "FAQ".concat("How to start".concat("Ask a question"));

        sleep(5000);
        getDriver().manage().window().maximize();

        WebElement menuSupport = getDriver().findElement(By.xpath("//div[@id='support-dropdown']"));

        menuSupport.click();

        List<WebElement> allSupportMenu = getDriver().findElements(By.xpath("//ul[@id='support-dropdown-menu']/li/a"));

        String actualResult = "";

        for (WebElement supportMenu : allSupportMenu) {
            actualResult += supportMenu.getText();
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTC_11_09() throws InterruptedException {
        String url = "https://openweathermap.org/";

        getDriver().get(url);

        sleep(4000);

        getDriver().findElement(
                By.name("q")).sendKeys("Rome", ENTER);

        Assert.assertTrue(getDriver().getCurrentUrl().contains("Rome"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("find"));
        Assert.assertTrue(getDriver().findElement(By.xpath("//input[@id='search_str']")).getText()
                .equals(getDriver().findElement(By.xpath("//input[@value='Rome']")).getText()));
    }
}
