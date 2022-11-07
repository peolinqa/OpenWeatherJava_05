import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NataliadylaiTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";


        getDriver().get(url);
        Thread.sleep(5000);

        WebElement searchCityField =
                getDriver().findElement(
                        By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(2000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_OpenWeatherMapAPIGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";
        getDriver().get(url);
        Thread.sleep(5000);

        WebElement guideHeader = getDriver().findElement(
                By.xpath("//div/ul/li/a[@href = '/guide']"));
        guideHeader.click();
        Thread.sleep(2000);
        String actualResult1 = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult1, expectedResult1);
        String actualResult2 = getDriver().getTitle();
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}