package pt.tooyummytogo.main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

public class ClienteExemplo {
	public static void main(String[] args) {
		TooYummyToGo ty2g = new TooYummyToGo();
		
		// UC1
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Felismina", "hortadafcul");
		
		// UC3
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
		
		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		regComHandler.registarComerciante("Maribel", "torredotombo", new PosicaoCoordenadas(33.5, 45.2));
		
		// UC4
		Optional<Sessao> talvezSessao = ty2g.autenticar("Silvino", "bardoc2");
		talvezSessao.ifPresent( (Sessao s) -> {
			AdicionarTipoDeProdutoHandler atp = s.adicionarTipoDeProdutoHandler();
			Random r = new Random();
			for (String tp : new String[] {"Pão", "Pão de Ló", "Mil-folhas"}) {
				atp.registaTipoDeProduto(tp, r.nextDouble()*10);
			}
		});

		// UC5
		Optional<Sessao> talvezSessao2 = ty2g.autenticar("Silvino", "bardoc2");
		talvezSessao2.ifPresent( (Sessao s) -> {
			ColocarProdutoHandler cpv = s.getColocarProdutoHandler();
			
			List<String> listaTiposDeProdutos = cpv.inicioDeProdutosHoje();
			
			cpv.indicaProduto(listaTiposDeProdutos.get(0), 10); // Pão
			cpv.indicaProduto(listaTiposDeProdutos.get(2), 5); // Mil-folhas
			
			cpv.indicaProduto("arroz", 420);
			
			cpv.confirma(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
			
			System.out.println("Produtos disponíveis");
		});
		
		// UC6 + UC7
		Optional<Sessao> talvezSessao3 = ty2g.autenticar("Felismina", "hortadafcul");
		talvezSessao3.ifPresent( (Sessao s) -> {
			EncomendarHandler lch = s.getEncomendarComerciantesHandler();
			List<ComercianteInfo> cs = lch.indicaLocalizacaoActual(new PosicaoCoordenadas(34.5, 45.2));
			
			for (ComercianteInfo i : cs) {
				System.out.println(i);
			}
			
			boolean redefineRaio = true;
			if (redefineRaio) {
				cs = lch.redefineRaio(100);
				for (ComercianteInfo i : cs) {
					System.out.println(i);
				}
			}
			
			boolean redefinePeriodo = false;
			if (redefinePeriodo) {
				cs = lch.redefinePeriodo(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
				for (ComercianteInfo i : cs) {
					System.out.println(i);
				}
			}
			
			
			// A partir de agora é UC7
			List<ProdutoInfo> ps = lch.escolheComerciante(cs.get(0));
			for (ProdutoInfo p : ps) {
				lch.indicaProduto(p, 1); // Um de cada
			}
			String codigoReserva = lch.indicaPagamento("365782312312", "02/2021", "766");
			System.out.println("Reserva " + codigoReserva + " feita com sucesso");
		});
	}
}
