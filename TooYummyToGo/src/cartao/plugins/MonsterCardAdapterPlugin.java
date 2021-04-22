package cartao.plugins;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

public class MonsterCardAdapterPlugin implements CartaoPlugin{
	
	private Card c;

	@Override
	public boolean isValid(String num, String ccv, String mes, String ano) {
		Card c = new Card(num, ccv, mes, ano);
		MonsterCardAPI mp = new MonsterCardAPI();
		boolean valido = mp.isValid(c);
		if(valido) {
			this.c = c;
		}
		return valido;
	}

	@Override
	public boolean pagar(double preco) {
		MonsterCardAPI mc = new MonsterCardAPI();
		boolean temDinheiro = mc.block(c, preco);
		if(temDinheiro) {
			mc.charge(c, preco);
		}
		return temDinheiro;
	}
		
	
}
