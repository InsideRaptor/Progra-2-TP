package utils;

import interfaces.ConjuntoTDA;

public class Conjunto implements ConjuntoTDA {
	private String [] array;
	private int cant ;

	@Override
	public void InicializarConjunto() {
		array = new String [7];
		cant = 0;
	}

	@Override
	public void Agregar(String x) {
		if (!this.Pertenece(x)) {
			array[cant] = x;
			cant ++;
		}
	}

	@Override
	public boolean Pertenece(String x) {
		int i = 0;
		while (i<cant && array[i]!= x)
			i ++;
		return ( i < cant );
	}
}
