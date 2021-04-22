package pt.tooyummytogo.facade.dto;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.Produto;
import pt.tooyummytogo.TipoProduto;
import pt.tooyummytogo.Encomenda;

public class ProdutoInfo{

	private int quantidade;
	private TipoProduto tipoProduto;
	private Produto p;
	
	public ProdutoInfo(Produto p) {
		this.p = p;
		this.quantidade = p.getQuantidade();
		this.tipoProduto = p.getTipoProduto();
	}

	public boolean corresponde(ProdutoInfo p) {
		return this.tipoProduto.equals(p.tipoProduto);
	}

	public boolean existe(int i) {
		return this.quantidade >= (i + p.encomendado());
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}

	public void adicionaEncomenda(Encomenda e) {
		p.adicionaEncomenda(e);
	}

	public double getPreco() {
		return tipoProduto.getPreco();
	}

	public String getNome() {
		return tipoProduto.getCodigo();
	}

	public void subtrai(int quantidade2) {
		this.quantidade -= quantidade2;
		p.subtrai(quantidade2);
	}

	public Produto getProduto() {
		return this.p;
	}

}
