import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Dasha1991Test extends BaseTest {
    @Test
    public void testButtonGuide_OpenWeather() throws InterruptedException {


        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement pushButtonGuide = getDriver().findElement(By.xpath("//a[@href = '/guide']"));

        pushButtonGuide.click();
        Thread.sleep(2000);

        String actualResult1 = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult1, expectedResult1);


        String actualResult2 = getDriver().getTitle();
        Assert.assertEquals(actualResult2, expectedResult2);

    }
    @Test
    public void testTemperatureF() throws InterruptedException {

        String url = "https://openweathermap.org/";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);

        WebElement temperatureF = getDriver().findElement(By.xpath("//div[@class = 'option'][2]"));

        temperatureF.click();
        Thread.sleep(2000);

        WebElement imageFTemperature = getDriver().findElement(By.xpath("//span[@class = 'heading']"));

        boolean actualResult = imageFTemperature.getText().contains("°F");
        Assert.assertTrue(actualResult);

    }
}
