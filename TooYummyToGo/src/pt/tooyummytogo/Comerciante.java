package pt.tooyummytogo;

import java.util.List;

import Observables.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Comerciante implements Observer{
	
	private String nomeNegócio;
	private String id;
	private String pw;
	private int telemovel;
	private String email;
	private PosicaoCoordenadas local;
	private List<String> tpsVendidos;
	private List<Produto> listaProdutos;

	public Comerciante(String username, String password, PosicaoCoordenadas p) {
		this.id = username;
		this.pw = password;
		this.local = p;
		this.tpsVendidos = new ArrayList<String>();
		this.listaProdutos = new ArrayList<Produto>();
	}
	
	public boolean temPassword(String pw) {
		return this.pw.equals(pw);
	}
	
	public void registarTipoProduto(String tp) {
		
		boolean jaExiste = false;
		for(int i = 0; i < tpsVendidos.size(); i++) {
			jaExiste = jaExiste || tpsVendidos.get(i).equals(tp);
		}
		if(!jaExiste) {
			tpsVendidos.add(tp);
		}
	}
	
	public List<String> getListaTiposProduto(){
		return this.tpsVendidos;
	}
	
	public String getId() {
		return this.id;
	}
	
	public List<Produto> getListaProdutos(){
		return this.listaProdutos;
	}
	
	public void associaProduto(Produto p) {
		listaProdutos.add(p);
	}

	public boolean preencheCondicoes(PosicaoCoordenadas coordinate, int i) {
		
		boolean temPDisponivel = false;
		for(Produto p : listaProdutos) {
			temPDisponivel = temPDisponivel || p.estaDisponivel();
		}
		return this.local.distanciaEmMetros(coordinate) <= i && temPDisponivel;
	}

	public boolean preencheCondicoesTempo(LocalDateTime now, LocalDateTime plusHours) {
		
		boolean temPDisponivel = false;
		for(Produto p : listaProdutos) {
			temPDisponivel = temPDisponivel || p.estaDisponivel(now, plusHours);
		}
		return temPDisponivel;
	}

	@Override
	public void update(List<Encomenda> listEncomendas) {
		for(Encomenda e : listEncomendas) {
			for(Produto p : listaProdutos) {
				if(p == e.getProduto()) {
					System.out.println(getId() + " recebeu uma encomenda de " +
							e.getQuantidade() + " " + p.getCodigo());
				}
			}
		}
	}
}
