import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
public class Lamda {
	WebDriver driver;
    @BeforeSuite
    public void setUp()
    {
    	System.out.println("Set up system property for chrome");
    	System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");	
    }
    @BeforeTest
	public void login()
	{
		System.out.println("Login");
	}
	@BeforeClass
	public void launchBrowser()
	{
		System.out.println("Launch chrome browser");
		driver=new ChromeDriver();
	}
	@BeforeMethod
	public void launchUrl()
	{
		System.out.println("Enter Url");
		driver.get("https://www.lambdatest.com/selenium-playground");
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void testclick()
	{
		System.out.println("click on the link");
		WebElement link=driver.findElement(By.linkText("Simple Form Demo"));
		link.click();
		String url=driver.getCurrentUrl();
		if(url.contains("simple-form-demo"))
		{
			System.out.println("Url is validated");
		}
		else
		{
			System.out.println("Url is not validated");	
		}
		String title="Welcome to LambdaTest";
		WebElement inputmessage=driver.findElement(By.id("user-message"));
		inputmessage.sendKeys(title);
		WebElement getcheckedvalue=driver.findElement(By.xpath("//*[@id=\"showInput\"]"));
		getcheckedvalue.click();
		WebElement textdisplayed=driver.findElement(By.id("message"));
		String Textmessage=textdisplayed.getText();
		Assert.assertEquals(Textmessage, title, "Message is not validated");
		/*if(Textmessage.equals(title))
		{
			System.out.println("Message is validated");
		}
		else
		{
			System.out.println("Message is not validated");
		}*/		
	}
	@Test(priority=2)
	public void testprogressbar()
	{
		System.out.println("Go to Progress bar Url");
	    driver.get("https://www.lambdatest.com/selenium-playground");
		WebElement progressbar=driver.findElement(By.linkText("Drag & Drop Sliders"));
		progressbar.click();
		WebElement barprogress15=driver.findElement(By.xpath("//*[@id=\"slider3\"]/div/input"));
		Actions moveSlider = new Actions(driver);
		Action action = moveSlider.dragAndDropBy(barprogress15, 120, 0).build();
		action.perform();
		WebElement newprogress=driver.findElement(By.xpath("//*[@id=\"rangeSuccess\"]"));
		String progressvalue=newprogress.getText();
		Assert.assertEquals("95", progressvalue);
	}
	@Test(priority=3)
	public void inputform()
	{
		System.out.println("Go to Input Form Submit");
		driver.get("https://www.lambdatest.com/selenium-playground/input-form-demo");
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/section[3]/div/div/div[1]/div[1]/ul/li[5]/a")).click();
		WebElement ele=driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[6]/button"));
		ele.click();
		String message=driver.findElement(By.xpath("//*[@id=\"name\"]")).getAttribute("validationMessage");
	    String popup="Please fill out this field.";
		System.out.println(message);
	    Assert.assertEquals(message, popup,"Not validated");
	    //Fill out all details
	    WebElement Name=driver.findElement(By.xpath("//*[@id=\"name\"]"));
	    Name.sendKeys("Khushi");
	    WebElement Email=driver.findElement(By.xpath("//*[@id=\"inputEmail4\"]"));
	    Email.sendKeys("Khushi@gmail.com");
	    WebElement Password=driver.findElement(By.xpath("//*[@id=\"inputPassword4\"]"));
	    Password.sendKeys("Khushi@1234");
	    WebElement Company=driver.findElement(By.xpath("//*[@id=\"company\"]"));
	    Company.sendKeys("Guji&Guji.co");
	    WebElement Website=driver.findElement(By.xpath("//*[@id=\"websitename\"]"));
	    Website.sendKeys("Aniket.co.in");
	    Select drpCountry = new Select(driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[3]/div[1]/select")));
		drpCountry.selectByVisibleText("United States");
	    WebElement City=driver.findElement(By.xpath("//*[@id=\"inputCity\"]"));
	    City.sendKeys("Pune");
	    WebElement Address=driver.findElement(By.xpath("//*[@id=\"inputAddress1\"]"));
	    Address.sendKeys("Magarpatta");
	    WebElement Address2=driver.findElement(By.xpath("//*[@id=\"inputAddress2\"]"));
	    Address2.sendKeys("Paratwada");
	    WebElement State=driver.findElement(By.xpath("//*[@id=\"inputState\"]"));
	    State.sendKeys("Maharashtra");
	    WebElement Zipcode=driver.findElement(By.xpath("//*[@id=\"inputZip\"]"));
	    Zipcode.sendKeys("411028");
	    driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[6]/button")).click();
	    System.out.println();
	    String finalmsg=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/section[3]/div/div/div[2]/div/p")).getText();    
	    String finaltext="Thanks for contacting us, we will get back to you shortly.";
	    Assert.assertEquals(finalmsg, finaltext,"Not validated");
	}
	@AfterSuite
    public void Tearall()
    {
    	System.out.println("quit chrome");
    	driver.close();
    }
}
