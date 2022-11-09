import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ViktoriyaEDTest extends BaseTest {

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
                By.xpath("//div[@id='weather-widget']//button[@type='submit']\n")
        );
        searchButton.click();

        Thread.sleep(2000);

        WebElement parisFrChoiceInDropDownMenu = getDriver().findElement(
                By.xpath("//ul[@class=\"search-dropdown-menu\"]//li//span[text() = 'Paris, FR ']")
        );
        parisFrChoiceInDropDownMenu.click();

        WebElement h2CityNameHeader = getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(2000);

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
        Thread.sleep(7000);
        getDriver().findElement(By.xpath("//a[@href = '/guide']")).click();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedResult);
        Assert.assertEquals(getDriver().getTitle(), expectedResult1);
    }

    @Test
    public void test_CheckWeather_WhenChooseFahrenheit() throws InterruptedException {

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(url);
        Thread.sleep(7000);

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
        Thread.sleep(7000);

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
        Thread.sleep(7000);

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

    @Test
    public void test_ClickSubmitWithoutCaptcha() throws InterruptedException {

        String url = "https://openweathermap.org/";

        String emailTest = "tester@test.com";
        String message = "This is test";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        getDriver().get(url);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);

        getDriver().findElement(By.xpath("//div[@id='support-dropdown']")).click();
        getDriver().findElement(By.linkText("Ask a question")).click();

        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        Assert.assertEquals(tabs.size(), 2);
        getDriver().switchTo().window(tabs.get(1));
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='question_form_email']")));

        getDriver().findElement(By.id("question_form_email")).sendKeys(emailTest);
        getDriver().findElement(By.xpath("//select[@id='question_form_subject']")).click();
        getDriver().findElement(By.xpath("//select[@id='question_form_subject']//option[text()='Other']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='question_form_message']")).sendKeys(message);
        getDriver().findElement(By.xpath("//input[@data-disable-with='Create Question form']")).click();
        String actualResult = getDriver().findElement(By.xpath("//div[@class='help-block']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void test_SwitchFahrenheitToCelsius() throws InterruptedException {

        String url = "https://openweathermap.org/";
        Boolean expectedResult = true;

        getDriver().get(url);
        Thread.sleep(7000);

        getDriver().findElement(By.xpath("//div[text()='Imperial: °F, mph']")).click();
        WebElement displayCurrentWeather = getDriver().findElement(By.xpath("//div[@class='current-temp']/span"));

        Assert.assertEquals(displayCurrentWeather.getText().contains("°F"), expectedResult);

        getDriver().findElement(By.xpath("//div[text()='Metric: °C, m/s']")).click();

        Assert.assertEquals(displayCurrentWeather.getText().contains("°C"), expectedResult);
    }
}






