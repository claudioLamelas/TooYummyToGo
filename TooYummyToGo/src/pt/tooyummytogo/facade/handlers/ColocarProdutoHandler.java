package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import pt.tooyummytogo.Comerciante;
import pt.tooyummytogo.Produto;
import pt.tooyummytogo.TipoProduto;
import pt.tooyummytogo.catTipoProduto;

public class ColocarProdutoHandler {
	
	private catTipoProduto tps; 
	private Comerciante comerciante;
	private List<String> tpsVendidos;
	private List<Produto> produtosCriados;
	
	public ColocarProdutoHandler(catTipoProduto tps, Comerciante comerciante) {
		this.tps = tps;
		this.comerciante = comerciante;
		this.produtosCriados = new ArrayList<Produto>();
	}

	public List<String> inicioDeProdutosHoje() {
		this.tpsVendidos = comerciante.getListaTiposProduto();
		return tpsVendidos;
	}

	public void indicaProduto(String string, int i) {
		boolean existe = false;
		for(String s : tpsVendidos) {
			existe = existe || s.equals(string);
		}
		if(existe) {
			TipoProduto tpCorrente = tps.getTp(string);
			Produto p = new Produto(i, tpCorrente);
			tpCorrente.criarProduto(p);
			produtosCriados.add(p);
		}else {
			Random r = new Random();
			tps.registarTipoProduto(string, r.nextDouble()*10);
			comerciante.registarTipoProduto(string);
		}
	}

	public void confirma(LocalDateTime now, LocalDateTime plusHours) {
		for(Produto p : produtosCriados) {
			p.definirHorario(now, plusHours);
			comerciante.associaProduto(p);
		}
	}
}
