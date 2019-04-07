import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UIB04 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\laitpham\\Type Driver\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://the-internet.herokuapp.com";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUploadFile() throws Exception {

        //Select 'File Upload' link"
        driver.findElement(By.linkText("File Upload")).click();
        System.out.println("Clicked on file upload link");
        assertTrue("File Upload title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"File Uploader\")]")).isDisplayed());

        // "Click on 'Choose File' button and upload a png file 'Ex: c:\abc.png'
        //Click on 'Upload' button"
        String imagePath = "C:\\Users\\laitpham\\Desktop\\Document\\";
        String imageName = "firefox.png";
        WebElement uploadImage = driver.findElement(By.id("file-upload"));
        uploadImage.sendKeys(imagePath + imageName);
        System.out.println("Uploaded file");
        driver.findElement(By.id("file-submit")).click();
        System.out.println("Submit upload file");

        //The png file is upload successful
        checkUploadFileSuccessfull(imageName);
        System.out.println("Upload file success");

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    /**
     * Check file exist after upload file
     * @param expectedImage: name of file that you want to verify
     */
    public void checkUploadFileSuccessfull(String expectedImage){
        assertTrue("File Upload title is displayed",driver.findElement(By.xpath("//div[@class = \"example\"]//h3[contains(text(),\"File Uploaded!\")]")).isDisplayed());
        String actualImage = driver.findElement(By.id("uploaded-files")).getText();
        assertEquals(actualImage,expectedImage);

    }
}
