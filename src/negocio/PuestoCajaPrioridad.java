package negocio;

import interfaces.ColaPrioridadTDA;

public class PuestoCajaPrioridad implements ColaPrioridadTDA {
	private String servicio;
	private int[] elementos;
	private int[] demorasParciales;
	private int[] prioridades;
	private int demoraTotal;
	private int indice;
	
	public void setDemorasParciales() {
		int demoraParcial = 0;
		for(int i = 0; i<demorasParciales.length;i++) {	
			demorasParciales[i] = demoraParcial;
			demoraParcial += prioridades[i];
		}
	}

	public void setDemoraTotal(int demora) {
		this.demoraTotal -= demora;
	}
	
	public int getPrioridades(int q) {
		return prioridades[q];
	}
	
	public boolean maximo() {	
		return indice == elementos.length;
	}

	public int primerDemora() {
		return prioridades[0];
	}

	public int getIndice() {
		return elementos.length;
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

	public int demoraParcial(int j) {
		return demorasParciales[j];
	}

	public int[] getElementos() {
		return elementos;
	}

	@Override
	public void InicializarCola() {
		indice = 0;
		elementos = new int[7];
		demorasParciales = new int[7];
		prioridades = new int[7];
		demoraTotal = 0;
	}

	@Override 
	public void acolarPrioridad(int valor, int prioridad) {
		int j = indice;
		for(; j>0 && prioridades[j-1]>=prioridad;j--) {
			elementos[j] = elementos[j-1];		
			prioridades[j] = prioridades[j-1];
		}
		elementos[j] = valor;
		prioridades[j] = prioridad;		
		demoraTotal += prioridad;
		indice++;	
	}

	@Override
	public void Desacolar() {
		indice--;
	}

	@Override
	public int Primero() {
		return elementos[indice-1];
	}

	@Override
	public boolean ColaVacia() {
		return (indice ==0);
	}

	@Override
	public int Prioridad() {
		return elementos[indice-1];
	}

	@Override
	public int elemento(int i) {
		return elementos[i];
	}
}
