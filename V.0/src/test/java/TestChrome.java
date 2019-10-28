import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestChrome {
    private WebDriver driver;

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
    /*public void test01() {
        InputTest test = new InputTest();
        test.test01(driver);
        test.testRowNumber(driver);
        test.findTextOnPage(driver);
    }*/
    public void testMultiplication(){
        MuliplicationTest test = new MuliplicationTest();
        test.startTest(driver);
        test.startTest(driver);
    }


  /* @After
   public void after() {
   driver.close();
   }
*/

}


