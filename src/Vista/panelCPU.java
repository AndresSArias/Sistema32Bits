package Vista;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class panelCPU extends JPanel {
	
	private JButton botonEjecutar;
	private JButton botonEjecutar1;
	private JButton botonReset;
	private JTextArea textArea ;
	
	public panelCPU() {
		initComponents();
	}

	private void initComponents() {
		
		this.setBounds(0,0,400,800);
		this.setLayout(null);
		
		botonEjecutar = new JButton("EJECUTAR");
		botonEjecutar.setBounds(10, 134, 364, 23);
		this.add(botonEjecutar);
		
		botonEjecutar1 = new JButton("EJECUTAR 1 PASO");
		botonEjecutar1.setBounds(10, 87, 364, 23);
		this.add(botonEjecutar1);
		
		botonReset = new JButton("RESETEAR");
		botonReset.setBounds(10, 41, 364, 23);
		this.add(botonReset);		
		
		JScrollPane scrollTextArea = new JScrollPane();
		scrollTextArea.setBounds(10, 187, 364, 563);
		this.add(scrollTextArea);
		
		textArea = new JTextArea();
		scrollTextArea.setViewportView(textArea);
		
	}

	public JButton getBotonEjecutar() {
		return botonEjecutar;
	}

	public JButton getBotonEjecutar1() {
		return botonEjecutar1;
	}

	public JButton getBotonReset() {
		return botonReset;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
	
	
	
}
