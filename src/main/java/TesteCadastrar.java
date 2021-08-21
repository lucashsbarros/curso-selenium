import java.util.List;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastrar {
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa(){
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	@After
	public void finaliza(){
		driver.quit();
	}


	@Test
	public void DevePreencherCadastro(){
		dsl.escreve("elementosForm:nome","Lucas");
		dsl.escreve("elementosForm:sobrenome","Barros");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");


		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Lucas"));
		Assert.assertEquals("Sobrenome: Barros", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: 2graucomp", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

	}

	@Test
	public void deveValidarNomeObrigatorio() {
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		

	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());

	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Barros");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		//Thread.sleep(5000);

	}
	@Test
	public void deveValidarComidaVegetariana(){
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Lucas");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Barros");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
	}
	
	@Test
	public void deveValidarEsportes() {
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
		
	}
}