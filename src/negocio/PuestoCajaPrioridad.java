package negocio;

import interfaces.ColaPrioridadTDA;

public class PuestoCajaPrioridad implements ColaPrioridadTDA {
	private String servicio;
	private int[] elemento;
	private int[] demoraParcial;
	private int[] prioridad;
	private int demoraTotal;
	private int indice;

	public void setDemorasParciales() {
		int demoraParcial = 0;
		for (int i = 0; i < this.demoraParcial.length; i++) {
			this.demoraParcial[i] = demoraParcial;
			demoraParcial += prioridad[i];
		}
	}

	public void setDemoraTotal(int demora) {
		this.demoraTotal -= demora;
	}

	public int getPrioridades(int q) {
		return prioridad[q];
	}

	public boolean maximo() {
		return indice == elemento.length;
	}

	public int getIndice() {
		return elemento.length;
	}

	public int getDemoraTotal() {
		return demoraTotal;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	@Override
	public void InicializarCola() {
		indice = 0;
		elemento = new int[7];
		demoraParcial = new int[7];
		prioridad = new int[7];
		demoraTotal = 0;
	}

	@Override
	public void acolarPrioridad(int valor, int prioridad) {
		int j = indice;
		for (; j > 0 && this.prioridad[j - 1] >= prioridad; j--) {
			elemento[j] = elemento[j - 1];
			this.prioridad[j] = this.prioridad[j - 1];
		}
		elemento[j] = valor;
		this.prioridad[j] = prioridad;
		demoraTotal += prioridad;
		indice++;
	}

	@Override
	public void Desacolar() {
		indice--;
	}

	@Override
	public int Primero() {
		return elemento[indice - 1];
	}

	@Override
	public int Prioridad() {
		return elemento[indice - 1];
	}
}