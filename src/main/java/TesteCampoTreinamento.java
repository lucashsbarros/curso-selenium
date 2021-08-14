import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;




public class TesteCampoTreinamento {
	@Test
	public void testeTextField() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		
		// a linha abaixo faz voce acessar os arquivos que estão na sua maquina, sendo assim, só irá funcionar na sua maquina.
		//driver.get("file:///C:/Users/Lucas/Documents/campo_treinamento/componentes.html");	
				
		
		// para criarmos um projeto "movel" podendo abrir em qualquer maquina devemos pegar os arquivos que precisamos e copiar na pasta
		// "src/main/resoucers do nosso projeto e executar o comando abaixo.
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		//driver.quit();
	}
	
	@Test
	
	public void deveInteragirComTextArea(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		driver.quit();
	}
	@Test
	public void deveInteragirComRadioButton(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click(); // comando que seleciona o Radio (o box escolhendo masculino ou feminino) 
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected()); // comando para verificar se o RADIO está tickado.
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCkeckBox(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click(); // comando que seleciona o CKECKBOX (o box escolhendo masculino ou feminino) 
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected()); // comando para verificar se o CKECKBOX está tickado.
		
		driver.quit();
	}
	
	@Test
	public void deveInteragirComCombo() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
//		combo.selectByIndex(2);  // seleciona por lugar no campo.
//		combo.selectByValue("superior"); // seleciona por VALOR da variavel
		combo.selectByVisibleText("2o grau completo"); // **recomendado** seleciona pelo que ta escrito na pagina, visivel para usuário final
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		
		driver.quit();
		
	}
	@Test
	public void deveVerificarValoresCombo(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		
	}
	
	@Test
	public void deveVerificarValoresCombMultiplo(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		// SELECIONA VARIAS OPÇÕES
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions(); // CRIA UMA LISTA DE ELEMENTOS APOS O COMANDO "combo.getAllSelectedOptions();" clicar em CTRL+1 pra criar corretamente
		Assert.assertEquals(3, allSelectedOptions.size());
		
	
		// DESELECIONA UMA OPÇÃO 
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
				
		driver.quit();
		
	}
	
	@Test
	public void deveInteragirComBotoes() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		WebElement botao = driver.findElement(By.id("buttonSimple")); // cria uma variavel chamada botao
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
		driver.quit();
		
	}
	@Test
	// @Ignore - esse comando faz o JUnit ignorar esse teste
	public void deveInteragirComLinks() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.linkText("Voltar")).click();
		//Assert.fail(); O teste não esta completo, por isso usamos esse comando para mostrar que é um falso sucesso
		//completando o teste
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
					
		driver.quit();
				
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		// comando a baixo mostra todas as escritas da pagina e o comando COUNTAINS verificar se a frase "CAMPO DE TREINAMENTO" contem na página 
		// Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.className("facilAchar")).getText());
		
		driver.quit();
		
	}
}
