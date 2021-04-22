package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

import Observables.Observer;
import Observables.Subject;
import cartao.plugins.CartaoPlugin;
import pt.tooyummytogo.Comerciante;
import pt.tooyummytogo.Encomenda;
import pt.tooyummytogo.Reserva;
import pt.tooyummytogo.Utilizador;
import pt.tooyummytogo.catComerciantes;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import strategies.PesquisaPorLocalizacao;
import strategies.PesquisaPorPeriodo;
import strategies.PesquisaPorRaio;
import strategies.PesquisarEstrategias;

public class EncomendarHandler implements Subject{
	
	private catComerciantes comerciantes;
	private Utilizador utilizadorCorrente;
	private List<ComercianteInfo> lcd;
	private List<Observer> observadores;
	private List<ProdutoInfo> lpd;
	private List<Encomenda> listaEncomendas;
	private ComercianteInfo comercianteSelecionado;
	private PesquisarEstrategias st;
	
	public EncomendarHandler(catComerciantes comerciantes, Utilizador utilizadorCorrente) {
		this.comerciantes = comerciantes;
		this.utilizadorCorrente = utilizadorCorrente;
		lcd = new ArrayList<>();
		lpd = new ArrayList<>();
		listaEncomendas = new ArrayList<>();
		observadores = new ArrayList<>();
	}

	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinate){
		st = new PesquisaPorLocalizacao();
		utilizadorCorrente.setLocalizacao(coordinate);
		utilizadorCorrente.setDisponibilidade(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		this.lcd = st.buscarComericantes(comerciantes, utilizadorCorrente);
		return lcd;
	}

	public List<ComercianteInfo> redefineRaio(int i) {
		st = new PesquisaPorRaio();
		utilizadorCorrente.setRaio(i);
		this.lcd = st.buscarComericantes(comerciantes, utilizadorCorrente);
		if(lcd.size() == 0) {
			System.out.println("Não existem comerciantes no raio definido");
		}
		return lcd;
	}

	public List<ComercianteInfo> redefinePeriodo(LocalDateTime now, LocalDateTime plusHours) {
		st = new PesquisaPorPeriodo();
		utilizadorCorrente.setDisponibilidade(now, plusHours);
		this.lcd = st.buscarComericantes(comerciantes, utilizadorCorrente);
		return lcd;
	}
	
	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		for(ComercianteInfo ci : lcd) {
			if(ci.corresponde(comercianteInfo))
				comercianteSelecionado = ci;
		}
		
		LocalDateTime inicio = utilizadorCorrente.getDisponibilidadeInicio();
		LocalDateTime fim = utilizadorCorrente.getDisponibilidadeFim();
		
		lpd = comercianteSelecionado.getProdutos(inicio, fim);
		return lpd;
	}

	public void indicaProduto(ProdutoInfo p, int i) {
		for(ProdutoInfo pi : lpd) {
			if(pi.corresponde(p)) {
				if(pi.existe(i)) {
					Encomenda e = new Encomenda(p, i);
					listaEncomendas.add(e);
					pi.adicionaEncomenda(e);
				}else {
					System.out.println("O comerciante só tem " + pi.getQuantidade() + " " + p.getNome());
				}
			}
		}
	}

	public String indicaPagamento(String string, String string2, String string3) {
		CartaoPlugin c = utilizadorCorrente.indicaPagamento(string, string2, string3);
		double preco = 0;
		if(c != null) {
			for(Encomenda e : listaEncomendas) {
				preco += e.getPreco();
			}
			Reserva r = new Reserva(listaEncomendas, preco);
			String cod = utilizadorCorrente.pagar(r,c);
			if(cod != null) {
				adicionarObservadores(comerciantes.getAllComerciantes());
				notificar();
				return cod;
			}
			return "Erro: O pagamento não foi efetuado";
		}
		return "Erro: O pagamento não foi efetuado";
	}
	

	@Override
	public void notificar() {
		for(Observer o : observadores) {
			o.update(this.listaEncomendas);
		}
	}


	@Override
	public void adicionarObservadores(List<Comerciante> comerciantes) {
		for(Observer o : comerciantes) {
			observadores.add(o);
		}
	}

}
