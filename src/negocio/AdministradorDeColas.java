package negocio;

import java.util.ArrayList;
import interfaces.AdministradorDeColasTDA;
import interfaces.DiccionarioSimpleTDA;
import utils.Diccionario;

public class AdministradorDeColas implements AdministradorDeColasTDA {
	private static final ArrayList<PuestoCaja> puestos = new ArrayList<>();
	private static final ArrayList<PuestoCajaPrioridad> puestosPrioritarios = new ArrayList<>();
	private final DiccionarioSimpleTDA nomenclatura = new Diccionario();
	private int identificadorCliente = 1;

	private void Timer(String sout) {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(sout);
	}

	private void GenerarNomenclatura() {
		nomenclatura.InicializarDiccionario();
		nomenclatura.Agregar("J", 30);
		nomenclatura.Agregar("P", 10);
		nomenclatura.Agregar("C", 40);
		//Servicios
		nomenclatura.Agregar("PF", 10);
		nomenclatura.Agregar("CH", 40);
		nomenclatura.Agregar("CG", 15);
	}

	@Override
	public void Inicializar(int n) {
		GenerarNomenclatura();
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

	private void AcolarMaximo(int demoraElemento) {
		if(!puestosPrioritarios.get(1).maximo()) {
			String strOut = String.format("\nEl turno %d se ha acolado en el puesto %s con una demora de: %d minutos", identificadorCliente, puestosPrioritarios.get(1).getServicio(), demoraElemento);
			Timer(strOut);
			puestosPrioritarios.get(1).acolarPrioridad(identificadorCliente, demoraElemento);
			identificadorCliente++;
		} else {
			String strOut = String.format("\nEl turno %d se ha acolado en el puesto %s con una demora de: %d minutos", identificadorCliente, puestosPrioritarios.get(2).getServicio(), demoraElemento);
			Timer(strOut);
			puestosPrioritarios.get(2).acolarPrioridad(identificadorCliente, demoraElemento);
			identificadorCliente++;
		}
	}

	@Override
	public void Acolar(String nombreFila, int cantidad) {
		int demoraElemento = nomenclatura.Recuperar(nombreFila);
		if(nombreFila.equals("PF") || nombreFila.equals("CG") || nombreFila.equals("CH")) {
			for(int i = 0;i<cantidad;i++) {
				if(!puestosPrioritarios.get(0).maximo()) {
					String strOut = String.format("\nEl turno %d se ha acolado en el puesto %s con una demora de: %d minutos", identificadorCliente, puestosPrioritarios.get(0).getServicio(), demoraElemento);
					Timer(strOut);
					puestosPrioritarios.get(0).acolarPrioridad(identificadorCliente, demoraElemento);
					identificadorCliente++;
				} else {
					AcolarMaximo(demoraElemento);
				}
			}
		} else {
			for (PuestoCaja puesto : puestos) {
				if (puesto.getServicio().equals(nombreFila)) {
					for (int q = 0; q < cantidad; q++) {
						if (!puesto.maximo()) {
							String strOut = String.format("\nEl turno %d se ha acolado en el puesto %s con una demora de: %d minutos", identificadorCliente, puesto.getServicio(), demoraElemento);
							Timer(strOut);
							puesto.Acolar(identificadorCliente, demoraElemento);
							identificadorCliente++;
						} else {
							AcolarMaximo(demoraElemento);
						}
					}
				}
			}
		}
	}

	@Override
	public void Desacolar() {
		int menor = 9999;
		int id = 0;
		boolean puestoPrioritario = true;
		for (PuestoCaja ignored : puestos) {
			if(puestos.get(id).Primero() < menor) {
				menor = puestos.get(id).PrimerDemora();
				puestoPrioritario = false;
			}
			if(puestosPrioritarios.get(id).Primero() < menor){
				menor = puestosPrioritarios.get(id).getPrioridades(0);
			}
		}
		if(puestoPrioritario) {
			for (int i = 0; i < puestosPrioritarios.get(id).getDemoraTotal() ; puestosPrioritarios.get(id).Desacolar()) {
				int demoraPrevia = puestosPrioritarios.get(id).getDemoraTotal();
				puestosPrioritarios.get(id).setDemoraTotal(menor);
				String strOut = String.format("\nSe atendio un cliente, el puesto %s pasó de una demora total de %d minutos a %d minutos", puestosPrioritarios.get(id).getServicio(), demoraPrevia, puestosPrioritarios.get(id).getDemoraTotal());
				Timer(strOut);
			}
		} else {
			for (int i = 0; i < puestos.get(id).getDemoraTotal(); puestos.get(id).Desacolar()) {
				int demoraPrevia = puestos.get(id).getDemoraTotal();
				puestos.get(id).setDemoraTotal(menor);
				String strOut = String.format("\nSe atendio un cliente, el puesto %s pasó de una demora total de %d minutos a %d minutos", puestos.get(id).getServicio(), demoraPrevia, puestos.get(id).getDemoraTotal());
				Timer(strOut);
			}
		}
	}

	@Override
	public int CantidadDeColas() {
		int cantidad = puestos.size() + puestosPrioritarios.size();
		String strOut = String.format("\nPara este banco hay %d puestos de atencion.", cantidad);
		Timer(strOut);
		return cantidad;
	}

	@Override
	public int Primero() {
		int id = 0;
		int menor = 9999;
		for(int i = 0;i < puestos.size(); i++) {
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
	public void Elementos() {
		for(int i = 0; i < puestos.size(); i++){
			String strOutServ = String.format("\nEl puesto %s tiene una demora total de %d minutos. \nTurnos en espera: ", puestos.get(i).getServicio(), puestos.get(i).getDemoraTotal());
			Timer(strOutServ);
			for(int j = 0; j < puestos.get(i).longitud(); j++) {
				String strOut = String.format("\nEl turno %d sera atendido en %d minutos, en la posicion %d", puestos.get(i).elemento(j), puestos.get(i).demoraParcial(j), j);
				Timer(strOut);
			}
			String strOutServ2 = String.format("\nEl puesto %s tiene una demora total de %d minutos. \nTurnos en espera: ", puestosPrioritarios.get(i).getServicio(), puestosPrioritarios.get(i).getDemoraTotal());
			Timer(strOutServ2);
			for(int q = 0; q < puestosPrioritarios.get(i).getIndice(); q++) {
				puestosPrioritarios.get(i).setDemorasParciales();
				String strOut2 = String.format("\nEl turno %d sera atendido en %d minutos en la posicion %d", puestos.get(i).elemento(q), puestos.get(i).demoraParcial(q), q);
				Timer(strOut2);
			}
		}
	}
}
