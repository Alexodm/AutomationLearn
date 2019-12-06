package UITests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class TestFirefox {
    private WebDriver driver;

    private void clickButtonNext() {
        driver.findElement(By.xpath("//input[@id='btnNext']")).click();
    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void before() {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("https://onlinetestpad.com/ru/test/68962-tablica-umnozheniya");
    }

    @Test
    public void test01_countTest() {


        int i = 1;

        clickButtonNext();

        while (i <= 10) {

            driver.findElement(By.xpath("//input[@class='otp-textbox digit ']")).sendKeys("10");

            String strCount = driver.findElement(By.xpath("//span[@class='num']")).getText();
            int resultCount = Integer.parseInt(strCount);
            Assert.assertEquals(resultCount, i);
            clickButtonNext();
            i++;

        }
    }

    @Test
    public void test02_negativeCase() {

        clickButtonNext();
        clickButtonNext();
        String alert = driver.findElement(By.xpath("//span[contains(text(),',')]")).getText();
        Assert.assertEquals(alert, "Ответьте, пожалуйста, на вопрос.");
    }

    @Test
    public void test03_resultTest() {
        test01_countTest();
        String alert = driver.findElement(By.xpath("//div[@class='title']//span")).getText();
        Assert.assertEquals(alert, "Результат");
    }

    @After
    public void after() {
        driver.close();
    }

}

