package utils;

import interfaces.ConjuntoTDA;
import interfaces.DiccionarioSimpleTDA;

public class Diccionario implements DiccionarioSimpleTDA {
	private class NodoClave {
		String clave;
		int valor;
		NodoClave sigClave;
	}
	NodoClave origen;

	@Override
	public void InicializarDiccionario() {
		origen = null;
	}
	
	@Override
	public void Agregar(String clave , int valor) {
		NodoClave nc = Clave2NodoClave ( clave);
		if (nc == null ) {
			nc = new NodoClave() ;
			nc.clave = clave;
			nc.sigClave = origen;
			origen = nc;
		}
		nc.valor = valor;
	}

	private NodoClave Clave2NodoClave(String clave) {
		NodoClave aux = origen;
		while ( aux != null && aux.clave!= clave) {
			aux = aux.sigClave;
		}
		return aux ;
	}
	
	@Override
	public void Eliminar( String clave) {
		if ( origen!= null ) {
			if (origen.clave == clave) {
				origen = origen. sigClave;
			}
			else {
				NodoClave aux = origen;
				while (aux . sigClave != null && aux.sigClave.clave	!= clave) {
					aux = aux.sigClave;
				}
				if ( aux.sigClave!= null ) {
					aux . sigClave= aux . sigClave. sigClave;
				}
			}
		}
	}
	
	@Override
	public int Recuperar(String clave) {
		NodoClave n = Clave2NodoClave( clave) ;
		return n.valor;
	}
	
	@Override
	public ConjuntoTDA Claves() {
		ConjuntoTDA c = new Conjunto();		
		c.InicializarConjunto () ;
		NodoClave aux = origen;
		while (aux!=  null ){
			c. Agregar( aux . clave);
			aux = aux.sigClave;
		}
		return c;
	}
}