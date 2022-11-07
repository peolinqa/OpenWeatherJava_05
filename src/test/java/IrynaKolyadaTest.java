import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class IrynaKolyadaTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(5000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement seatchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']"));
        seatchButton.click();

        Thread.sleep(1000);
        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']"
                ));

        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//h2"));

        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

    }
    @Test
    public void testTemperatureInFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        Boolean expectedResult = true;
        String symbolF = "°F";

        getDriver().get(url);
        Thread.sleep(5000);

        WebElement temperatureF = getDriver().findElement
                (By.xpath("//div[text()='Imperial: °F, mph']"));

        temperatureF.click();
        Thread.sleep(2000);
        WebElement imageTempF = getDriver().findElement(By.xpath("//div[@class ='current-temp']"));
        Boolean actualResult = imageTempF.getText().contains(symbolF);
        Assert.assertEquals(actualResult,expectedResult);

    }

}
