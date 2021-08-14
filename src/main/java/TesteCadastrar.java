import java.util.List;

import org.junit.Assert;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastrar {
	@Test
	public void DevePreencherCadastro(){
		
		// criando cenário
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		// Preenchando campo Nome
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");			
		Assert.assertEquals("Lucas",driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		// Preenchendo campo Sobrenome
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Barros");
		Assert.assertEquals("Barros",driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		//Preenchendo campo Sexo
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		//Preenchendo campo Comida Favorita
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		//Preenchendo campo Escolaridade 
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByVisibleText("2o grau completo");
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
				// Verifica se o campo Escolaridade realmente tem 8 elementos e se um deles está dentro da lista
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		boolean encontrou = false;
		for(WebElement option:options) {
			if(option.getText().equals("2o grau completo")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		// Preenchendo o campo Esportes
		WebElement element1 = driver.findElement(By.id("elementosForm:esportes"));
		Select combo1 = new Select(element1);
		combo1.selectByVisibleText("Natacao");
		// Verifica se a quantidade de Esportes selecionado está correto
		List<WebElement> allSelectedOptions = combo1.getAllSelectedOptions();
		Assert.assertEquals(1, allSelectedOptions.size());		
		//Confirma cadastro		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Lucas"));
		Assert.assertEquals("Sobrenome: Barros", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: 2graucomp", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao", driver.findElement(By.id("descEsportes")).getText());
		
		driver.quit();
		
	}

	@Test
	public void deveValidarNomeObrigatorio() {
		
		// criando cenário
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		
		driver.quit();
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		
		// criando cenário
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		
		driver.quit();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		
		// criando cenário
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Barros");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		//Thread.sleep(5000);
		driver.quit();
	}
	@Test
	public void deveValidarComidaVegetariana(){
		
		// criando cenário
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Barros");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		
		driver.quit();

	}
	
	@Test
	public void deveValidarEsportes() {
		
		// criando cenário
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Barros");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");				
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		
		//driver.quit();


	}
	
	
	
	
	
}