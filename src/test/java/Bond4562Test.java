import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Bond4562Test extends BaseTest {

    @Test
    public void testClickGuideAndVerifyTitleandUrl() throws InterruptedException {

        String urlBase = "https://openweathermap.org/";
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedtResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";


        getDriver().get(urlBase);

        Thread.sleep(7000);


        getDriver().findElement(
                By.xpath("//nav[@id='nav-website']//div[@id='desktop-menu']//a[@href='/guide']")
        ).click();
        Thread.sleep(2000);

        String actualResultUrl = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();


        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedtResultTitle);
    }

}
