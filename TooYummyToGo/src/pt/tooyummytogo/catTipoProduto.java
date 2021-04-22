package pt.tooyummytogo;

import java.util.HashMap;

public class catTipoProduto {
	
	private HashMap<String, TipoProduto> mapaTiposProduto;
	
	public catTipoProduto() {
		mapaTiposProduto = new HashMap<String, TipoProduto>();
	}
	
	public void registarTipoProduto(String tp, double d) {
		mapaTiposProduto.put(tp, new TipoProduto(tp, d));
	}
	
	public TipoProduto getTp(String s) {
		return mapaTiposProduto.get(s);
	}
}
