import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class AnzhelikaBaaTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
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

        Thread.sleep(1000);

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
        Thread.sleep(7000);

        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id= 'desktop-menu']//a[@href='/guide']")
        );
        guideButton.click();
        Thread.sleep(5000);

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
    @Test
    public void testTempDisplayInF_WhenPressingFUnitsButton() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String fTempSymbol = "°F";
        boolean expectedResult = true;

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);

        WebElement fUnitsButton = getDriver().findElement(
                By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']")
        );
        fUnitsButton.click();

        Thread.sleep(5000);
        WebElement tempForCityInF = getDriver().findElement(
                By.xpath("//span[@class='heading'][contains(text(),'°F')]")
        );

        Thread.sleep(3000);
        boolean actualResult = tempForCityInF.getText().contains(fTempSymbol);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
