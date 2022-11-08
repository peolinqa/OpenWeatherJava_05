import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SyniaginaTest extends BaseTest {
    @Test
    public void testGuideHeaderAndURL() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResult2 = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(6000);
        WebElement buttonGuide = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")
        );
        buttonGuide.click();
        Thread.sleep(6000);

        String actualResult1 = getDriver().getTitle();
        String actualResult2 = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}
