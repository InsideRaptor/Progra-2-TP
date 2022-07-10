package ejecucion;

import interfaces.DiccionarioSimpleTDA;
import negocio.AdministradorDeColas;
import utils.Diccionario;

public class EjecutarBanco {
	public static void main(String[] args) {
		AdministradorDeColas admin = new AdministradorDeColas();
		admin.Inicializar(6);
		
		DiccionarioSimpleTDA serviciosFilas = new Diccionario();
		serviciosFilas.InicializarDiccionario();

		admin.Acolar("C", 10);
		admin.Acolar("P", 10);
		admin.Acolar("J", 10);
		admin.Acolar("PF", 4);	
		admin.Acolar("CH", 4);	
		admin.Acolar("CG", 4);
		
		admin.Desacolar();		
		admin.Programacion("J");
		admin.Programacion("General 2");
		admin.CantidadDeColas();	
		admin.Primero();	
		admin.Elementos();
		admin.TiempoEstimado();	
		admin.PuestoDelProximoElemento();
		admin.PuestoDelElemento(2);
	}
}
