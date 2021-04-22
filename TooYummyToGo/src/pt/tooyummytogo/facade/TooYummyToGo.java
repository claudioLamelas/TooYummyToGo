package pt.tooyummytogo.facade;

import java.util.Optional;

import pt.tooyummytogo.Comerciante;
import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.Utilizador;
import pt.tooyummytogo.catComerciantes;
import pt.tooyummytogo.catTipoProduto;
import pt.tooyummytogo.catUtilizadores;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

/**
 * Esta é a classe do sistema.
 */
public class TooYummyToGo {

	public catUtilizadores utilizadores = new catUtilizadores();
	public catComerciantes comerciantes = new catComerciantes();
	public catTipoProduto tiposProduto = new catTipoProduto();

	// UC1
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(utilizadores);
	}
	
	/**
	 * Returns an optional Session representing the authenticated user.
	 * @param username
	 * @param password
	 * @return
	 * 
	 * UC2
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		Optional<Utilizador> contaU = utilizadores.tentaAutenticar(username, password);
		Optional<Comerciante> contaC = comerciantes.tentaAutenticar(username, password);
		
		if(contaU.isPresent()) {
			return contaU.map(u -> new Sessao(u, comerciantes, tiposProduto));
		}else if(contaC.isPresent()){
			return contaC.map(c -> new Sessao(c, comerciantes, tiposProduto));
		}else {
			return Optional.empty();
		}
	}

	// UC3
	public RegistarComercianteHandler getRegistarComercianteHandler() {
		return new RegistarComercianteHandler(comerciantes);
	}
	

}
