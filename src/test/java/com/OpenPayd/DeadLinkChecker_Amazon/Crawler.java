package com.OpenPayd.DeadLinkChecker_Amazon;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.OpenPayd.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Crawler {

    private static WebDriver driver;


    public static void main(String[] args) throws InterruptedException, AWTException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");

        Thread.sleep(3000);

        List < WebElement > links = driver.findElements(By.tagName("a"));

        String txtFile = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        //combine the stamp and file name-path

        String filePath = System.getProperty("user.dir") +"\\target\\_results"+txtFile ;
        System.out.println(filePath+" is created!");


        for (int i = 0; i < links.size(); i++) {
            WebElement ele = links.get(i);
            String url = ele.getAttribute("href");
            verifyLinkActive(url, filePath);
        }


        Dropbox_upload(filePath);

    }

    public static void verifyLinkActive(String linkUrl, String file) {


        try {
            URL url = new URL(linkUrl);

            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

            httpURLConnect.setConnectTimeout(3000);

            httpURLConnect.connect();

            if (httpURLConnect.getResponseCode() == 200) {

                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
                String output = linkUrl + " - " + httpURLConnect.getResponseMessage();
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter br = new BufferedWriter(writer);
                br.write("\r\n"); // write new line
                br.write(output);
                br.close();


            }
            if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
                String output = linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND;
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter br = new BufferedWriter(writer);
                br.write("\r\n"); // write new line
                br.write(output);
                br.close();
            }
        } catch (Exception e) {

        }


    }


    public static void Dropbox_upload(String path_filename) throws InterruptedException, AWTException {

        driver.get("https://www.dropbox.com/en_GB/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);


        driver.findElement(By.name("login_email")).sendKeys("osman.sirin@openpayd.com");
        Thread.sleep(4000);
        driver.findElement(By.name("login_password")).sendKeys("Password1!");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//div[@class='signin-text']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[@class='ue-effect-container uee-AppActionsView-SecondaryActionMenu-text-upload-file']")).click();
        Thread.sleep(3000);


        StringSelection ss = new StringSelection(path_filename);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(3000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(5000);



    }



}