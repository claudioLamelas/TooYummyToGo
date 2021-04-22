package pt.tooyummytogo;

import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class catComerciantes{

	HashMap<String, Comerciante> mapaComerciantes;
	
	public catComerciantes() {
		mapaComerciantes = new HashMap<String, Comerciante>();
	}

	public void registarComerciante(String username, String password, PosicaoCoordenadas p) {
		if(!mapaComerciantes.containsKey(username)) {
			mapaComerciantes.put(username, new Comerciante(username, password, p));
		}else {
			System.out.println("Username already taken");
		}
	}

	public Optional<Comerciante> tentaAutenticar(String username, String pw){
		Optional<Comerciante> autenticado = Optional.ofNullable(mapaComerciantes.get(username));
		if(!autenticado.isEmpty() && mapaComerciantes.get(username).temPassword(pw)) {
			return autenticado;
		}else {
			return Optional.empty();
		}
	}
	
	public List<ComercianteInfo> getComerciantes(PosicaoCoordenadas coordinate, int raio){
		List<ComercianteInfo> lcd = new ArrayList<ComercianteInfo>();
		for(Comerciante c : mapaComerciantes.values()) {
			if(c.preencheCondicoes(coordinate, raio)) {
				ComercianteInfo newC = new ComercianteInfo(c);
				lcd.add(newC);
			}
		}
		return lcd;
	}

	public List<ComercianteInfo> getComerciantes(PosicaoCoordenadas localizacao, int i, LocalDateTime now,
			LocalDateTime plusHours) {
		List<ComercianteInfo> lcd = new ArrayList<ComercianteInfo>();
		for(Comerciante c : mapaComerciantes.values()) {
			if(c.preencheCondicoesTempo(now, plusHours) && c.preencheCondicoes(localizacao, i)) {
				ComercianteInfo newC = new ComercianteInfo(c);
				lcd.add(newC);
			}
		}
		return lcd;
	}
	
	public List<Comerciante> getAllComerciantes(){
		List<Comerciante> lc = new ArrayList<>(mapaComerciantes.values());
		return lc;
	}
}