package pt.tooyummytogo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Produto {

	private int quantidade;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private TipoProduto tipoProduto;
	private List<Encomenda> listaEncomendas;
	
	public Produto(int i, TipoProduto tipoProduto) {
		this.quantidade = i;
		this.tipoProduto = tipoProduto;
		this.listaEncomendas = new ArrayList<>();
	}

	public void definirHorario(LocalDateTime now, LocalDateTime plusHours) {
		this.dataInicio = now;
		this.dataFim = plusHours;
	}

	public boolean estaDisponivel() {
		return dataFim.isAfter(LocalDateTime.now().plusHours(1));
	}
	
	public boolean estaDisponivel(LocalDateTime now, LocalDateTime after) {
		return this.dataInicio.isBefore(now) || this.dataFim.isAfter(after);
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public TipoProduto getTipoProduto() {
		return this.tipoProduto;
	}
	
	public String getCodigo() {
		return this.tipoProduto.getCodigo();
	}

	public List<Encomenda> getListaEncomendas() {
		return listaEncomendas;
	}
	
	public int encomendado() {
		int encomendado = 0;
		for(Encomenda e : listaEncomendas) {
			encomendado += e.getQuantidade();
		}
		return encomendado;
	}

	public void adicionaEncomenda(Encomenda e) {
		listaEncomendas.add(e);
	}

	public void subtrai(int quantidade2) {
		this.quantidade -= quantidade2;
	}
}
