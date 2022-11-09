import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        Thread.sleep(7000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id=\"weather-widget\"]//input[@placeholder='Search city']")
        );
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

        Thread.sleep(7000);
        WebElement switchToFahrenheit = getDriver().findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
        );

        switchToFahrenheit.click();
        char expectedResult = 'F';
        WebElement findFahrenheit = getDriver().findElement(
                By.xpath("//span[@class='heading'][contains(text(),'F')]")
        );
        String result = findFahrenheit.getText();
        char actualResult = result.charAt(result.length()-1);

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testOpenWeatherPageGuidMaximize() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedURL = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);
        WebElement searchButtonGUid = getDriver().findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']")
        );

        searchButtonGUid.click();
        String actualResultURL = getDriver().getCurrentUrl();
        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResultURL,expectedURL);
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void testApprovinfButtonsAllow_allAndManage_cookies() throws InterruptedException {
        String url = "https://openweathermap.org/";
        getDriver().get(url);
        Thread.sleep(7000);
        String expectedResultPanelText = "We use cookies which are essential for the site to work. We also use non-essential" +
                " cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies" +
                " or manage them individually.";
        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";
        WebElement panelText = getDriver().findElement(
                By.xpath("//p[@class='stick-footer-panel__description']")
        );
        String actualResultPanelText = panelText.getText();
        WebElement buttonAllowAll = getDriver().findElement(
                By.xpath("//button[@class='stick-footer-panel__link']")
        );
        String actualResultAllowAll = buttonAllowAll.getText();
        WebElement buttonManageCookies = getDriver().findElement(
                By.xpath("//a[@class='stick-footer-panel__link'][text()=' Manage cookies ']"));
        String actualResultManageCookies = buttonManageCookies.getText();

        Assert.assertEquals(actualResultPanelText,expectedResultPanelText);
        Assert.assertEquals(actualResultAllowAll,expectedResultAllowAll);
        Assert.assertEquals(actualResultManageCookies,expectedResultManageCookies);
         }



}

