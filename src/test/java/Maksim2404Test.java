import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Maksim2404Test extends BaseTest {
    @Test
    public void testH2TextWhenSearchingCityCountry() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchCityField = getDriver().findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = getDriver().findElement(
                By.xpath("//button[@type = 'submit']")
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
    public void testConfirmThatSupportHave3Types() throws InterruptedException {
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        getDriver().get(url);
        Thread.sleep(7000);

        WebElement searchButton = getDriver().findElement(
                By.id("support-dropdown")
        );
        searchButton.click();
        Thread.sleep(2000);

        WebElement confirm1 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/faq']"));

        String actualResult1 = confirm1.getText();
        Thread.sleep(2000);

        WebElement confirm2 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='/appid']")
        );

        String actualResult2 = confirm2.getText();
        Thread.sleep(2000);

        WebElement confirm3 = getDriver().findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']")
        );

        String actualResult3 = confirm3.getText();


        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }
}