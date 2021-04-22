package Observables;

import pt.tooyummytogo.Encomenda;
import java.util.List;

public interface Observer {

	public void update(List<Encomenda> listEncomendas);
}
