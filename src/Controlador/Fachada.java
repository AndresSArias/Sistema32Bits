package Controlador;

import java.util.ArrayList;

import javax.swing.JLabel;

import Vista.panelCPU;
import Vista.panelMemoria;
import Vista.panelSap;
import Vista.ventanaPrincipal;
import Modelo.ALU;



public class Fachada {
	
	private ventanaPrincipal interfaz;
	private String[] opcodes;
	private String opcode;
	private int tiempoOpcode = 1000;
	private String[][] memoria;
	
	private String[] bus;
	private ArrayList<String[]> historialBus = new ArrayList<String[]>();
	
	private String[] mar;
	private String marS;
	private int caminoAux = 0;
	private String dirreccionAux = "";
	
	private String registroA;
	
	private String registroB;
	
	private String registroMemoria;

	private String alu;

	private String salida;
	
	public boolean bandera = false;
	
	
	private int camino = 0;
	
	private ALU ALU;

	
	private boolean enEjecucion = false;

	public Fachada(ventanaPrincipal interfaz) {
		this.interfaz = interfaz;
		obtenerOpcodes(interfaz.getPanelMemoria().getDirrecciones());
		ALU = new ALU();
	}

	public void Resetear(panelMemoria panelMemoria, panelSap panelSap, panelCPU panelCPU) {
		
		enEjecucion = false;
		establecerVariables();
		establecerPaneles(panelSap);
		

		
		memoriaEnEjecucion (panelMemoria);
		
	}
	
	public void Ejecutar(panelMemoria panelMemoria, panelSap panelSap) {
		enEjecucion = true;
		memoriaEnEjecucion (panelMemoria);
		
		if (camino >= 64) {
			camino = 0;
			
		}
	
		memoriaEnCamino(panelMemoria);
		
		getMemoria(panelMemoria);
		setBus(panelMemoria);
		setMar(panelMemoria);
		setOpcode();
		actualizarBus(panelSap);
		actualizarIntruccion(panelSap);
		actualizarMar(panelSap);


		camino++;

        try {
        	interfaz.getPanelMemoria().getTablaMemoria().repaint();
            Thread.sleep(tiempoOpcode);
        } catch (Exception e) {
            System.out.println(e);
        }
		
	}
	
	public void EjecutarOpcode(panelMemoria panelMemoria, panelSap panelSap, panelCPU panelCPU) {
		
        
        
        if(marS.equals("000000")) {
        	
        }else {
        	memoriaEnMar(panelMemoria);
    		OpcodearRegistro(panelSap, panelMemoria, panelCPU);
        }
        
    
	}
	
	private void memoriaEnCamino(panelMemoria panel) {
		
		panel.getTablaMemoria().setRowSelectionAllowed(true);
		panel.getTablaMemoria().setRowSelectionInterval(camino, camino);
		
	}
	
	private void memoriaEnMar(panelMemoria panel) {
		
		panel.getTablaMemoria().setRowSelectionAllowed(true);
		panel.getTablaMemoria().setRowSelectionInterval(caminoAux, caminoAux);
		
	}

