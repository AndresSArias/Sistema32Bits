package Controlador;

import java.util.ArrayList;

import Vista.panelCPU;
import Vista.panelMemoria;
import Vista.panelSap;
import Vista.ventanaPrincipal;


public class Fachada {
	
	private ventanaPrincipal interfaz;
	
	private String[][] memoria;
	
	private String[] bus;
	private ArrayList<String[]> historialBus = new ArrayList<String[]>();
	
	private String[] mar;
	private String marS;
	private int caminoAux = 0;
	
	
	private int camino = 0;

	
	private boolean enEjecucion = false;

	public Fachada(ventanaPrincipal interfaz) {
		this.interfaz = interfaz;
	}
	
	
	
	public void Ejecutar(panelMemoria panelMemoria, panelSap panelSap) {
		
		if (camino >= 64) {
			camino = 0;
			
		}
		
		enEjecucion = true;
		getMemoria(panelMemoria);
		setBus(panelMemoria);
		actualizarBus(panelSap);
		actualizarIntruccion(panelSap);
		setMar(panelMemoria);
		actualizarMar(panelSap);
		memoriaEnEjecucion (panelMemoria);
		camino++;

	}
	
	private void setMar(panelMemoria panel) {
		
		mar = new String[6];
		marS = null;
		
		for (int j = 0; j < 6; j++) {
			bus[j] = (String) panel.getTablaMemoria().getValueAt(camino, (j+7));
			marS = marS + bus[j];
		}
		
		for (int i = 0; i < memoria.length ; i++) {
			
			for (int j = 0 ; j  < memoria[0].length; j++) {
				
				
				System.out.print(memoria[i][j]+"\t");
				
			}
			
			System.out.print("\n");
			
		}
		
	}



	private void actualizarIntruccion(panelSap panel) {
		
		
		for (int i = 0; i < bus.length; i++) {
			try {
			panel.getBtns_bitsIR()[i].setText(historialBus.get(historialBus.size()-2)[i]);
			} catch (IndexOutOfBoundsException ioobe) {
				panel.getBtns_bitsIR()[i].setText("0");
			}
		}
		
	}



	private void actualizarMar(panelSap panel) {
		
		
		
	}



	private boolean memoriaEnEjecucion(panelMemoria panel) {
		
		if (enEjecucion) {
			panel.getTablaMemoria().setEnabled(false);
			panel.getTablaMemoria().setRowSelectionAllowed(true);
			panel.getTablaMemoria().setRowSelectionInterval(camino, camino);
			
		}else {
			panel.getTablaMemoria().setEnabled(true);
			panel.getTablaMemoria().setRowSelectionAllowed(false);
		}
		
		return (enEjecucion);
		
	}

	private void actualizarBus(panelSap panel) {
		for (int i = 0; i < bus.length; i++) {
			panel.getBtns_bistBUS()[i].setText(bus[i]);
		}
		
	}

	private void setBus(panelMemoria panel) {
		
		bus = new String[12];
		
		for (int j = 0; j < 12; j++) {
			bus[j] = (String) panel.getTablaMemoria().getValueAt(camino, (j+1));
		}
		
		historialBus.add(bus);
		
	}

	private void imprimirMemoria() {
		
		for (int i = 0; i < memoria.length ; i++) {
			
			for (int j = 0 ; j  < memoria[0].length; j++) {
				
				
				System.out.print(memoria[i][j]+"\t");
				
			}
			
			System.out.print("\n");
			
		}
		
	}

	public void getMemoria(panelMemoria panel) {
		
		memoria = new String[64][13];
		
		for (int i = 0; i < 64; i++) {
			for(int j = 0; j < 13; j++) {
			
				memoria[i][j] = (String) panel.getTablaMemoria().getValueAt(i, j);
				
				
			}
		}
		
	}
	
	public void interactuarTablaMemoria(panelMemoria panel, int fila, int columna) {
		
		if (memoriaEnEjecucion(panel) == true) {
			
		}else {
			
			String valor = (String) panel.getTablaMemoria().getValueAt(fila, columna);
			
			if (valor.equals("0")) {
				panel.getTablaMemoria().setValueAt("1", fila, columna);
			}else if (valor.equals("1")) {
				panel.getTablaMemoria().setValueAt("0", fila, columna);
			}
		}
		

		
	}

	

	
	public void BorrarMemoria(panelMemoria panel) {
		
		panel.establacerMemoria();
		
		
	}


	

}
