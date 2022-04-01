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

	private LoginPage paginaDeLogin;

	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();
	}

	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		paginaDeLogin.preencheFormularioDeLogin("Fulano", "pass");
		paginaDeLogin.efetuarLogin();

		String nomeUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
		
		Assert.assertEquals("fulano", nomeUsuarioLogado);
		Assert.assertTrue(paginaDeLogin.isPaginaAtual());
	}

	@Test
	public void naoDeveriaLogarComDadosInvalidos() {
		paginaDeLogin.preencheFormularioDeLogin("invalido", "123");
		paginaDeLogin.efetuarLogin();
		
		Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
		Assert.assertTrue(paginaDeLogin.isPaginaAtual());
		Assert.assertTrue(paginaDeLogin.isMensagemDeLoginInvalidoVisivel());
	}

	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		paginaDeLogin.navegaParaPaginaDeLances();

		Assert.assertFalse(paginaDeLogin.isPaginaAtual());
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}
}