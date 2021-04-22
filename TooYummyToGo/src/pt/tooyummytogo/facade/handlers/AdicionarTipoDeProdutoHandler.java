package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.Comerciante;
import pt.tooyummytogo.catTipoProduto;

public class AdicionarTipoDeProdutoHandler {

	private catTipoProduto tiposProduto;
	private Comerciante comercianteCorrente;

	public AdicionarTipoDeProdutoHandler(catTipoProduto tps, Comerciante comerciante) {
		this.tiposProduto = tps;
		this.comercianteCorrente = comerciante;
	}

	public void registaTipoDeProduto(String tp, double d) {
		tiposProduto.registarTipoProduto(tp, d);
		comercianteCorrente.registarTipoProduto(tp);
	}
}
