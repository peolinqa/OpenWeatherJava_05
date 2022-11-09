import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class IriSamoTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(8000);

        WebElement searchCity = getDriver().findElement(
                By.xpath("//input[@placeholder='Search city']")
        );
        Thread.sleep(1000);
        searchCity.click();
        searchCity.sendKeys(cityName);
        Thread.sleep(1000);
        WebElement buttonSearch = getDriver().findElement(
                By.xpath("//button[@type='submit']")
        );
        buttonSearch.click();
        Thread.sleep(1000);
        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//span[text()='Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id=\"weather-widget\"]//h2")
        );
        Thread.sleep(2000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}