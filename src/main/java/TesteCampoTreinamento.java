import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;




public class TesteCampoTreinamento {
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
	public void testeTextField() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension (800, 765));
		
		// a linha abaixo faz voce acessar os arquivos que estao na sua maquina, sendo assim, s� ir� funcionar na sua maquina.
		//driver.get("file:///C:/Users/Lucas/Documents/campo_treinamento/componentes.html");	
				
		
		// para criarmos um projeto "movel" podendo abrir em qualquer maquina devemos pegar os arquivos que precisamos e copiar na pasta
		// "src/main/resoucers do nosso projeto e executar o comando abaixo.
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl.escreve("elementosForm:nome","Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));

	}
	
	@Test
	
	public void deveInteragirComTextArea(){
		dsl.escreve("elementosForm:sugestoes","teste");

		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));

	}
	@Test
	public void deveInteragirComRadioButton(){
		dsl.clicarRadio("elementosForm:comidaFavorita:2"); // comando que seleciona o Radio (o box escolhendo masculino ou feminino)
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0")); // comando para verificar se o RADIO est� tickado.

	}
	
	@Test
	public void deveInteragirComCkeckBox(){
		dsl.clicarRadio("elementosForm:comidaFavorita:2"); // comando que seleciona o CKECKBOX (o box escolhendo masculino ou feminino)
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2")); // comando para verificar se o CKECKBOX est� tickado.

	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade","2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	@Test
	public void deveVerificarValoresCombo(){
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
		dsl.selecionarCombo("elementosForm:esportes","Natacao");
		dsl.selecionarCombo("elementosForm:esportes","Corrida");
		dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions(); // CRIA UMA LISTA DE ELEMENTOS APOS O COMANDO "combo.getAllSelectedOptions();" clicar em CTRL+1 pra criar corretamente
		Assert.assertEquals(3, allSelectedOptions.size());

		// DESELECIONA UMA OP��O
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		WebElement botao = driver.findElement(By.id("buttonSimple")); // cria uma variavel chamada botao
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

	}
	@Test
	// @Ignore - esse comando faz o JUnit ignorar esse teste
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		//Assert.fail(); O teste n�o esta completo, por isso usamos esse comando para mostrar que � um falso sucesso
		//completando o teste
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));

	}
	
	@Test
	public void deveBuscarTextosNaPagina() {

		// comando a baixo mostra todas as escritas da pagina e o comando COUNTAINS verificar se a frase "CAMPO DE TREINAMENTO" contem na p�gina 
		// Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));

	}
}
