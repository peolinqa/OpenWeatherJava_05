import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GdikhsanovTest extends BaseTest {

    @Test
    public void test1Openweathermap_justGoToGuide_gdiksanov() {

        String url = "https://openweathermap.org/";

        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        getDriver().get(url);
        getDriver().manage().window().maximize();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[@class='owm-loader-container']/div")));

        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        getDriver().findElement(By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")).click();

        String currentUrl = getDriver().getCurrentUrl();
        String currentTitle = getDriver().getTitle();

        Assert.assertEquals(currentUrl, "https://openweathermap.org/guide");
        Assert.assertEquals(currentTitle, "OpenWeatherMap API guide - OpenWeatherMap");
    }
}

