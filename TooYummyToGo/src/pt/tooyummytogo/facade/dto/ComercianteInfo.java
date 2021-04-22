package pt.tooyummytogo.facade.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.Comerciante;
import pt.tooyummytogo.Produto;

public class ComercianteInfo {
	
	private String id;
	private List<Produto> listaProdutos;
	
	public ComercianteInfo(Comerciante c) {
		this.id = c.getId();
		this.listaProdutos = c.getListaProdutos();
	}
	
	public String toString(){
		return id;
	}

	public boolean corresponde(ComercianteInfo comercianteInfo) {
		return this.id == comercianteInfo.id;
	}

	public List<ProdutoInfo> getProdutos(LocalDateTime inicio, LocalDateTime fim) {
		List<ProdutoInfo> lpd = new ArrayList<>();
		for(Produto p : listaProdutos) {
			if(p.estaDisponivel(inicio, fim)) {
				ProdutoInfo newP = new ProdutoInfo(p);
				lpd.add(newP);
			}
		}
		return lpd;
	}
}