	private void OpcodearRegistro(panelSap panelSap, panelMemoria panelMemoria, panelCPU panelCPU) {
		
		switch(opcode) {
		
		  case "000000": {

			System.out.print ("Soy un cero");
			break;
			  
		  }
		  
		  case "000001": {
			  
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  actualizarRegistroA(panelSap);
			  actualizarRegistroB(panelSap);
			  alu = ALU.Resta(registroA, registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);
			  
			  break;
			  
		  }
		  case "000010": {
			  
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  actualizarRegistroA(panelSap);
			  actualizarRegistroB(panelSap);
			  alu = ALU.Resta(registroB, registroA);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  
		  case "000011": {
			  
			setRegistroA(panelSap);
			
			if(registroA.equals("000000000000")) {
				System.out.println("El registro A es 0, TRUE");
			}
			
			break;
			  
		  }
		    
		  case "000100": {

			setRegistroB(panelSap);
			
			if(registroB.equals("000000000000")) {
				System.out.println("El registro B es 0, TRUE");
			}
			
			break;
			  
		  }
		  case "000101": {
			  
			  setRegistroA(panelSap);
			  alu = ALU.Potencia(registroA);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);	
			  break;
			  
		  }
		    
		  case "000110": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.Potencia(registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);	
			break;
			  
		  }
		  
		  case "000111": {
			  
			setRegistroA(panelSap);
			getRegistroMemoria();
			
			if (registroA.equals(registroMemoria)) {
				System.out.println("TRUE");
			}
			
			break;
			  
		  }
		    
		  case "001000": {
			  
				setRegistroB(panelSap);
				getRegistroMemoria();
				
				if (registroB.equals(registroMemoria)) {
					System.out.println("TRUE");
				}
			break;
			  
		  }
		  case "001001": {
				setRegistroA(panelSap);
				getRegistroMemoria();
				
				if (!registroA.equals(registroMemoria)) {
					System.out.println("TRUE");
				}
			break;
			  
		  }
		    
		  case "001010": {
				setRegistroB(panelSap);
				getRegistroMemoria();
				
				if (!registroB.equals(registroMemoria)) {
					System.out.println("TRUE");
				}
			break;
			  
		  }
		  
		  case "001011": {
			  
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  actualizarRegistroA(panelSap);
			  actualizarRegistroB(panelSap);
			  alu = ALU.Divide(registroA, registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "001100": {
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  actualizarRegistroA(panelSap);
			  actualizarRegistroB(panelSap);
			  alu = ALU.Divide(registroB, registroA);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  case "001101": {
			  
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  actualizarRegistroA(panelSap);
			  actualizarRegistroB(panelSap);
			  alu = ALU.Multiplicacion(registroA, registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "001110": {
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
				if (registroA.equals(registroB)) {
					System.out.println("TRUE");
				}
			break;
			  
		  }
		  
		  case "001111": {
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
				if (!registroA.equals(registroB)) {
					System.out.println("TRUE");
				}			  

			break;
			  
		  }
		    
		  case "010000": {
			  
				setRegistroA(panelSap);
				
				if(!registroA.equals("000000000000")) {
					System.out.println("El registro A no es 0, TRUE");
				}
				
			break;
			  
		  }
		  case "010001": {
			  
				setRegistroB(panelSap);
				
				if(!registroB.equals("000000000000")) {
					System.out.println("El registro B es 0, TRUE");
				}
			break;
			  
		  }
		    
		  case "010010": {

			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  alu = ALU.AND(registroA, registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);	  
	  
			break;
			  
		  }
		  
		  case "010011": {
			  
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  alu = ALU.OR(registroA, registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "010100": {
			  
			  setRegistroA(panelSap);

			break;
			  
		  }
		  case "010101": {
			  
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  alu = ALU.XOR(registroA, registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "010110": {
			  

			  
			break;
			  
		  }
		  
		  case "010111": {
			  
			  Resetear(panelMemoria, panelSap, panelCPU);
			  
			break;
			  
		  }
		    
		  case "011000": {
			  
			  setRegistroA(panelSap);
			  alu = ALU.Suma(registroA, "00000000001");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  case "011001": {
			  
			  setRegistroA(panelSap);
			  alu = ALU.Suma(registroA, "000000000010");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "011010": {
			  setRegistroA(panelSap);
			  alu = ALU.Suma(registroA, "000000000011");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  
		  case "011011": {
			  setRegistroA(panelSap);
			  alu = ALU.Suma(registroA, "000000000100");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "011100": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.Suma(registroB, "00000000001");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			  
			break;
			  
		  }
		  case "011101": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.Suma(registroB, "000000000010");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "011110": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.Suma(registroB, "000000000011");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  
		  case "011111": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.Suma(registroB, "000000000100");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "100000": {
			  setRegistroA(panelSap);
			  alu = ALU.Resta(registroA, "00000000001");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);			  

			break;
			  
		  }
		  case "100001": {
			  setRegistroA(panelSap);
			  alu = ALU.Resta(registroA, "000000000010");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "100010": {
			  setRegistroA(panelSap);
			  alu = ALU.Resta(registroA, "000000000011");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  
		  case "100011": {
			  
			  setRegistroA(panelSap);
			  alu = ALU.Resta(registroA, "000000000100");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "100100": {
			  setRegistroB(panelSap);
			  alu = ALU.Resta(registroB, "00000000001");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  case "100101": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.Resta(registroB, "000000000010");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "100110": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.Resta(registroB, "000000000011");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  
		  case "100111": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.Resta(registroB, "000000000100");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			  
			break;
			  
		  }
		    
		  case "101000": {
			  
			  setRegistroA(panelSap);
			  alu = ALU.Multiplicacion(registroA, "000000000010");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);

			break;
			  
		  }
		  case "101001": {
			  setRegistroA(panelSap);
			  alu = ALU.Multiplicacion(registroA, "000000000011");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "101010": {
			  
			  setRegistroA(panelSap);
			  alu = ALU.Divide(registroA, "000000000010");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  
		  case "101011": {
			  setRegistroB(panelSap);
			  alu = ALU.Divide(registroB, "000000000010");
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "101100": {
			  
			  setRegistroA(panelSap);
			  alu = ALU.MODULO(registroA);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  case "101101": {
			  
			  setRegistroB(panelSap);
			  alu = ALU.MODULO(registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "101110": {

			  setRegistroA(panelSap);
			  salida = registroA;
			  actualizarSalida(panelSap);
			  actualizarMemoria(panelMemoria);
			  
			break;
			  
		  }
		  
		  case "101111": {
			  
			  setRegistroB(panelSap);
			  salida = registroB;
			  actualizarSalida(panelSap);
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "110000": {
			  
			  registroA = "000000000000";
			  actualizarRegistroA(panelSap);
			  actualizarMemoria(panelMemoria);

			break;
			  
		  }
		  case "110001": {
			  
				registroB = "000000000000";

				  actualizarRegistroB(panelSap);
				  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "110010": {
			  
				salida = "000000000000";
				actualizarSalida(panelSap);
				  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  
		  case "110011": {
			
			getRegistroA();
			actualizarRegistroA(panelSap);			
			  
			break;
			  
		  }
		    
		  case "110100": {
			  
			getRegistroB();
			actualizarRegistroB(panelSap);
			break;
			  
		  }
		  case "110101": {

			  getRegistroMemoria();
			  salida = registroMemoria;
				actualizarSalida(panelSap);
				  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		    
		  case "110110": {
			  
			setRegistroA(panelSap);
			setRegistroB(panelSap);
		  
			registroMemoria = registroA;
			registroA = registroB;
			registroB = registroMemoria;
			
			actualizarRegistroA(panelSap);		
			actualizarRegistroB(panelSap);		
			
			  
			break;
			  
		  }
		  
		  case "110111": {
				setRegistroA(panelSap);
				registroB = registroA;
				actualizarRegistroB(panelSap);
			break;
			  
		  }
		    
		  case "111000": {
			  
				setRegistroB(panelSap);
				registroA = registroB;
				actualizarRegistroA(panelSap);	
			break;
			  
		  }
		  case "111001": {
				setRegistroA(panelSap);
				alu = ALU.Par(registroA);
				  alu = formatear(alu);
				  actualizarAlu(panelSap);
				  actualizarMemoria(panelMemoria);
				  if (alu.equals(000000000010)) {
					  System.out.println("TRUE");
				  }
			break;
			  
		  }
		    
		  case "111010": {
				setRegistroB(panelSap);
				alu = ALU.Par(registroB);
				  alu = formatear(alu);
				  actualizarAlu(panelSap);
				  actualizarMemoria(panelMemoria);
				  if (alu.equals(000000000010)) {
					  System.out.println("TRUE");
				  }
			break;
			  
		  }
		  
		  case "111011": {
			  
				setRegistroA(panelSap);
				alu = ALU.Impar(registroA);
				  alu = formatear(alu);
				  actualizarAlu(panelSap);
				  actualizarMemoria(panelMemoria);
				  if (alu.equals(000000000000)) {
					  System.out.println("TRUE");
				  }
			break;
			  
		  }
		    
		  case "111100": {
			  
				setRegistroB(panelSap);
				alu = ALU.Impar(registroB);
				  alu = formatear(alu);
				  actualizarAlu(panelSap);
				  actualizarMemoria(panelMemoria);
				  if (alu.equals(000000000000)) {
					  System.out.println("TRUE");
				  }
			break;
			  
		  }
		  case "111101": {
			  
				setRegistroA(panelSap);
				setRegistroB(panelSap);
				
				alu = ALU.Mayor(registroA,registroB);
				  alu = formatear(alu);
				  actualizarAlu(panelSap);
				  actualizarMemoria(panelMemoria);
				  if (alu.equals(000000000001)) {
					  System.out.println("TRUE");
				  }
				
			break;
			  
		  }
		    
		  case "111110": {
			  
				setRegistroA(panelSap);
				setRegistroB(panelSap);
				
				alu = ALU.Menor(registroA,registroB);
				  alu = formatear(alu);
				  actualizarAlu(panelSap);
				  actualizarMemoria(panelMemoria);
				  if (alu.equals(000000000001)) {
					  System.out.println("TRUE");
				  }
			break;
			  
		  }
		  
		  case "111111": {
			  setRegistroA(panelSap);
			  setRegistroB(panelSap);
			  actualizarRegistroA(panelSap);
			  actualizarRegistroB(panelSap);
			  alu = ALU.Suma(registroA, registroB);
			  alu = formatear(alu);
			  actualizarAlu(panelSap);
			  
			  actualizarMemoria(panelMemoria);
			break;
			  
		  }
		  default:{
			  
		  }

		}
		
	}
	
	private void getRegistroMemoria() {
		registroMemoria ="";
		for (int j = 1; j < 13; j++) {
			registroMemoria = registroMemoria + memoria[caminoAux][j];
		}
	}

	private void getRegistroB() {
		registroB ="";
		for (int j = 1; j < 13; j++) {
			registroB = registroB + memoria[caminoAux][j];
		}
	}

	private void actualizarMemoria(panelMemoria panelMemoria) {
		alu = formatear(alu);
		System.out.print(alu+"\n"+alu.length());
		for (int j = 1; j < 13; j++) {
			panelMemoria.getTablaMemoria().setValueAt(alu.charAt(j-1)+"", caminoAux, j);
			memoria[caminoAux][j] = alu.charAt(j-1)+"";
			
		}
		
	}

	private String formatear(String respuesta) {
		
		while(respuesta.length() < 12) {
			respuesta = "0"+respuesta;
		}
		
		return respuesta;
	}

	private void getRegistroA() {
		registroA ="";
		for (int j = 1; j < 13; j++) {
			registroA = registroA + memoria[caminoAux][j];
		}
	}

	private void setRegistroB(panelSap panel) {
		registroB = "";
		
		for (int i = 0; i < 12; i++) {
			
			registroB = registroB + panel.getBtns_bitsB()[i].getText();
			
		}
	}

	private void setRegistroA(panelSap panel) {
		
		registroA = "";
		
		for (int i = 0; i < 12; i++) {
			
			registroA = registroA + panel.getBtns_bitsA()[i].getText();
			
		}
		
	}
	
	private void setOpcode() {
		
		opcode = "";
	
		for (int i = 0; i < 6; i++) {
			
			opcode = opcode + bus[i];
			
		}
		
	}

	private void obtenerOpcodes(String[] opcode) {
		this.opcodes = new String[opcode.length];
		
		for (int i = 0; i < opcode.length ; i++) {
			this.opcodes[i] = opcode[i].replaceAll("\\[", "").replaceAll("\\]","");
		}
		
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
		registroA = "000000000000";
		registroB = "000000000000";
		alu = "000000000000";
		salida = "000000000000";
	}

	private void actualizarSalida(panelSap panel) {
		
		for (int i = 0; i < salida.length(); i++) {
			panel.getBtns_bitsOUT()[i].setText(salida.charAt(i)+"");
		}
		
	}

	private void actualizarAlu(panelSap panel) {
		
		for (int i = 0; i < alu.length(); i++) {
			panel.getBtns_bitsALU()[i].setText(alu.charAt(i)+"");
		}
	}

	private void actualizarRegistroA(panelSap panel) {
		
		for (int i = 0; i < 12; i++) {
			panel.getBtns_bitsA()[i].setText(registroA.charAt(i)+"");
		}
	}
	
	private void actualizarRegistroB(panelSap panel) {
		
		for (int i = 0; i < registroB.length(); i++) {
			panel.getBtns_bitsB()[i].setText(registroB.charAt(i)+"");
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
