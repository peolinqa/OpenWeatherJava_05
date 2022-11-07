import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Elena_STest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id=\"weather-widget\"]//input[@placeholder='Search city']")
        );
        Thread.sleep(5000);
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id=\"weather-widget\"]//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement choiceDropDownMenuParisFrance = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        choiceDropDownMenuParisFrance.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(5000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);

    }

    @Test
    public void testOpenWeatherPageGuidMaximixe() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedURL = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        getDriver().get(url);
//        getDriver().manage().window().maximize(); //Maximize current window
        WebElement searchButtonGUid = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']")
        );

        Thread.sleep(5000);
        searchButtonGUid.click();
        String actualResultURL = getDriver().getCurrentUrl();
        String actualResult = getDriver().getTitle();
        Assert.assertEquals(actualResultURL,expectedURL);
        Assert.assertEquals(actualResult,expectedResult);

    }
}
