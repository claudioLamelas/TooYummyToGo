package Observables;

import pt.tooyummytogo.Comerciante;

import java.util.List;

public interface Subject {

	public void notificar();
	
	public void adicionarObservadores(List<Comerciante> comerciantes);
}
