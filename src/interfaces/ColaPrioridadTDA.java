package interfaces;

public interface ColaPrioridadTDA {
	
	//No posee
	public void InicializarCola () ;
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void acolarPrioridad (int identificadorCliente,int prioridad);
	
	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public void Desacolar() ;
	
	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public int Primero();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public boolean ColaVacia ();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public int Prioridad();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public boolean maximo();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public int elemento(int i);
}


