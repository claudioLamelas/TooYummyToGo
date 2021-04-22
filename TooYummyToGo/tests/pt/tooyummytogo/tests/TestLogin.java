package pt.tooyummytogo.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

class TestLogin {

	TooYummyToGo ty2g = new TooYummyToGo();
	RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
	RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
	
	@Test
	void test1Utilizador() {
		regHandler.registarUtilizador("Armindo", "batata123");
		regHandler.registarUtilizador("Orangotango", "uhuhahah");
		assertEquals(ty2g.autenticar("joana", "batata123"), Optional.empty());
	}
	
	@Test
	void test2Utilizador() {
		regHandler.registarUtilizador("Armindo", "batata123");
		regHandler.registarUtilizador("Orangotango", "uhuhahah");
		assertFalse(ty2g.autenticar("Armindo", "batata123").equals(Optional.empty()));
	}

	@Test
	void test1Comerciante() {
		regComHandler.registarComerciante("Barrancho", "pao", new PosicaoCoordenadas(34.5, 45.2));
		assertEquals(ty2g.autenticar("joana", "batata123"), Optional.empty());
	}
	
	@Test
	void test2Comerciante() {
		regComHandler.registarComerciante("Barrancho", "pao", new PosicaoCoordenadas(34.5, 45.2));
		assertFalse(ty2g.autenticar("Barrancho", "pao").equals(Optional.empty()));
	}
	
	@Test
	void testHandlers1() {
		regHandler.registarUtilizador("Armindo", "batata123");
		Optional<Sessao> sessao = ty2g.autenticar("Armindo", "batata123");
		sessao.ifPresent((Sessao s) -> {
			assertEquals(s.adicionarTipoDeProdutoHandler(), null);
			}
		);
	}
	
	@Test
	void testHandlers2() {
		regHandler.registarUtilizador("Armindo", "batata123");
		Optional<Sessao> sessao = ty2g.autenticar("Armindo", "batata123");
		sessao.ifPresent((Sessao s) -> {
			assertEquals(s.getColocarProdutoHandler(), null);
			}
		);
	}
	
	@Test
	void testHandlers3() {
		regComHandler.registarComerciante("Barrancho", "pao", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> sessao = ty2g.autenticar("Barrancho", "pao");
		sessao.ifPresent((Sessao s) -> {
			assertEquals(s.getEncomendarComerciantesHandler(), null);
			}
		);
	}
	
	@Test
	void testHandlers4() {
		regHandler.registarUtilizador("Armindo", "batata123");
		Optional<Sessao> sessao = ty2g.autenticar("Armindo", "batata123");
		sessao.ifPresent((Sessao s) -> {
			assertTrue(s.getEncomendarComerciantesHandler() != null);
			}
		);
	}
	
	@Test
	void testHandlers5() {
		regComHandler.registarComerciante("Barrancho", "pao", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> sessao = ty2g.autenticar("Barrancho", "pao");
		sessao.ifPresent((Sessao s) -> {
			assertTrue(s.adicionarTipoDeProdutoHandler() != null);
			}
		);
	}
	
	@Test
	void testHandlers6() {
		regComHandler.registarComerciante("Barrancho", "pao", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> sessao = ty2g.autenticar("Barrancho", "pao");
		sessao.ifPresent((Sessao s) -> {
			assertTrue(s.getColocarProdutoHandler() != null);
			}
		);
	}
}