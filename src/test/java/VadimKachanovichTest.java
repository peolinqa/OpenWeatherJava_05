import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class VadimKachanovichTest extends BaseTest {
    @Test
    public void testWhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(2000);

        WebElement parisFRChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(3000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckTitleName() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedGuideLink = "https://openweathermap.org/guide";
        String expectedTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        Thread.sleep(7000);
        WebElement guideTab = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[@href='/guide']")
        );

        guideTab.click();
        Thread.sleep(2000);
        String actualGuideLink = getDriver().getCurrentUrl();
        Thread.sleep(5000);
        String actualTitle = getDriver().getTitle();
        Assert.assertEquals(actualGuideLink, expectedGuideLink);
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
