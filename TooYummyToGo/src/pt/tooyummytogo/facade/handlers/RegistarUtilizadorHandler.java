package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.catUtilizadores;

public class RegistarUtilizadorHandler {
	
	catUtilizadores utilizadores;
	/**
	 * Regista um utilizador normal.
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username
	 */
	
	public RegistarUtilizadorHandler(catUtilizadores utilizadores) {
		this.utilizadores = utilizadores;
	}
	
	public void registarUtilizador(String username, String password) {
		utilizadores.registaUtilizador(username, password);
	}

}
