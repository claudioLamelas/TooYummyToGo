package cartao.plugins;

public interface CartaoPlugin {
	
	public boolean isValid(String num, String ccv, String mes, String ano);
	
	public boolean pagar(double preco);
}
