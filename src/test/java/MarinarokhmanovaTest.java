import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class MarinarokhmanovaTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id= 'weather-widget']//input[@ placeholder='Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceINDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class ='search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceINDropdownMenu.click();

        WebElement h2CityCountryHeard = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeard.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void testOpenWeatherMapGuide() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);

        WebElement guideSearchField = getDriver().findElement(
                By.xpath("//a[@href][text()='Guide']")
        );
        guideSearchField.click();
        Thread.sleep(2000);

        String confirmPageWithLink = getDriver().getTitle();

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = confirmPageWithLink.intern();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}
