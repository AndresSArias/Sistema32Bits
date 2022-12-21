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
	
	private String OPCODE6Bits = "--------------------------------OPCODE 6 BITS--------------------------------\n000000 ZR: 0 \n"
			+ "000001 SUBA: Restar los registros A - B \n"
			+ "000010 SUBB: Restar los registros B-A \n"
			+ "000011 BZROA: Verifica si el registro A es 0 y devuelve true \n"
			+ "000100 ZROB: Verifica si el registro B es 0 y devuelve true \n"
			+ "000101 W2A: Realiza potencia de 2 del registro A \n"
			+ "000110 POW2B: Realiza potencia de 2 del registro B \n"
			+ "000111 JEA: Verifica si el registro A es igual a un valor indicado, devuelve true \n"
			+ "001000 JEB: Verifica si el registro B  es igual a un valor indicado, devuelve true \n"
			+ "001001 JZA: Si el registro A no es igual a un valor especificado devuelve True \n"
			+ "001010 JZBv Si el registro B no es igual a un valor especificado devuelve True \n"
			+ "001011 DIVAv: Divide el registro A entre el registro B \n"
			+ "001100 DIVB: Divide el registro B entre el registro A \n"
			+ "001101 MULT: Multiplica el registro A y B \n"
			+ "001110 EQ: si el registro A y B son iguales devuelve true \n"
			+ "001111 NEQ: Si el registro A y B no son iguales devuelve true\n"
			+ "010000 NZA: Si el registro A no es 0 devuelve true \n"
			+ "010001 NZB: Si el registro B no es 0 devuelve true \n "
			+ "010010 AND: Realiza operación AND entre A y B (&) \n"
			+ "010011 OR: Realiza operación OR entre A y B ( | ) \n "
			+ "010100 NOTA: Niega  A ( ~ ) \n"
			+ "010101 XOR: Realiza operación XOR entre A y B  (  ^ ) \n"
			+ "010110 NOTB: Niega B  ( ~ ) \n"
			+ "010111 EXIT: Apaga el programa \n"
			+ "011000 ADD1: Adiciona 1 al registro A \n"
			+ "011001 ADD2: Adiciona 2 al registro A \n"
			+ "011010 ADD3: Adiciona 3 al registro A \n"
			+ "011011 ADD4: Adiciona 4al registro A \n"
			+ "011100 ADD6: Adiciona 1 al registro B \n"
			+ "011101 ADD7: Adiciona 2 al registro B \n"
			+ "011110 ADD8: Adiciona 3 al registro B \n"
			+ "011111 ADD9: Adiciona 4 al registro B \n"
			+ "100000 SUB1: Resta 1 al registro A \n"
			+ "100001 SUB2: Resta 2 al registro A \n"
			+ "100010 SUB3: Resta 3 al registro A \n"
			+ "100011 SUB4: Resta 4 al registro A \n"
			+ "100100 SUB6: Resta 1 al registro B \n"
			+ "100101 SUB7: Resta 2 al registro B \n"
			+ "100110 SUB8: Resta 3 al registro B \n"
			+ "100111 SUB9: Resta 4 al registro B \n"
			+"101000 MT2A: Duplica el registro A \n"
			+ "101001 MT3A: Triplica el registro A \n"
			+ "101010 DIV2A: Divide a la mitad  registro A \n"
			+ "101011 DIV2B: Divide a la mitad  registro B \n"
			+ "101100  MODA: Imprime el módulo de un número (dividido entre 10) para el registro A \n"
			+ "101101 MODB: Imprime el módulo de un número (dividido entre 10) para el registro B \n"
			+ "101110 SOTA: Imprimir el valor del registro A \n"
			+ "101111 SOTB: Imprimir el valor del registro B \n"
			+ "110000 CLRA: Vaciar registro A \n"
			+ "110001 CLRB: Vaciar registro B \n"
			+ "110010 CLRB: Vaciar Salida \n"
			+ "110011 LOADA: Cargar un valor en A \n"
			+ "110100 LOADB: Cargar un valor en B \n"
			+ "110101 LOADO: Cargar un valor directamente en la salida \n"
			+ "110110 CHG: Intercambiar el valor de A y B \n"
			+ "110111 MVTB: Mover el valor de A a B (copiar) \n"
			+ "111000 MVTA: Mover el valor de B a A (copiar) \n"
			+ "111001 IAP: Si el registro A es par retorna true (imprime 2) \n"
			+ "111010 IBP: Si el registro B es par retorna  true (imprime 2) \n"
			+ "111011 IAI: Si el registro A es impar retorna true (imprime 1) \n"
			+ "111100 IBI: Si el registro B es impar retorna true (imprime 1) \n"
			+ "111101 IAMB: Si A es mayor a B retorna true \n"
			+ "111110 IBMA: Si B es mayor a A retorna true \n"
			+ "111111 ADD: Sumar los registros A y B, se coloca en el registro indicado";

	
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
		
		textArea.setText(OPCODE6Bits);
		textArea.setEditable(false);
		
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

	public String getOPCODE6Bits() {
		return OPCODE6Bits;
	}
	
	
	
}
