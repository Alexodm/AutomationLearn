import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestNov01 {
    private WebDriver driver;

    private void clickButtonNext() {
        driver.findElement(By.xpath("//input[@id='btnNext']")).click();
    }

    private String multiplyNums(){
        WebElement elementToParse = driver.findElement(By.cssSelector("span.qtext"));
        String firstString = elementToParse.getText();
        String substring = firstString.substring(0,3);
        String[] split = substring.split("\\*",2);
        int result = Integer.parseInt(split[0])*Integer.parseInt(split[1]);
        return Integer.toString(result);
    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        driver = new ChromeDriver();
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

            driver.findElement(By.xpath("//input[@class='otp-textbox digit ']")).sendKeys(multiplyNums());
            String strCount = driver.findElement(By.xpath("//span[@class='num']")).getText();
            int resultCount = Integer.parseInt(strCount);
            Assert.assertEquals(resultCount, i);
            clickButtonNext();
            i++;

        }
        WebElement resultElement = driver.findElement(By.cssSelector("tr:nth-child(3) > td.ta-c:nth-child(2)"));
        Assert.assertEquals("10", resultElement.getText());
    }

    @After
    public void after() {
        driver.close();
    }

}


