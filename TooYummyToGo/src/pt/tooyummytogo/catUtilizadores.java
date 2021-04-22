package pt.tooyummytogo;
import java.util.HashMap;
import java.util.Optional;

public class catUtilizadores{
	
	public HashMap<String,Utilizador> mapaUtilizadores;
	
	public catUtilizadores() {
		mapaUtilizadores = new HashMap<String,Utilizador>();
	}
	
	public void registaUtilizador(String username, String pw) {
		if(!mapaUtilizadores.containsKey(username)) {
			mapaUtilizadores.put(username, new Utilizador(username, pw));
		}else {
			System.out.println("Username already taken");
		}
	}

	public Optional<Utilizador> tentaAutenticar(String username, String pw){
		Optional<Utilizador> autenticado = Optional.ofNullable(mapaUtilizadores.get(username));
		if(!autenticado.isEmpty() && mapaUtilizadores.get(username).temPassword(pw)) {
			return autenticado;
		}else {
			return Optional.empty();
		}
	}
}