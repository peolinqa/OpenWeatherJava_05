import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MaxIaskoTest extends BaseTest {

    @Test
    public void testSearch30OrangeButtons() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);
        int expectedResult = 30;

        Thread.sleep(7000);
        WebElement searchApiPage = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        searchApiPage.click();

        int actualResult = getDriver().findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange')]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testConfirmLinkNoChangeByLogoClick() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchLogoField = getDriver().findElement(By.xpath("//a[@href ='/']"));
        searchLogoField.click();

        String actualResult = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult, url);
    }
}




