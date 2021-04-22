package strategies;

import java.util.List;

import pt.tooyummytogo.Utilizador;
import pt.tooyummytogo.catComerciantes;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class PesquisaPorRaio implements PesquisarEstrategias{

	@Override
	public List<ComercianteInfo> buscarComericantes(catComerciantes comerciantes, Utilizador u) {
		return comerciantes.getComerciantes(u.getLocalizacao(), u.getRaio());
	}

}
