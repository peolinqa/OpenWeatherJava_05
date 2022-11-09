import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class TimberhutAskQuestionWeatherTest extends BaseTest {

    @Test
    public void timberhutGitHubTestAskQuestionForm() throws InterruptedException {

        String email = "test@gmail.com";
        String expectedResultAnswerMessage = "reCAPTCHA verification failed, please try again.";
        String message = "How to start question";

        getDriver().get("https://openweathermap.org/");
        Thread.sleep(7000);
        WebElement supportMenu = getDriver().findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        supportMenu.click();

        WebElement supportDropDownMenuAskQuestion = getDriver().findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']" +
                        "//a[@href='https://home.openweathermap.org/questions']")
        );
        supportDropDownMenuAskQuestion.click();
        Thread.sleep(2000);

        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }

        WebElement emailTextArea = getDriver().findElement(
                By.xpath("//div[@class = 'col-sm-8']//input[@id='question_form_email']")
        );
        emailTextArea.click(); emailTextArea.sendKeys(email);

        WebElement subjectTextArea = getDriver().findElement(
                By.xpath("//select[@class= 'form-control select required']")
        );
        subjectTextArea.click();

        WebElement subjectTextMenuTech = getDriver().findElement(
                By.xpath("//select[@class= 'form-control select required']//option[@value='Tech Issue']")
        );
        subjectTextMenuTech.click();

        WebElement textMenuArea = getDriver().findElement(
                By.xpath("//div[@class= 'col-sm-8']//textarea")
        );
        textMenuArea.click(); textMenuArea.sendKeys(message);

        WebElement submitButton = getDriver().findElement(
                By.xpath("//input[@class='btn btn-default']")
        );
        submitButton.click();

        String actualResulrmessageCapcha = getDriver().findElement(
                By.xpath("//div[@class='help-block']")
        ).getText();

        Assert.assertEquals(actualResulrmessageCapcha,expectedResultAnswerMessage);
    }
}
