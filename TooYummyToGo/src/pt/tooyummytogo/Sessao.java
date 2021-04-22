package pt.tooyummytogo;

import java.util.Optional;

import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class Sessao {
	
	public Utilizador utilizadorCorrente;
	private Comerciante comercianteCorrente;
	private catComerciantes comerciantes;
	private catTipoProduto tipoProduto;
	
	public Sessao(Utilizador utilizador, catComerciantes comerciantes, catTipoProduto tp) {
		this.utilizadorCorrente = utilizador;
		this.comerciantes = comerciantes;
		this.tipoProduto = tp;
	}
	
	public Sessao(Comerciante comerciante, catComerciantes comerciantes, catTipoProduto tp) {
		this.comercianteCorrente = comerciante;
		this.comerciantes = comerciantes;
		this.tipoProduto = tp;
	}
	
	// UC4
	public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() {
		if (comercianteCorrente == null) {
			return null;
		}
		return new AdicionarTipoDeProdutoHandler(tipoProduto, comercianteCorrente);
	}

	// UC5
	public ColocarProdutoHandler getColocarProdutoHandler() {
		if (comercianteCorrente == null) {
			return null;
		}
		return new ColocarProdutoHandler(tipoProduto, comercianteCorrente);
	}

	public EncomendarHandler getEncomendarComerciantesHandler() {
		if (utilizadorCorrente == null) {
			return null;
		}
		return new EncomendarHandler(comerciantes, utilizadorCorrente);
	}
}
