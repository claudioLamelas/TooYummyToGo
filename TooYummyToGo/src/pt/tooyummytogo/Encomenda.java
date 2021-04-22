package pt.tooyummytogo;

import pt.tooyummytogo.facade.dto.ProdutoInfo;

public class Encomenda {
	private int quantidade;
	private ProdutoInfo produto;
	
	public Encomenda(ProdutoInfo p, int quantidade) {
		this.produto = p;
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public double getPreco() {
		return this.quantidade * produto.getPreco();
	}
	
	public void subtrai() {
		produto.subtrai(this.quantidade);
	}

	public Produto getProduto() {
		return produto.getProduto();
	}
}
