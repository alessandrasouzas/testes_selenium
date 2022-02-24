package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTeste {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	private static final String URL_LOGIN_ERROR = "http://localhost:8080/login?error";
	
	private WebDriver browser;
	
	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");	
	}
	
	@BeforeEach
	public void beforeEach() {		
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}
	
	@AfterEach
	public void afterEach() {
		this.browser.quit();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {				
		
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();
		
		//verifica se o login teve sucesso a partir da mudança de endereço
		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
		
		//verifica se o login teve sucesso a partir do nome logado
		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-Logado")).getText());		
	}
	
	@Test
	public void naoDeveriaLogarComDadosInvalidos() {

		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("123");
		browser.findElement(By.id("login-form")).submit();
		
		
		//verifica se o login teve erro a partir da mudança de endereço
		Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN_ERROR));
		
		//verifica se o login teve sucesso a partir do nome logado
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));	
		
		//verifica se é lançada uma exceção
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-Logado")));	

	}
}