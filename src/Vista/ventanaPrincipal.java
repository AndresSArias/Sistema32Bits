package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import Controlador.ControladorEvento;
import Controlador.Fachada;

public class ventanaPrincipal extends JFrame {

	private panelMemoria panelMemoria;
	private panelSap panelSap;
	private panelCPU panelCPU;
	private ControladorEvento control;
	private Fachada fachada;
	
  public ventanaPrincipal (){
    initComponents();
    capturarEventos();
  }

  public void iniciar(){
	fachada = new Fachada (this);
    this.setVisible(true);
  }

  public void initComponents(){
	  
    this.setTitle("ProyectoFinalSistema32Bits");
    this.setResizable(false);
    this.setBounds(100, 100, 1300, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(new BorderLayout(0, 0));
    
    
    panelMemoria = new panelMemoria();
    this.getContentPane().add(panelMemoria,BorderLayout.EAST);
    
    panelSap = new panelSap();
    this.getContentPane().add(panelSap,BorderLayout.WEST);
    
    panelCPU = new panelCPU();
    this.getContentPane().add(panelCPU,BorderLayout.CENTER);
    
  }

  public void capturarEventos() {
	  
	  panelMemoria.getTablaMemoria().addMouseListener(getControl());
	  panelMemoria.getBotonBorrarMemoria().addActionListener(getControl());
	  panelCPU.getBotonEjecutar1().addActionListener(getControl());
	  
  }
  public ControladorEvento getControl() {
		
		if (control == null) {
			control = new ControladorEvento(this);
			}
		return control;
		
	}

	public panelMemoria getPanelMemoria() {
		return panelMemoria;
	}
	
	public panelSap getPanelSap() {
		return panelSap;
	}
	
	public panelCPU getPanelCPU() {
		return panelCPU;
	}
	
	public Fachada getFachada() {
		return fachada;
	}
	  
  
  
  
}