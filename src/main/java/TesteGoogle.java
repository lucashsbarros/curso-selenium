import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;
import org.junit.Test;
//import org.openqa.selenium.chrome.ChromeDriver;



public class TesteGoogle {
	private WebDriver driver;

	@Before
	public void inicializa(){
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));

	}
	@After
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void teste() {
		//System.setProperty("webdriver.gecko.driver", "D:\\Selenium Drivers\\geckodriver.exe");
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle()); // verifica se o titulo do navegador � GOOGLE.

	}
}
