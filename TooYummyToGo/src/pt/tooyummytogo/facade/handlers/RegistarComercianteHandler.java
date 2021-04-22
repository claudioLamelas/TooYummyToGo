package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.catComerciantes;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class RegistarComercianteHandler {
	
	catComerciantes comerciantes;
	/**
	 * Regista um Comerciante.
	 * @param Username
	 * @param Password
	 * @ensures existe um comerciante com esse username
	 */
	
	public RegistarComercianteHandler(catComerciantes comerciantes) {
		this.comerciantes = comerciantes;
	}
	
	public void registarComerciante(String username, String password, PosicaoCoordenadas p) {
		comerciantes.registarComerciante(username, password, p);
	}

}
