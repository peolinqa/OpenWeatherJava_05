import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class KartavenkoPavelTest extends BaseTest {
private static final String URL = "https://openweathermap.org/";

    @Test
    public void testCheckThirtyOrangeButtons() throws InterruptedException {
        int expectedResult = 30;
        getDriver().get(URL);
        Thread.sleep(7000);
        getDriver().findElement(By.xpath("//ul[@id='first-level-nav']//a[@href='/api']")).click();
        List<WebElement> buttonList = getDriver().findElements(By.xpath("//a[contains(@class,'orange')]"));

        Assert.assertEquals(buttonList.size(), expectedResult);
    }
}
