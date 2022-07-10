package negocio;

import interfaces.ColaTDA;

public class PuestoCaja implements ColaTDA {
	private String servicio;
	private int[] valor;
	private int[] demora;
	private int[] demoraParcial;
	private int demoraTotal;
	private int cantidad;

	public String getServicio() {
		return servicio;
	}

	public boolean maximo() {
		return cantidad == demora.length;
	}

	public int longitud() {
		return valor.length;
	}

	public int elemento(int posicion) {
		return valor[posicion];
	}

	public int getDemoraTotal() {
		return demoraTotal;
	}
	
	public void setDemoraTotal(int demora) {
		this.demoraTotal -= demora;
	}
	
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	public int demoraParcial(int j) {
		return demoraParcial[j];
	}

	@Override
	public void InicializarCola() {
		valor = new int[7];
		demora = new int[7];
		demoraParcial = new int[7];
		cantidad = 0;
		demoraTotal = 0;	
	}

	@Override
	public void Acolar(int id, int demora) {
		valor[cantidad] = id;
		this.demora[cantidad] = demora;
		demoraParcial[cantidad] += demoraTotal;
		demoraTotal += demora;
		cantidad ++;
	}

	@Override
	public void Desacolar() {
		for(int i = 0; i < cantidad - 1; i++)
			valor[i] = valor[i + 1];
		cantidad --;
	}

	@Override
	public boolean ColaVacia() {
		return cantidad == 0;
	}

	@Override
	public int Primero() {
		return valor[0];
	}

	@Override
	public int PrimerDemora() {
		return  demora[0];
	}
}
