import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class QascaTest {
    private WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void before() {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("https://qasca-554.reallysimplesystems.com/");
        loginToQasca();
    }

    @Test
    public void testLoginIsCorrect() {
        String loginText = driver.findElement(By.xpath("//div[@class='username']")).getText();
        Assert.assertEquals("OLEG KUTC", loginText);
    }

    @Test
    public void checkAccountsCount() {
        goToAccountsPage();
        List accounts = driver.findElements(By.xpath("//tr[@id]"));
        Assert.assertEquals(8, accounts.size());
    }

    @Test
    public void checkSearchModule() {
        String existingAccount = "Richmond Talent";
        String nonExistingAccount = "This is fake account";

        Assert.assertEquals("1 matching record", searchModule(existingAccount));
        Assert.assertEquals("0 matching records", searchModule(nonExistingAccount));

    }


    @After
    public void after() {
        driver.close();
    }

    private void loginToQasca() {
        driver.findElement(By.cssSelector("#email")).sendKeys("oleg.kutc@gmail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("Pass#12345");
        driver.findElement(By.cssSelector("#login_submit")).click();
    }

    private void goToAccountsPage() {
        driver.findElement(By.xpath("//a[@class='menu-item menu-item-2']")).click();
    }


    private String searchModule(String Account) {
        String accountResult;
        driver.findElement(By.cssSelector("#search")).sendKeys(Account);
        driver.findElement(By.xpath("//a[@class='show_all']")).click();
        accountResult = driver.findElement(By.xpath("//div[@class='container-fluid']//tr[1]//td[2]")).getText();
        return accountResult;
    }

}

