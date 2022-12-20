package Controlador;

import Vista.panelMemoria;
import Vista.ventanaPrincipal;


public class Fachada {
	
	private ventanaPrincipal interfaz;

	public Fachada(ventanaPrincipal interfaz) {
		this.interfaz = interfaz;
	}

	public void interactuarTablaMemoria(panelMemoria panel, int fila, int columna) {
		
		
		String valor = (String) panel.getTablaMemoria().getValueAt(fila, columna);
		
		if (valor.equals("0")) {
			panel.getTablaMemoria().setValueAt("1", fila, columna);
		}else if (valor.equals("1")) {
			panel.getTablaMemoria().setValueAt("0", fila, columna);
		}

		
	}

	public void BorrarMemoria(panelMemoria panel) {
		
		panel.establacerMemoria();
		
		
	}

}
