import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class DenSebrovskyTest extends BaseTest {

    @Test
    public void testFindThirtyOrangeButtons () throws InterruptedException {
        int expectedresult = 30;

        getDriver().get("https://openweathermap.org/");
        Thread.sleep(7000);
        getDriver().findElement(By.xpath(
                "//nav[@id = 'nav-website']//div[@id = 'desktop-menu']//a[@href = '/api']")).click();
        List<WebElement> orangeButtonsList = getDriver().findElements(By.xpath(
                "//a[contains(@class, 'orange')]"));

        Assert.assertEquals(expectedresult, orangeButtonsList.size());
    }
}
