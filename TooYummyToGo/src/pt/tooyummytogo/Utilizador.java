package pt.tooyummytogo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cartao.plugins.CartaoFactory;
import cartao.plugins.CartaoPlugin;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Utilizador {

	private String username;
	private String pw;
	private String email;
	private int telemovel;
	private LocalDateTime disponibilidadeInicial;
	private LocalDateTime disponibilidadeFinal;
	private PosicaoCoordenadas local;
	private int raioDeProcura;
	private List<CartaoPlugin> listaCartao;
	private List<Reserva> listaReserva;
	
	public Utilizador(String username,String pw) {
		this.username = username;
		this.pw = pw;
		listaCartao = new ArrayList<>();
		listaReserva = new ArrayList<>();
	}
	
	public boolean temPassword(String pw) {
		return this.pw.equals(pw);
	}
	
	public void setLocalizacao(PosicaoCoordenadas local) {
		this.local = local;
	}
	
	public void setRaio(int raio) {
		this.raioDeProcura = raio*1000;
	}
	
	public int getRaio() {
		return this.raioDeProcura;
	}
	
	public PosicaoCoordenadas getLocalizacao() {
		return local;
	}
	
	public void setDisponibilidade(LocalDateTime now, LocalDateTime after) {
		this.disponibilidadeInicial = now;
		this.disponibilidadeFinal = after;
	}

	public LocalDateTime getDisponibilidadeInicio() {
		return this.disponibilidadeInicial;
	}

	public LocalDateTime getDisponibilidadeFim() {
		return this.disponibilidadeFinal;
	}

	public CartaoPlugin indicaPagamento(String string, String string2, String string3) {
		String mes = string2.split("/")[0];
		String ano = string2.split("/")[1];
		boolean existe = false;
		CartaoPlugin c = CartaoFactory.criaCartao();
		for(CartaoPlugin cc : listaCartao) {
			if(cc.equals(c)) {
				existe = true;
				return cc;
			}
		}
		if(!existe) {
			if(c.isValid(string, string3, mes, ano)) {
				listaCartao.add(c);
				return c;
			}
		}
		return null;
	}

	public String pagar(Reserva r, CartaoPlugin c) {
		double preco = r.getPreco();
		boolean pago = c.pagar(preco);
		String codigo = null;
		if(pago) {
			codigo = r.gerarCodigo();
			listaReserva.add(r);
		}
		return codigo;
	}
}
