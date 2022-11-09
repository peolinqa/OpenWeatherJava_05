import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class GalinaKuklevaTest extends BaseTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(baseUrl);
        Thread.sleep(7000);
        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(3000);
        WebElement searchButton = getDriver().findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(3000);
        WebElement firstChoiceInDropdownMenu = getDriver().findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        firstChoiceInDropdownMenu.click();
        WebElement h2CityCountryHeader = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(3000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testOpenWeatherMapGuidePageTitle() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        String expectedResult_1 = "https://openweathermap.org/guide";
        String expectedResult_2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(baseUrl);
        WebElement guideButton = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//li/a [@href='/guide']")
        );
        Thread.sleep(7000);
        guideButton.click();

        String actualResult_1 = getDriver().getCurrentUrl();
        String actualResult_2 = getDriver().getTitle();

        Assert.assertEquals(actualResult_1, expectedResult_1);
        Assert.assertEquals(actualResult_2, expectedResult_2);
    }

    @Test
    public void testTemperatureInFahrenheits() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        String expectedResult = "°F";

        getDriver().get(baseUrl);
        Thread.sleep(7000);

        WebElement imperialData = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']")
        );

        imperialData.click();
        WebElement tempInTheCity = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//span[@class = 'heading']")
        );
        Boolean actualResult = tempInTheCity.getText().contains(expectedResult);

        Assert.assertTrue(actualResult, expectedResult);
    }

    @Test
    public void testNonCaptchaVerification() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        String eMail = "tester@test.com";
        String message = "Hello, World!";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        getDriver().get(baseUrl);
        getDriver().manage().window().maximize();
        Thread.sleep(7000);
        WebElement supportButton = getDriver().findElement(
                By.xpath("//div[@id='support-dropdown']")
        );

        supportButton.click();
        Thread.sleep(5000);
        WebElement subMenuAskAQuestion = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text() = 'Ask a question']")
        );
        subMenuAskAQuestion.click();
        Thread.sleep(10000);
        String originalWindow = getDriver().getWindowHandle();
        assert getDriver().getWindowHandles().size() != 1;
        Thread.sleep(10000);
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
        WebElement emailField = getDriver().findElement(
                By.id("question_form_email")
        );
        emailField.sendKeys(eMail);
        Thread.sleep(5000);
        WebElement subjectField = getDriver().findElement(
                By.id("question_form_subject")
        );
        subjectField.click();
        WebElement optionTech = getDriver().findElement(
                By.xpath("//select[@id = 'question_form_subject']/option[@value = 'Tech Issue']")
        );
        optionTech.click();
        Thread.sleep(5000);

        WebElement messageField = getDriver().findElement(
                By.id("question_form_message")
        );
        messageField.sendKeys(message);
        Thread.sleep(5000);
        WebElement submitField = getDriver().findElement(
                By.xpath("//form[@id = 'new_question_form']//input[@value = 'Submit']")
        );
        submitField.click();
        WebElement submitVerification = getDriver().findElement(
                By.xpath("//div[text() = 'reCAPTCHA verification failed, please try again.']")
        );
        String actualResult = submitVerification.getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testDeskTopMenuClickAPIFind30Buttons1() throws InterruptedException {

        String baseUrl = "https://openweathermap.org/";
        getDriver().get(baseUrl);
        getDriver().manage().window().maximize();
        int expectedResult = 30;
        Thread.sleep(7000);
        WebElement element = getDriver().findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        element.click();
        int actualResult = getDriver().findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
