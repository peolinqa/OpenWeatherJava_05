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
    public void testTemperatureFormatSelectionInFahrenheit() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        WebElement switchToFahrenheit = getDriver().findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
        );
        Thread.sleep(5000);
        switchToFahrenheit.click();
        char expectedResult = 'F';
        WebElement findFahrenheit = getDriver().findElement(
                By.xpath("//span[@class='heading'][contains(text(),'F')]")
        );
        String result = findFahrenheit.getText();
        char actualResult = result.charAt(result.length()-1);

        Assert.assertEquals(actualResult,expectedResult);
    }



}

