import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KsutitovaTest extends BaseTest {

    @Test
    public void testH2TextOpenWeatherMapInGuideLink() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement menuGuide = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']")
        );


        menuGuide.click();

        String urlGuede = getDriver().getCurrentUrl();
        String title = getDriver().getTitle();

        Assert.assertEquals(urlGuede, expectedResultUrl);
        Assert.assertEquals(title, expectedResultTitle);
    }


    @Test
    public void testConfirmTemperatureFaringate() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement imperialF = getDriver().findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
        );
        Thread.sleep(5000);
        imperialF.click();

        WebElement faringate = getDriver().findElement(
                By.xpath("//div[@class = 'current-temp']/span")
        );

        Thread.sleep(5000);
        String actualResult = faringate.getText();

        Assert.assertTrue(actualResult.contains("F"));

    }

}
