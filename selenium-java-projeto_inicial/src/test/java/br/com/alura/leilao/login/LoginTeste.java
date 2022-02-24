package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTeste {

	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		String url = "http://localhost:8080/login";
		browser.navigate().to(url);
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();
		
		//verifica se o login teve sucesso a partir da mudança de endereço
		Assert.assertFalse(browser.getCurrentUrl().equals(url));
		
		//verifica se o login teve sucesso a partir do nome logado
		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-Logado")).getText());
		
	}
}
