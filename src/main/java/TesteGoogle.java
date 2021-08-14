import org.openqa.selenium.Dimension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;
import org.junit.Test;
//import org.openqa.selenium.chrome.ChromeDriver;



public class TesteGoogle {
	
	@Test
	public void teste() {
		System.setProperty("webdriver.gecko.driver", "D:\\Selenium Drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (1200, 765));; // cria o browser no tamanho selecionado
		driver.get("http://www.google.com");		
		Assert.assertEquals("Google", driver.getTitle()); // verifica se o titulo do navegador é GOOGLE.
		driver.quit(); // fecha o browser e mata os processos que estavam nele.
	}
}
