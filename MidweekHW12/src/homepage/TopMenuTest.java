package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TopMenuTest extends Utility {

     String baseUrl="https://demo.nopcommerce.com/";

    public void chooseMenu(String menu) {
       driver.findElement(By.xpath("//ul[@class='top-menu notmobile']"));// to go on title of array list
        if (menu.equalsIgnoreCase("Computers")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[1]//a[@href=\"/computers\"]]"));
            System.out.println("I m in computer loop");
        } else if (menu.equalsIgnoreCase("Electronics")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[2]//a[@href=\"/electronics\"]"));
        } else if (menu.equalsIgnoreCase("Apparel")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[3]//a[@href=\"/apparel\"]"));
        } else if (menu.equalsIgnoreCase("Digital downloads")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[4]//a[@href=\"/digital-downloads\"]"));
        } else if (menu.equalsIgnoreCase("Books")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[5]//a[@href=\"/books\"]"));
        } else if (menu.equalsIgnoreCase("Jewelry")) {
            driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[6]//a[@href=\"/jewelry\"]"));
        } else if (menu.equalsIgnoreCase("Gift Cards")) {
           driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//li[7]//a[@href=\"/gift-cards\"]"));
        }
    }

    @Before
    public void setUP(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyPageNavigation(){
       // chooseMenu("Computers");
        chooseMenu("Gift Cards");
    }

    @After

    public void tearDown(){
        closeDownBrowser();
    }

}