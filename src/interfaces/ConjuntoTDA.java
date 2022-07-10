package interfaces;

public interface ConjuntoTDA {
	
	//No posee
	public void InicializarConjunto ();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public boolean ConjuntoVacio() ;
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void Agregar(String x);
	
	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public String Elegir();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void Sacar(String x);
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public boolean Pertenece(String x);

}
