import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
public class elenuraTest extends BaseTest {
    @Test
    public void testH2TagTextWhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@ placeholder =  'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);


        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();
        Assert.assertEquals(actualResult, expectedResult);

        getDriver().quit();
    }

    @Test

    public void testTitleOpenWeatherMapAPIGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResultOpenPage = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(5000);

        WebElement Title = getDriver().findElement(
                By.xpath("//div/ul/li/a[@href='/guide']")
        );
        Title.click();
        Thread.sleep(3000);

        Assert.assertEquals(getDriver().getCurrentUrl(),expectedResultOpenPage);
        Assert.assertEquals(getDriver().getTitle(),expectedResultTitle);
    }

}
