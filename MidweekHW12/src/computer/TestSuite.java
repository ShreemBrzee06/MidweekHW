package computer;

import homepage.TopMenuTest;
import homepage.Utility;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void openingBrowser() {
        openBrowser(baseUrl);
    }

    @Test
    public void computerTest() {
        //1.1 Click on Computer Menu.
        mouseHooverMethod(By.xpath("//ul[@class='top-menu notmobile']//li[1]//a[@href=\"/computers\"]"));
        //1.2 Click on Desktop
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//li[1]//a[@href=\"/desktops\"]"));
        //1.3 Select Sort By position "Name: Z to A"
        WebElement sort = driver.findElement(By.id("products-orderby"));
        // sort.click();
        Select select = new Select(sort);
        // select.selectByValue("6");
        select.selectByVisibleText("Name: Z to A");

        //1.4 Verify the Product will arrange in Descending order.
        driver.navigate().refresh();
        List<WebElement> descendingOrder = driver.findElements(By.xpath("//h2"));
        List<String> sortDescendingOrders = new ArrayList<>();
        for (WebElement sortOrder : descendingOrder) {
            System.out.println(sortOrder.getText());
            //System.out.println(sortDescendingOrders.add(sortOrder.getText()));
        }


    }
     public void billingInformation(){
        driver.findElement(By.cssSelector("#BillingNewAddress_FirstName")).sendKeys("Ganesha");
        driver.findElement(By.cssSelector("#BillingNewAddress_LastName")).sendKeys("Shiv");
        driver.findElement(By.cssSelector("#BillingNewAddress_Email")).sendKeys("ganesha@gmail.com");
        WebElement option= driver.findElement(By.cssSelector("#BillingNewAddress_CountryId"));
        Select opt= new Select(option);
        opt.selectByVisibleText("United Kingdom");
        WebElement state= driver.findElement(By.cssSelector("select#BillingNewAddress_StateProvinceId"));
        Select se =new Select(state);
        se.selectByVisibleText("Other");
        driver.findElement(By.cssSelector("input#BillingNewAddress_City")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#BillingNewAddress_Address1")).sendKeys("Test Address1");
        driver.findElement(By.cssSelector("input#BillingNewAddress_ZipPostalCode")).sendKeys("Test");
        driver.findElement(By.cssSelector("input#BillingNewAddress_PhoneNumber")).sendKeys("123456789");
        driver.findElement(By.cssSelector("button[onclick='Billing.save()']")).click();


     }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //2.1 Click on Computer Menu.
        mouseHooverMethod(By.xpath("//ul[@class='top-menu notmobile']//li[1]//a[@href=\"/computers\"]"));
        //2.2 Click on Desktop
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//li[1]//a[@href=\"/desktops\"]"));
        //2.3 Select Sort By position "Name: A to Z"
        WebElement sort1 = driver.findElement(By.id("products-orderby"));
        Select select = new Select(sort1);
        select.selectByVisibleText("Name: A to Z");

        //2.4 Click on "Add To Cart"
        driver.navigate().refresh();
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[1]")).click();
        //2.5 Verify the Text "Build your own computer"
        String actualMsgProductName = driver.findElement(By.xpath("//div[@class='product-name']//h1")).getText();
        System.out.println(actualMsgProductName);

        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7.Select "8GB [+$60.00]" using Select class.
        WebElement option = driver.findElement(By.id("product_attribute_2"));
        Select s = new Select(option);
        s.selectByVisibleText("8GB [+$60.00]");

        //2.8 Select HDD radio "400 GB [+$100.00]"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
        WebElement hdd = driver.findElement(By.cssSelector("li:nth-of-type(2)>input[name=\"product_attribute_3\"]+label"));
        hdd.click();

        //2.9 Select OS radio "Vista Premium [+$60.00]"
        WebElement OS = driver.findElement(By.cssSelector("li>#product_attribute_4_9"));
        OS.click();

        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
        //[+$5.00]"
        WebElement twoCheckBox = driver.findElement(By.xpath("//dl//dd[5]//ul[@class='option-list']//li[3]//input[1]"));
        twoCheckBox.click();

        //2.11 Verify the price "$1,475.00"
        String verifyThePrice = driver.findElement(By.id("price-value-1")).getText();
        System.out.println(verifyThePrice);
        String expectedVerifyThePriceMsg = "$1,475.00";
        // Assert.assertEquals("Verify the price",expectedVerifyThePriceMsg,verifyThePrice);

        //2.12 Click on "ADD TO CARD" Button.
        //driver.navigate().refresh();
        driver.findElement(By.cssSelector("div.add-to-cart-panel>#add-to-cart-button-1")).click();

        //2.13 Verify the Message "The product has been added to your shopping cart" on Top
        //green Bar
        //After that close the bar clicking on the cross button.
        String actualNotificationMsg = driver.findElement(By.cssSelector("#bar-notification>div>p")).getText();
        System.out.println(actualNotificationMsg);
        String expectedNotificationMsg = "The product has been added to your shopping cart";
        Assert.assertEquals("verify the message", expectedNotificationMsg, actualNotificationMsg);
        driver.findElement(By.cssSelector("#bar-notification>div>p+span")).click();

        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHooverMethod(By.cssSelector("a.ico-cart"));
        driver.findElement(By.cssSelector("div.mini-shopping-cart>div:nth-of-type(4)>button")).click();

        //2.15 Verify the message "Shopping cart"
        String shoppingCartMSG = driver.findElement(By.cssSelector("div.page-title>h1")).getText();
        System.out.println(shoppingCartMSG);
        String expectedShoppingCartMsg = "Shopping cart";
        Assert.assertEquals("Verify Shopping cart msg", expectedShoppingCartMsg, shoppingCartMSG);

        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        driver.findElement(By.cssSelector("tr>td:nth-of-type(5)>label+input"));
        clearTextFromField(By.cssSelector("tr>td:nth-of-type(5)>label+input"));
        driver.findElement(By.cssSelector("tr>td:nth-of-type(5)>label+input")).sendKeys("2");
        driver.findElement(By.cssSelector("div>#updatecart")).click();

        //2.17 Verify the Total"$2,950.00"
        String verifyTotalPrice= driver.findElement(By.cssSelector("tr>td:nth-of-type(6)>label+span")).getText();
        System.out.println(verifyTotalPrice);
        String expectedVerifyTotalPrice="$2,950.00";
        Assert.assertEquals("Verify the total price",expectedVerifyTotalPrice,verifyTotalPrice);

        //2.18 click on checkbox “I agree with the terms of service”
        driver.findElement(By.cssSelector("div>#termsofservice")).click();

        //2.19 Click on “CHECKOUT”
        driver.findElement(By.cssSelector("div>#checkout")).click();

        //2.20 Verify the Text “Welcome, Please Sign In!”
        String welcomeSignIn = driver.findElement(By.cssSelector("div.page-title>h1")).getText();
        System.out.println(welcomeSignIn);
        String expectedWelcomeSignInMsg = "Welcome, Please Sign In!";
        Assert.assertEquals("verify the welcome signin msg",expectedWelcomeSignInMsg,welcomeSignIn);

        //2.21Click on “CHECKOUT AS GUEST” Tab
        driver.findElement(By.cssSelector(".checkout-as-guest-button")).click();

        //2.22 Fill the all mandatory field
        billingInformation();

        //2.24 Click on Radio Button “Next Day Air($0.00)”
        driver.findElement(By.cssSelector("#shippingoption_1")).click();

        //2.25 Click on “CONTINUE”
        driver.findElement(By.cssSelector("button[onclick='ShippingMethod.save()']")).click();

        //2.26 Select Radio Button “Credit Card”
        driver.findElement(By.cssSelector("#paymentmethod_1")).click();

        // click on Continue
        driver.findElement(By.cssSelector("button[onclick='PaymentMethod.save()']")).click();

        //2.27 Select “Master card” From Select credit card dropdown
       WebElement dropDownCreditType = driver.findElement(By.cssSelector("select.dropdownlists"));
       dropDownCreditType.click();
       Select dropDown = new Select(dropDownCreditType);
       dropDown.selectByVisibleText("Master card");

       // 2.28 Fill all the details
        cardInfo();

        //2.29 Click on “CONTINUE”
        driver.findElement(By.cssSelector("button[onclick='PaymentInfo.save()']")).click();

        //2.30 Verify “Payment Method” is “Credit Card”
        String actualPaymentMsg = getTextFromElement(By.cssSelector("ul>li.payment-method>span:last-of-type"));
        String expectedPaymentMsg="Credit Card";
        Assert.assertEquals("Verify Payment Msg",expectedPaymentMsg,actualPaymentMsg);

        //2.32 Verify “Shipping Method” is “Next Day Air”
        String shippingMethod = driver.findElement(By.cssSelector("ul>li.shipping-method>span:last-of-type")).getText();
        String expectedShippingMethod = "Next Day Air";
        Assert.assertEquals("Verify the shipping msg",expectedShippingMethod,shippingMethod);

        //2.33 Verify Total is “$2,950.00”
        String total =getTextFromElement(By.cssSelector("tr>td.subtotal>span"));
        String expectedTotal = "$2,950.00";
        Assert.assertEquals("verify Total amt msg",expectedTotal,total);

        //2.34 Click on “CONFIRM”
        driver.findElement(By.cssSelector("button[onclick='ConfirmOrder.save()']")).click();

        //2.35 Verify the Text “Thank You”
        String thankYouMSG =getTextFromElement(By.cssSelector("div.page-title>h1"));
        String expectedThankYouMsg = "Thank you";
        //Assert.assertEquals("Verify Thank you msg",expectedThankYouMsg,thankYouMSG);

        //2.36 Verify the message “Your order has been successfully processed!”
        String successfulMsg = getTextFromElement(By.cssSelector("div.section.order-completed>div>strong"));
        String expectedSuccessfulMSG = "Your order has been successfully processed!";
        Assert.assertEquals("Verify successfulMsg",expectedSuccessfulMSG,successfulMsg);

        //2.37 Click on “CONTINUE”
        clickOnElement(By.cssSelector("button[onclick='setLocation(\"/\")']"));

        //2.37 Verify the text “Welcome to our store”
        String WelcomeStoreMSG = getTextFromElement(By.cssSelector("div.topic-block-title>h2"));
        String expectedWelcomeStoreMsg = "Welcome to our store";
        Assert.assertEquals("Verify the Welcome Store message",expectedWelcomeStoreMsg,WelcomeStoreMSG);
    }

    public void cardInfo(){
        driver.findElement(By.cssSelector("#CardholderName")).sendKeys("Ganesha Shiv");
        driver.findElement(By.cssSelector("#CardNumber")).sendKeys("1254 2555 55");
        WebElement expireMonth = driver.findElement(By.cssSelector("#ExpireMonth"));
        Select month = new Select(expireMonth);
        month.selectByVisibleText("11");

        WebElement expireYear = driver.findElement(By.cssSelector("#ExpireYear"));
        Select year = new Select(expireYear);
        year.selectByVisibleText("2027");

        driver.findElement(By.cssSelector("#CardCode")).sendKeys("555");
    }

    @After
    public void tearDown() {
        // closeDownBrowser();
    }
}
