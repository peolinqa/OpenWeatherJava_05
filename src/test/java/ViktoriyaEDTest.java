import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class ViktoriyaEDTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(6000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//div[@id='weather-widget']//button[@type='submit']\n")
        );
        searchButton.click();

        Thread.sleep(2000);

        WebElement parisFrChoiceInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class=\"search-dropdown-menu\"]//li//span[text() = 'Paris, FR ']")
        );
        parisFrChoiceInDropDownMenu.click();

        WebElement h2CityNameHeader = getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(1000);

        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_TitleText_WhenChooseMenuGuide() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/guide";
        String expectedResult1 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(6000);
        getDriver().findElement(By.xpath("//a[@href = '/guide']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
        Assert.assertEquals(getDriver().getTitle(), expectedResult1);
    }

    @Test
    public void test_CheckWeather_WhenChooseFahrenheit() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(url);
        Thread.sleep(6000);

        getDriver().findElement(By.xpath("//div[@class='switch-container']/div[3]")).click();
        WebElement weatherInF = getDriver().findElement(By.xpath("//span[@class='heading']"));
        Thread.sleep(2000);

        Assert.assertTrue(weatherInF.getText().contains(expectedResult));
    }

    @Test
    public void test_CheckCookiesText() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential " +
                "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies " +
                "or manage them individually.";
        String expectedResult1 = "Allow all";
        String expectedResult3 = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(6000);

        Assert.assertTrue(getDriver().findElement(By.className("stick-footer-panel__container")).isDisplayed());

        WebElement cookiesText = getDriver().findElement(By.xpath("//div[@id='stick-footer-panel']//p"));
        WebElement allowAllButton = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//button")
        );
        WebElement manageCookiesButton = getDriver().findElement(
                By.xpath("//div[@id='stick-footer-panel']//a[@href='/cookies-settings']")
        );

        Assert.assertEquals(cookiesText.getText(), expectedResult);
        Assert.assertEquals(allowAllButton.getText(), expectedResult1);
        Assert.assertEquals(manageCookiesButton.getText(), expectedResult3);
    }

    @Test
    public void testSupportDropDownMenu() throws InterruptedException {

        String url = "https://openweathermap.org/";

        String expectedResult = "FAQ";
        String expectedResult1 = "How to start";
        String expectedResult2 = "Ask a question";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(6000);

        WebElement menuSupportDropDown = getDriver().findElement(By.xpath("//div[@id='support-dropdown']"));
        menuSupportDropDown.click();

        WebElement FAQ = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']")
        );
        WebElement HowToStart = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']")
        );
        WebElement AskAQuestion = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@target='_blank']")
        );

        Assert.assertEquals(FAQ.getText(), expectedResult);
        Assert.assertEquals(HowToStart.getText(), expectedResult1);
        Assert.assertEquals(AskAQuestion.getText(), expectedResult2);
    }
}

