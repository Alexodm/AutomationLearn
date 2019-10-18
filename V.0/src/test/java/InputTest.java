import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

import java.util.List;


class InputTest {

    void test01(WebDriver driver) {

        System.out.println("Page name is " + driver.getTitle());

        WebElement element = driver.findElement(By.name("q"));

        element.sendKeys("CSS vs XPATH locators");

        element.submit();
    }

    void testRowNumber(WebDriver driver) {
        List<WebElement> rows = driver.findElements(By.className("r"));
        System.out.println("Number of rows: " + rows.size());
        assertArrayEquals(new int[]{11}, new int[]{rows.size()});
        WebElement element = rows.get(0);
        System.out.println("Name of first row object: " + element);
        element.click();
    }

    void findTextOnPage(WebDriver driver) {
        Boolean textExists = driver.getPageSource().contains("Locating Element Using XPATH Selectors");
        System.out.println("Text exists on page = " + textExists);
        assertEquals(true, textExists);
    }

}
