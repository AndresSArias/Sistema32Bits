package Controlador;

import java.util.ArrayList;

import javax.swing.JLabel;

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
	private String dirreccionAux = "";
	
	private String[] registroA;
	
	private String[] registroB;

	private String[] alu;

	private String[] salida;
	

	
	
	private int camino = 0;

	
	private boolean enEjecucion = false;

	public Fachada(ventanaPrincipal interfaz) {
		this.interfaz = interfaz;
	}
	
	public void Resetear(panelMemoria panelMemoria, panelSap panelSap, panelCPU panelCPU) {
		
		enEjecucion = false;
		establecerVariables();
		establecerPaneles(panelSap);
		

		
		memoriaEnEjecucion (panelMemoria);
		
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
	
	private void establecerPaneles(panelSap panelSap) {
		actualizarBus(panelSap);
		actualizarIntruccion(panelSap);
		actualizarMar(panelSap);
		actualizarRegistroA(panelSap);
		actualizarRegistroB(panelSap);
		actualizarAlu(panelSap);
		actualizarSalida(panelSap);
	}

	private void establecerVariables() {
		camino = 0;
		bus = new String[] {"0","0","0","0","0","0","0","0","0","0","0","0"};
		mar = new String[] {"0","0","0","0","0","0"};
		historialBus.clear();
		registroA = new String[] {"0","0","0","0","0","0","0","0","0","0","0","0"};
		registroB = new String[] {"0","0","0","0","0","0","0","0","0","0","0","0"};
		alu = new String[] {"0","0","0","0","0","0","0","0","0","0","0","0"};
		salida = new String[] {"0","0","0","0","0","0","0","0","0","0","0","0"};
	}

	private void actualizarSalida(panelSap panel) {
		
		for (int i = 0; i < salida.length; i++) {
			panel.getBtns_bitsOUT()[i].setText(salida[i]);
		}
		
	}

	private void actualizarAlu(panelSap panel) {
		
		for (int i = 0; i < alu.length; i++) {
			panel.getBtns_bitsALU()[i].setText(alu[i]);
		}
	}

	private void actualizarRegistroA(panelSap panel) {
		
		for (int i = 0; i < registroA.length; i++) {
			panel.getBtns_bitsA()[i].setText(registroA[i]);
		}
	}
	
	private void actualizarRegistroB(panelSap panel) {
		
		for (int i = 0; i < registroB.length; i++) {
			panel.getBtns_bitsB()[i].setText(registroB[i]);
		}
	}
	
	private void setMar(panelMemoria panel) {
		
		mar = new String[6];
		marS = "";
		
		for (int j = 0; j < 6; j++) {
			mar[j] = (String) panel.getTablaMemoria().getValueAt(camino, (j+7));
			marS = marS + mar[j];
		}		
		
		for (int i = 0; i < memoria.length ; i++) {
			dirreccionAux = memoria[i][0].replaceAll("\\[", "").replaceAll("\\]","");
	
			if (marS.equals(dirreccionAux)) {
				caminoAux = i;
				break;
			}
			
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
		
		for (int i = 0; i < mar.length; i++) {
			panel.getBtns_bitsMAR()[i].setText(mar[i]);
		}
		
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
				
				
				System.out.print(memoria[i][0]+"\t");
				
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
			
		}else if (memoriaEnEjecucion(panel) == false ){
			
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
