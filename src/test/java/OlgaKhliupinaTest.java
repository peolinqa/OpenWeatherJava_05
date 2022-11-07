import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class OlgaKhliupinaTest extends BaseTest {

   @Test
   public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

      String url = "https://openweathermap.org/";
      String cityName = "Paris";
      String expectedResult = "Paris, FR";

      getDriver().manage().window().maximize();
      getDriver().get(url);
      Thread.sleep(6000);

      WebElement searchCityField = getDriver().findElement(
              By.xpath("//div[@id='weather-widget']//input[@placeholder='Search city']"));
      searchCityField.click();
      searchCityField.sendKeys(cityName);

      WebElement searchButton = getDriver().findElement(By.xpath("//button[@type='submit']"));
      searchButton.click();
      Thread.sleep(1000);

      WebElement parisFranceChoiceInDropdownMenu = getDriver().findElement(
              By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']"));
      parisFranceChoiceInDropdownMenu.click();

      WebElement h2CityCountryHeader = getDriver().findElement(By.xpath("//div[@id='weather-widget']//h2"));

      Thread.sleep(2000);
      String actualResult = h2CityCountryHeader.getText();

      Assert.assertEquals(actualResult, expectedResult);
   }

   @Test
   public void testCookiePanel_BottomMainPage_WhenOpenWebsite() throws InterruptedException {

      String url = "https://openweathermap.org/";

      String expectedResult_CookieText = "We use cookies which are essential for the site to work. We also use non-essential " +
              "cookies to help us improve our services. Any data collected is anonymised. You can allow all " +
              "cookies or manage them individually.";
      String expectedResult_ButtonAllowAll = "Allow all";
      String expectedResult_ButtonManageCookies = "Manage cookies";

      getDriver().manage().window().maximize();
      getDriver().get(url);
      Thread.sleep(6000);

      Assert.assertTrue(getDriver().findElement(By.className("stick-footer-panel__container")).isDisplayed());

      WebElement cookieBanner = getDriver().findElement(
              By.className("stick-footer-panel__description"));
      String actualResult_CookieText = cookieBanner.getText();

      Assert.assertEquals(actualResult_CookieText, expectedResult_CookieText);

      Assert.assertEquals(getDriver().findElements(
              By.xpath("//div[@class='stick-footer-panel__btn-container']/*")).size(), 2);

      WebElement buttonAllowAll = getDriver().findElement(By.xpath("//div/button[@type='button']"));

      String actualResult_ButtonAllowAll = buttonAllowAll.getText();

      Assert.assertEquals(actualResult_ButtonAllowAll, expectedResult_ButtonAllowAll);

      WebElement buttonManageCookies = getDriver().findElement(
              By.xpath("//div/a[@href='/cookies-settings']"));

      String actualResult_ManageCookies = buttonManageCookies.getText();

      Assert.assertEquals(actualResult_ManageCookies, expectedResult_ButtonManageCookies);
   }
}
