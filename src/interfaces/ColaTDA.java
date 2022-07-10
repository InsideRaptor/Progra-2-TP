package interfaces;

public interface ColaTDA {

	//No posee
	public void InicializarCola () ;
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void Acolar(int Identificador,int demora);
	
	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public void Desacolar() ;
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public boolean ColaVacia ();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public int Primero();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public int PrimerDemora();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public boolean maximo();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public int longitud();

}
