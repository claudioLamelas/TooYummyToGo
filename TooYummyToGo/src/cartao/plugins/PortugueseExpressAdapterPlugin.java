package cartao.plugins;

import pt.portugueseexpress.InvalidCardException;
import pt.portugueseexpress.PortugueseExpress;

public class PortugueseExpressAdapterPlugin implements CartaoPlugin{

	private PortugueseExpress api;
	@Override
	public boolean isValid(String num, String ccv, String mes, String ano) {
		PortugueseExpress api = new PortugueseExpress();
		api.setNumber(num);
		api.setCcv(Integer.parseInt(ccv));
		api.setMonth(Integer.parseInt(mes));
		api.setYear(Integer.parseInt(ano));
		boolean valido = api.validate();
		if(valido) {
			this.api = api;
		}
		return valido;
	}

	@Override
	public boolean pagar(double preco){
		try {
			api.block(preco);
			api.charge(preco);
		}catch(InvalidCardException e) {
			return false;
		}
		return true;
	}

}
