import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class IvanRamin7Test extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(5000);
        WebElement searchCity = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCity.click();
        searchCity.sendKeys(cityName);
        WebElement searchButton = getDriver().findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(3000);
        WebElement parisFRChoiceFromDropdownMenu = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//ul[@class = 'search-dropdown-menu']//span[text() = 'Paris, FR ']")
        );
        parisFRChoiceFromDropdownMenu.click();
        Thread.sleep(3000);
        WebElement h2CityNameHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        String actualResult = h2CityNameHeader.getText();


        Assert.assertEquals(actualResult, expectedResult);
    }
}