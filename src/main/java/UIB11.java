import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB11 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception {
//        System.setProperty("webdriver.gecko.driver","C:\\Users\\laitpham\\Type Driver\\geckodriver-v0.24.0-win64\\geckodriver.exe");
//////        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://the-internet.herokuapp.com";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testTable() throws Exception {

        //Select 'Sortable Data Tables' link
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        System.out.println("Click on Sortable dât tables link");
        assertTrue("Data Tables title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"Data Tables\")]")).isDisplayed());

        //Verify header at column 3 on Table 1
        WebElement emailField= driver.findElement(By.xpath("(//table[@id = \"table1\"]//tr//th)[3]//span"));
        System.out.println("Verify header at column 3 on table 1 is: "+emailField.getText());
        assertEquals("Email",emailField.getText());

        //Verify cell value (row 3, column 2) on Table 1
        WebElement cell3 = driver.findElement(By.xpath("(//table[@id = \"table1\"]//tbody//tr[3]//td)[2]"));
        System.out.println("Value at row3,column2: "+cell3.getText());
        assertEquals("Jason",cell3.getText());

        //Verify cell value (row 2, column 4) on Table 1
        WebElement cell4 = driver.findElement(By.xpath("(//table[@id = \"table1\"]//tbody//tr[2]//td)[4]"));
        System.out.println("Value at row 2, column 4 is: "+cell4);
        assertEquals("$51.00",cell4.getText());

        //Click on 'Email' header column on Table 2
        driver.findElement(By.xpath("(//table[@id = \"table2\"]//tr//th)[3]")).click();
        System.out.println("Click on Email field at table 2");

        //Verify The Email column sort by alphabetical from A-Z
        List<WebElement> elementList= driver.findElements(By.xpath("//table[@id = \"table2\"]//tbody//tr//td[3]"));
        System.out.println("Get all emails in table 2");
        checkSortData(elementList);

    }
    @After
    public void tearDown() throws Exception {
//        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    /**
     * Get data then sort data and compare verify that data is sort by alphabetical from A-Z
     * @param elementList: get list data which need to verify
     */
    public void checkSortData(List<WebElement> elementList){
        ArrayList<String> temptList = new ArrayList<>();
        for(WebElement we:elementList){
            // System.out.println(we.getText());
            temptList.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for(String s:temptList){
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        System.out.println("Original data: "+sortedList);
        Assert.assertTrue(sortedList.equals(temptList));

    }


}
