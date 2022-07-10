package negocio;

import java.util.ArrayList;
import interfaces.AdministradorDeColasTDA;
import interfaces.DiccionarioSimpleTDA;
import utils.Diccionario;

public class AdministradorDeColas implements AdministradorDeColasTDA {
	private static ArrayList<PuestoCaja> puestos = new ArrayList();
	private static ArrayList<PuestoCajaPrioridad> puestosPrioritarios = new ArrayList();
	private DiccionarioSimpleTDA servicios = new Diccionario();
	private int identificadorCliente = 1;

	private void timer (String sout) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(sout);
	}

	private void generarNomenclatura() {
		servicios.InicializarDiccionario();
		servicios.Agregar("J", 30);
		servicios.Agregar("P", 10);
		servicios.Agregar("C", 40);
		//Servicios
		servicios.Agregar("PF", 10);
		servicios.Agregar("CH", 40);
		servicios.Agregar("CG", 15);
	}

	@Override
	public void Inicializar(int n) {
		generarNomenclatura();
		PuestoCaja auxiliar1 = new PuestoCaja();
		auxiliar1.InicializarCola();

		PuestoCaja auxiliar2 = new PuestoCaja();
		auxiliar2.InicializarCola();

		PuestoCaja auxiliar3 = new PuestoCaja();
		auxiliar3.InicializarCola();

		auxiliar1.setServicio("J");
		auxiliar2.setServicio("P");
		auxiliar3.setServicio("C");
		auxiliar1.setDemoraTotal(0);
		auxiliar2.setDemoraTotal(0);
		auxiliar3.setDemoraTotal(0);

		puestos.add(auxiliar1);
		puestos.add(auxiliar2);
		puestos.add(auxiliar3);

		PuestoCajaPrioridad prioritario1 = new PuestoCajaPrioridad();
		prioritario1.InicializarCola();

		PuestoCajaPrioridad prioritario2= new PuestoCajaPrioridad();
		prioritario2.InicializarCola();

		PuestoCajaPrioridad prioritario3 = new PuestoCajaPrioridad();
		prioritario3.InicializarCola();

		prioritario1.setServicio("Servicios"); 
		prioritario2.setServicio("General 1"); 
		prioritario3.setServicio("General 2");	

		puestosPrioritarios.add(prioritario1);
		puestosPrioritarios.add(prioritario2);
		puestosPrioritarios.add(prioritario3);
	}

	private void acolarMaximo(int demoraElemento,int cantidad) {
		if(!puestosPrioritarios.get(1).maximo()) {
			System.out.println("El turno" + "\t" + identificadorCliente + "\t" + "se ha acolado en " + "\t" + puestosPrioritarios.get(1).getServicio() + "\t" +  "con una demora de: " + "\t" +  demoraElemento  );

			puestosPrioritarios.get(1).acolarPrioridad(identificadorCliente, demoraElemento);
			identificadorCliente++;
		} else {
			System.out.println("El turno" + "\t" + identificadorCliente + "\t" + "se ha acolado en " + "\t" + puestosPrioritarios.get(2).getServicio() + "\t" + "con una demora de: " + "\t" +  demoraElemento  );

			puestosPrioritarios.get(2).acolarPrioridad(identificadorCliente, demoraElemento);
			identificadorCliente++;
		}
	}

	@Override
	public void Acolar(String nombreFila, int cantidad) {
		int demoraElemento = servicios.Recuperar(nombreFila);
		if(nombreFila.equals("PF") || nombreFila.equals("CG") || nombreFila.equals("CH")) {
			for(int i = 0;i<cantidad;i++) {
				if(!puestosPrioritarios.get(0).maximo()) {	
					System.out.println("El turno" + "\t" + identificadorCliente + "\t" + "se ha acolado en " + "\t" + puestosPrioritarios.get(0).getServicio() + "\t" +  "con una demora de: " + "\t" + demoraElemento );

					puestosPrioritarios.get(0).acolarPrioridad(identificadorCliente, demoraElemento);
					identificadorCliente++;
				} else {
					acolarMaximo(demoraElemento,cantidad);
				}
			}
		} else {
			for(int j = 0;j<puestos.size();j++) {				
				if(puestos.get(j).getServicio().equals(nombreFila)) {						
					for(int q = 0;q<cantidad;q++) {
						if(!puestos.get(j).maximo()) {
							String strOut = String.format("El turno %d se ha acolado en %s con una demora de: %d", identificadorCliente, puestos.get(j).getServicio(), demoraElemento);
							timer(strOut);
							puestos.get(j).Acolar(identificadorCliente, demoraElemento);
							identificadorCliente++;
						} else {
							acolarMaximo(demoraElemento,cantidad);
						}
					}
				}
			}
		}
	}

	@Override
	public void Desacolar() {
		int menor = 9000;
		int id = 0;
		boolean puestoPrioritario = true;
		for(int i = 0; i<puestos.size();i++) {			
			if(puestos.get(i).Primero() < menor) {
				menor = puestos.get(i).PrimerDemora();
				id = i;
				puestoPrioritario = false;
			}
			if(puestosPrioritarios.get(i).Primero() < menor){
				menor = puestosPrioritarios.get(i).getPrioridades(0);
				id = i;
			}
		}
		if(puestoPrioritario) {
			int demoraPrevia = puestosPrioritarios.get(id).getDemoraTotal();
			puestosPrioritarios.get(id).Desacolar();
			puestosPrioritarios.get(id).setDemoraTotal(menor);
			System.out.println("El elemento se ha desacolado, pasó de una demora total de: " + "\t" + demoraPrevia + "\t" + "minutos a " + "\t" + puestosPrioritarios.get(id).getDemoraTotal());
		} else {
			int demoraAnterior = puestos.get(id).getDemoraTotal();
			puestos.get(id).Desacolar();
			puestos.get(id).setDemoraTotal(menor);
			System.out.println("El elemento se ha desacolado, pasó de una demora total de: " + "\t" + demoraAnterior + "\t" + "minutos a " + "\t" + puestos.get(id).getDemoraTotal());
		}
	}


	@Override
	public Object Programacion(String servicio) {
		PuestoCajaPrioridad retornar2 = new PuestoCajaPrioridad();
		retornar2.InicializarCola();

		PuestoCaja retornar = new PuestoCaja();
		retornar.InicializarCola();

		for(int i = 0; i<puestosPrioritarios.size();i++) {				
			if(puestosPrioritarios.get(i).getServicio().equals(servicio)){
				retornar2 = puestosPrioritarios.get(i);
			}
			if(puestos.get(i).getServicio().equals(servicio)) {
				retornar = puestos.get(i);
			}
		}
		if(!retornar.ColaVacia()) {
			System.out.println("Para el servicio"+"\t"+servicio+"\t"+"hay una demora total de: " + retornar.getDemoraTotal() + "\t"+ "minutos");
			return retornar;

		} else {
			System.out.println("Para el servicio"+"\t"+servicio+"\t"+"hay una demora total de: " + retornar2.getDemoraTotal() + "\t"+ "minutos");
			return retornar2;
		}
	}

	@Override
	public int CantidadDeColas() {
		int cantidad = puestos.size() + puestosPrioritarios.size();
		System.out.println("Para este banco, hay " + "\t" + cantidad + "\t" + "de puestos de atención");
		return cantidad;
	}

	@Override
	public int Primero() {
		int id = 0;
		int menor = 1440;
		for(int i = 0;i<puestos.size();i++) {
			if(puestos.get(i).PrimerDemora() < menor) {
				id = puestos.get(i).Primero();
				menor = puestos.get(i).PrimerDemora();
			}
			else if(puestosPrioritarios.get(i).Prioridad() < menor) {
				id = puestos.get(i).Primero();
				menor = puestos.get(i).PrimerDemora();
			}
		}
		return id;
	}

	@Override
	public int TiempoEstimado() {
		int menor = 1440;
		for(int i = 0;i<puestos.size();i++) {			
			if(puestos.get(i).PrimerDemora() < menor) {
				menor = puestos.get(i).PrimerDemora();
			}
			else if(puestosPrioritarios.get(i).Prioridad() < menor) {
				menor = puestos.get(i).PrimerDemora();
			}
		}
		return menor;
	}

	@Override
	public String PuestoDelProximoElemento() {
		String servicio = "";
		int menor = 1440;
		for(int i = 0;i<puestos.size();i++) {		
			if(puestos.get(i).PrimerDemora() < menor) {
				servicio = puestos.get(i).getServicio();
				menor = puestos.get(i).PrimerDemora();
			}
			else if(puestosPrioritarios.get(i).primerDemora() < menor) {			
				menor = puestosPrioritarios.get(i).primerDemora();
				servicio = puestos.get(i).getServicio();
			}
		}
		System.out.println("El próximo elemento corresponde a la fila" + "\t" + servicio);
		return servicio;
	}

	

	@Override
	public int PuestoDelElemento(int id) {
		String nombreServicio = "";
		int idFila = 0;
		boolean encontrado = false;
		for(int i = 0; i<puestos.size();i++) {
			if(!encontrado) {
				for(int q = 0; q<puestos.get(i).longitud();q++) {
					if(puestos.get(i).elemento(q) == id) {
						encontrado = true;
						idFila = q;
						nombreServicio = puestos.get(i).getServicio();
					}
				}
			} else {
				for(int j = 0;j<puestosPrioritarios.get(i).getElementos().length;j++){
					if(puestosPrioritarios.get(i).elemento(j) == id) {
						encontrado  = true;
						idFila = j;
						nombreServicio = puestosPrioritarios.get(i).getServicio();
					}
				}
			}
		}
		System.out.println("El elemento buscado se encuentra en la posición" + "\t" + idFila + "\t" + "del puesto" + "\t" + nombreServicio);
		return idFila;
	}

	@Override
	public void Elementos() {
		for(int i = 0; i<puestos.size();i++){
			System.out.println("El nombre del servicio es: " + "\t" + puestos.get(i).getServicio() + "\t" + "Tiene una demora total de: " + "\t" + puestos.get(i).getDemoraTotal() + "\t" + "minutos."  + "\t" + "Los clientes acolados a este puesto son:" + "\t");
			for(int j = 0; j<puestos.get(i).longitud();j++) {
				System.out.println("\t" + "El turno" + "\t" + puestos.get(i).elemento(j) + "\t" + "será atendido en: " + "\t" + puestos.get(i).demoraParcial(j) + "\t" + "minutos" + "\t" + "en la posición" + "\t" + j +"\t");
			}
			System.out.println("El nombre del servicio es: " + "\t" + puestosPrioritarios.get(i).getServicio() + "\t" + "Tiene una demora total de: " + "\t" + puestosPrioritarios.get(i).getDemoraTotal() + "\t" + "minutos." + "\t" + "Los clientes acolados a este puesto son:" + "\t");
			for(int q = 0; q<puestosPrioritarios.get(i).getIndice();q++) {
				puestosPrioritarios.get(i).setDemorasParciales();
				System.out.println("\t" + "El turno" + "\t" + puestosPrioritarios.get(i).elemento(q) + "\t" + "será atendido en: " + "\t" + puestosPrioritarios.get(i).demoraParcial(q) + "\t" + "minutos" + "\t" + "en la posición" + "\t" + q +"\t");
			}
		}
	}
}

