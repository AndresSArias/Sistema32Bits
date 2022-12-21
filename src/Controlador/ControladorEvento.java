package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.Timer;

import Vista.ventanaPrincipal;


public class ControladorEvento implements ActionListener, MouseListener{

	private ventanaPrincipal interfaz;
	private Timer t;
	private int tiempo = 1000;
	private int tiempoOpcode = 1000;
	public ControladorEvento (ventanaPrincipal interfaz) {
		
		this.interfaz = interfaz;

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == interfaz.getPanelMemoria().getBotonBorrarMemoria()) {
			interfaz.getFachada().BorrarMemoria(interfaz.getPanelMemoria());
		}
		if(ae.getSource() == interfaz.getPanelCPU().getBotonEjecutar1()) {
			try {
				t.stop();
			}catch (NullPointerException npe) {
				
			}
			interfaz.getFachada().Ejecutar(interfaz.getPanelMemoria(), interfaz.getPanelSap());
			
			t = new Timer (tiempo, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					interfaz.getFachada().EjecutarOpcode(interfaz.getPanelMemoria(), interfaz.getPanelSap(), interfaz.getPanelCPU());
				}
				
			});
			t.start();
			

		}
		if(ae.getSource() == interfaz.getPanelCPU().getBotonEjecutar()) {
			
			t = new Timer (tiempo, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					interfaz.getFachada().Ejecutar(interfaz.getPanelMemoria(), interfaz.getPanelSap());
				}
				
			});
			t.start();
			
		}
		if (ae.getSource() == interfaz.getPanelCPU().getBotonReset()) {
			interfaz.getFachada().Resetear(interfaz.getPanelMemoria(),interfaz.getPanelSap(),interfaz.getPanelCPU());
			try {
				t.stop();
			}catch (NullPointerException npe) {
				
			}
			
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
