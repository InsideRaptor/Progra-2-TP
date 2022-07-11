package interfaces;

public interface ConjuntoTDA {
	
	//No posee
	public void InicializarConjunto ();

	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void Agregar(String x);

	//La estructura debe estar inicializada antes de efectuar esta operacion
	public boolean Pertenece(String x);

}
