import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

        sleep(7000);
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

        sleep(7000);

        getDriver().findElement(
                By.name("q")).sendKeys("Rome", ENTER);

        Assert.assertTrue(getDriver().getCurrentUrl().contains("Rome"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("find"));
        Assert.assertTrue(getDriver().findElement(By.xpath("//input[@id='search_str']")).getText()
                .equals(getDriver().findElement(By.xpath("//input[@value='Rome']")).getText()));
    }

    @Test
    public void testTC_11_03() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);

        sleep(7000);

        String expectedResult = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. Any data collected is anonymised." +
                " You can allow all cookies or manage them individually.";

        WebElement textAtBottom = getDriver().findElement(By.xpath("//div[@class='stick-footer-panel__container']/p"));

        WebElement allAllowButton = getDriver().findElement(By.xpath("//div[@class='container']//button"));

        WebElement manageCookiesButton = getDriver().findElement(By.xpath("//a[@href='/cookies-settings']"));

        Assert.assertEquals(textAtBottom.getText(), expectedResult);
        Assert.assertTrue(allAllowButton.isDisplayed());
        Assert.assertEquals(allAllowButton.getText(), "Allow all");
        Assert.assertTrue(manageCookiesButton.isEnabled());

        manageCookiesButton.click();

        sleep(7000);

        Assert.assertNotEquals(getDriver().getCurrentUrl(), url);
    }
}
