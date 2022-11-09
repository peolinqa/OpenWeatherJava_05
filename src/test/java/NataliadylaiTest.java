import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class NataliadylaiTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";


        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchCityField =
                getDriver().findElement(
                        By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(2000);

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

        WebElement guideHeader = getDriver().findElement(
                By.xpath("//div/ul/li/a[@href = '/guide']"));
        guideHeader.click();
        Thread.sleep(2000);
        String actualResult1 = getDriver().getCurrentUrl();
        Assert.assertEquals(actualResult1, expectedResult1);
        String actualResult2 = getDriver().getTitle();
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Test
    public void testChangingTempUnitInHeading_WhenSwitchTempUnitButton() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(7000);
        WebElement tempUnit = getDriver().findElement(
                By.xpath("//div[text()='Imperial: °F, mph']"));
        tempUnit.click();
        Thread.sleep(2000);
        WebElement tempUnitHeading = getDriver().findElement(
                By.xpath("//div[@class='current-temp']/span"));

        boolean actualResult = tempUnitHeading.getText().contains("°F");

        Assert.assertTrue(actualResult);
    }
    @Test
    public void test_ConfirmCookiesOnTheFooter() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String button1 = "Allow";
        String button2 = "Manage cookies";
        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);
        Assert.assertTrue(getDriver().findElement(By.className("stick-footer-panel__container")).isDisplayed());
        Assert.assertEquals(getDriver().findElements(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*")).size(), 2);
        WebElement cookies = getDriver().findElement(
                By.className("stick-footer-panel__description"));
        Thread.sleep(2000);
        String actualResult = cookies.getText();
        Assert.assertEquals(actualResult,expectedResult);

        Assert.assertTrue(getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = 'Allow all']")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']/*[text() = ' Manage cookies ']")).isDisplayed());
    }
}