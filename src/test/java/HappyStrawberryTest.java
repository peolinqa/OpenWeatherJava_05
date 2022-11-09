import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class HappyStrawberryTest extends BaseTest {




       @Test

       public void testH2TagWhensearchingCityCountry() throws InterruptedException {
           String url = "https://openweathermap.org/";
           String cityName = "Paris";
           String expectedResult = "Paris, FR";

           getDriver().get(url);
           Thread.sleep(7000);

           WebElement searchCityField = getDriver().findElement(
                   By.xpath("//input[@placeholder='Search city']"
                   ));

           searchCityField.click();
           searchCityField.sendKeys(cityName);


           WebElement searchButton = getDriver().findElement(
                   By.xpath("//div[@id='weather-widget']//button[@type='submit']")
           );
           searchButton.click();
           Thread.sleep(2000);

           WebElement parisFrChoiceInDropdownMenu = getDriver().findElement(
                   By.xpath("//ul[@class='search-dropdown-menu']//li//span[text() = 'Paris, FR ']")
           );
           parisFrChoiceInDropdownMenu.click();


           WebElement h2CittCountryHeader = getDriver().findElement(
                   By.xpath("//div[@id= 'weather-widget']//h2")
           );

           Thread.sleep(2000);//задержка запуска сайта

           String actualResult = h2CittCountryHeader.getText();


           Assert.assertEquals(actualResult, expectedResult);

       }

}
