package ejecucion;

import interfaces.DiccionarioSimpleTDA;
import negocio.AdministradorDeColas;
import utils.Diccionario;

public class EjecutarBanco {
	public static void main(String[] args) {
		AdministradorDeColas system = new AdministradorDeColas();
		system.Inicializar(6);
		DiccionarioSimpleTDA prioridades = new Diccionario();
		prioridades.InicializarDiccionario();

		system.Acolar("C", 12);
		system.Acolar("P", 7);
		system.Acolar("J", 11);
		system.Acolar("PF", 4);
		system.Acolar("CH", 4);
		system.Acolar("CG", 4);

		system.CantidadDeColas();
		system.Primero();
		system.Elementos();
		system.TiempoEstimado();
		system.Desacolar();
	}
}
