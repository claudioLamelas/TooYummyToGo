package pt.tooyummytogo;

import java.util.ArrayList;
import java.util.List;

public class TipoProduto {
	
	private String nome;
	private String ingredientes;
	private List<Produto> listaProdutos;
	private double preco;
	
	public TipoProduto(String nome, double preco) {
		this.nome = nome;
		this.listaProdutos = new ArrayList<Produto>();
		this.preco = preco;
	}
	
	public String getCodigo() {
		return this.nome;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public void criarProduto(Produto p) {
		listaProdutos.add(p);
	}
}
