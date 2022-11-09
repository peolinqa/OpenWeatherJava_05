import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class IreneSnitkoTest extends BaseTest {

    private static final String BASE_URL = "https://openweathermap.org/";

    @Test
    public void testGuideMenuOpeningGuidePage() throws InterruptedException {

        String guideMenuText = "Guide";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        getDriver().get(BASE_URL);
        getDriver().manage().window().maximize();
        Thread.sleep(8000);

        WebElement guideMenu = getDriver().findElement(By.linkText(guideMenuText));
        guideMenu.click();
        Thread.sleep(2000);

        String actualResult1 = getDriver().getCurrentUrl();
        String actualResult2 = getDriver().getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}