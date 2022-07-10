package interfaces;

public interface AdministradorDeColasTDA {
	
	//No posee
	public void Inicializar(int n);
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void Acolar(String nombre, int demora);

	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public void Desacolar();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public Object Programacion(String Servicio);
	
	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public int CantidadDeColas();
	
	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public int Primero();

	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public int TiempoEstimado();

	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public String PuestoDelProximoElemento();

	//La estructura debe estar inicializada antes de efectuar esta operacion y no vacia
	public int PuestoDelElemento(int id);
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void Elementos();
}
