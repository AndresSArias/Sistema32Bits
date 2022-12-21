package Vista;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class panelMemoria extends JPanel{
	
	//Componentes no interactivos
	private JPanel panelSuperior;
	private JLabel etiquetaMemoria;
	
	//Atributos interactivos
	private JButton botonBorrarMemoria;
	private JScrollPane scrollTablaMemoria;
	private DefaultTableModel modelo;
	private JTable tablaMemoria;
	
	//Dirrecciónes
	private String[] dirrecciones = {"[000000]",
			"[000001]",
			"[000010]",
			"[000011]",
			"[000100]",
			"[000101]",
			"[000110]",
			"[000111]",
			"[001000]",
			"[001001]",
			"[001010]",
			"[001011]",
			"[001100]",
			"[001101]",
			"[001110]",
			"[001111]",
			"[010000]",
			"[010001]",
			"[010010]",
			"[010011]",
			"[010100]",
			"[010101]",
			"[010110]",
			"[010111]",
			"[011000]",
			"[011001]",
			"[011010]",
			"[011011]",
			"[011100]",
			"[011101]",
			"[011110]",
			"[011111]",
			"[100000]",
			"[100001]",
			"[100010]",
			"[100011]",
			"[100100]",
			"[100101]",
			"[100110]",
			"[100111]",
			"[101000]",
			"[101001]",
			"[101010]",
			"[101011]",
			"[101100]",
			"[101101]",
			"[101110]",
			"[101111]",
			"[110000]",
			"[110001]",
			"[110010]",
			"[110011]",
			"[110100]",
			"[110101]",
			"[110110]",
			"[110111]",
			"[111000]",
			"[111001]",
			"[111010]",
			"[111011]",
			"[111100]",
			"[111101]",
			"[111110]",
			"[111111]"};
	
	
	public panelMemoria () {
		initComponents();
	}

	private void initComponents() {
		
		this.setBounds(0, 0, 400, 800);
		this.setLayout(new BorderLayout(0, 0));
		
		panelSuperior = new JPanel();
		this.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		etiquetaMemoria = new JLabel("                    ¡CONETENIDO DE LA MEMORIA!");
		etiquetaMemoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelSuperior.add(etiquetaMemoria, BorderLayout.SOUTH);
		
		botonBorrarMemoria = new JButton("BORRAR MEMORIA");
		panelSuperior.add(botonBorrarMemoria, BorderLayout.CENTER);


		scrollTablaMemoria = new JScrollPane();
		scrollTablaMemoria.setBounds(10, 54, 274, 255);
		this.add(scrollTablaMemoria,BorderLayout.CENTER);
		
		String [] columnNames = {"DiRECCIÓN","1","2","3","4","5","6","7","8","9","10","11","12"};
		DefaultTableModel modelo = new DefaultTableModel(columnNames,0){
        	public boolean isCellEditable (int filas, int columnas) {
        		if (columnas == 13) {
        			return true;
        		}else {
        			return false;
        		}
        	}
        };;
        
       
        
		tablaMemoria = new JTable(modelo);
		scrollTablaMemoria.setViewportView(tablaMemoria);
		establacerMemoria();
	}
	
	public void establacerMemoria() {
		
		
		String[] nombreColumna = {"DiRECCIÓN","1","2","3","4","5","6","7","8","9","10","11","12"};
		
		tablaMemoria.removeAll();
        
        DefaultTableModel modeloTabla = new DefaultTableModel() {
        	public boolean isCellEditable (int filas, int columnas) {
        		if (columnas == 13) {
        			return true;
        		}else {
        			return false;
        		}
        	}
        };
       
        modeloTabla.setColumnIdentifiers(nombreColumna);
        tablaMemoria.setModel(modeloTabla);     
        
        for (int i = 0; i < dirrecciones.length; i++) {
        
        	Object[] data = {dirrecciones[i],"0","0","0","0","0","0","0","0","0","0","0","0"};
        	modeloTabla.addRow(data);
        }
        
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER
        
        tablaMemoria.getColumnModel().getColumn(0).setPreferredWidth(230);
        tablaMemoria.getColumnModel().getColumn(0).setCellRenderer(Alinear);
        tablaMemoria.getColumnModel().getColumn(0).setResizable(false);     
        for (int i = 1; i<13; i++) {
        	tablaMemoria.getColumnModel().getColumn(i).setCellRenderer(Alinear);
            tablaMemoria.getColumnModel().getColumn(i).setResizable(false);      
        }
        
        
        
        tablaMemoria.getTableHeader().setReorderingAllowed(false) ;
        tablaMemoria.setRowSelectionAllowed(false);
       
		
	}

	public JButton getBotonBorrarMemoria() {
		return botonBorrarMemoria;
	}

	public JScrollPane getScrollTablaMemoria() {
		return scrollTablaMemoria;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTablaMemoria() {
		return tablaMemoria;
	}

	public String[] getDirrecciones() {
		return dirrecciones;
	}
	
	

}
