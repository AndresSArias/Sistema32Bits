package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import Vista.ventanaPrincipal;


public class ControladorEvento implements ActionListener, MouseListener{

	private ventanaPrincipal interfaz;
	
	public ControladorEvento (ventanaPrincipal interfaz) {
		
		this.interfaz = interfaz;

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == interfaz.getPanelMemoria().getBotonBorrarMemoria()) {
			interfaz.getFachada().BorrarMemoria(interfaz.getPanelMemoria());
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		
		if (me.getClickCount() == 1) {
			JTable apuntar = (JTable)me.getSource();
			interfaz.getFachada().interactuarTablaMemoria (interfaz.getPanelMemoria(),apuntar.getSelectedRow(),apuntar.getSelectedColumn());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
