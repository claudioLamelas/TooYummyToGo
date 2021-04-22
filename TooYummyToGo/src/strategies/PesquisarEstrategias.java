package strategies;

import java.util.List;

import pt.tooyummytogo.Utilizador;
import pt.tooyummytogo.catComerciantes;
import pt.tooyummytogo.facade.dto.ComercianteInfo;

public interface PesquisarEstrategias {
	
	public List<ComercianteInfo> buscarComericantes(catComerciantes comerciantes, Utilizador u);
}
