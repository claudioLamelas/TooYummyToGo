package pt.tooyummytogo;

import java.util.List;
import java.util.Random;

import Observables.Subject;

public class Reserva{
	
	private List<Encomenda> listaEncomendas;
	private double preco;
	private String codigo;
	
	public Reserva(List<Encomenda> le, double p) {
		this.listaEncomendas = le;
		this.preco = p;
	}

	public double getPreco() {
		return preco;
	}

	public String gerarCodigo() {
		String[] caracteres = {"a","b","c","d","e","g","h","i","j","k","l","m","n","o","p","q",
				"r","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9","0"};
		String codigo = "";
		Random r = new Random();
		for(int i = 0; i < 6; i++) {
			int j = r.nextInt(caracteres.length);
			codigo = codigo + caracteres[j];
		}
		r.nextInt(caracteres.length);
		return codigo;
	}

	public void subtrairProduto() {
		for(Encomenda e : listaEncomendas) {
			e.subtrai();
		}
	}
}
