import org.junit.Assert;
import org.openqa.selenium.*;

public class MuliplicationTest {

    void startTest(WebDriver driver) {

        driver.findElement(By.xpath("//input[@id='btnNext']")).click();
    }

    void negativeCase(WebDriver driver) {

        driver.findElement(By.xpath("//input[@id='btnNext']")).click();
        String alert = driver.findElement(By.xpath("//span[contains(text(),',')]")).getText();
        Assert.assertEquals(alert, "Ответьте, пожалуйста, на вопрос.");
    }

    void countTest(WebDriver driver) {

        int i = 1;

        while (i <= 10) {

            driver.findElement(By.xpath("//input[@class='otp-textbox digit ']")).sendKeys("10");

            String strCount = driver.findElement(By.xpath("//span[@class='num']")).getText();
            int resultCount = Integer.parseInt(strCount);
            Assert.assertEquals(resultCount, i);

            driver.findElement(By.xpath("//input[@id='btnNext']")).click();
            i++;

        }


    }

    void resultTest(WebDriver driver) {
        String alert = driver.findElement(By.xpath("//div[@class='title']//span")).getText();
        Assert.assertEquals(alert, "Результат");
    }
}
