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
        Thread.sleep(7000);

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
        Thread.sleep(7000);

        WebElement temperatureF = getDriver().findElement
                (By.xpath("//div[text()='Imperial: °F, mph']"));

        temperatureF.click();
        Thread.sleep(2000);
        WebElement imageTempF = getDriver().findElement(By.xpath("//div[@class ='current-temp']"));
        Boolean actualResult = imageTempF.getText().contains(symbolF);
        Assert.assertEquals(actualResult,expectedResult);

    }
    @Test
    public void testConfirmFooterText() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult1 = "Allow all";
        String expectedResult2 = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(7000);
        Assert.assertTrue(getDriver().findElement
                (By.xpath("//div[@id='stick-footer-panel']//div[@class='container']")).isDisplayed());

        WebElement textFooter = getDriver().findElement(By.className("stick-footer-panel__description"));
        String actualResult = textFooter.getText();
        Assert.assertEquals(actualResult, expectedResult);

        Assert.assertEquals(getDriver().findElements
                (By.xpath("//div[@class='stick-footer-panel__btn-container']/*")).size(),2);

        WebElement buttonAllowAllFooter = getDriver().findElement(By.xpath("//button[@type='button']"));
        String actualResult1 = buttonAllowAllFooter.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        WebElement buttonManageCookiesFooter =getDriver().findElement
                (By.xpath("//a[@href='/cookies-settings']"));

        Assert.assertEquals(buttonManageCookiesFooter.getText(), expectedResult2);
    }

}
