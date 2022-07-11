package interfaces;

public interface DiccionarioSimpleTDA {
	
	//No posee
	public void InicializarDiccionario () ;
	
	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void Agregar( String clave , int valor);

	//La estructura debe estar inicializada antes de efectuar esta operacion
	public void Eliminar(String clave);

	//La estructura debe estar inicializada antes de efectuar esta operacion y la clave debe existir
	public int Recuperar(String clave);

}
