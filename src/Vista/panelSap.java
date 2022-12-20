package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class panelSap extends JPanel{
	
    public static final Dimension BUTTON_SIZE = new Dimension(22, 22);
    public static final Dimension WIDGET_SIZE = new Dimension(625, 500);
    public static final Color BUTTON_UNSELECTED_BG = new Color(238, 238, 238);
    public static final Color BUTTON_SELECTED_BG = new Color(255, 85, 85);
    public static final Color COLOR_BACKGROUND = new Color(203, 246, 245);
    public static final Color WIDGET_BORDER_COLOR = Color.BLACK;
    
    private JLabel[] btns_bitsControl;
    private JLabel[] btns_bistBUS;
    private JLabel[] btns_bitsA;
	private JLabel[] btns_bitsB;
	private JLabel[] btns_bitsALU;
	private JLabel[] btns_bitsIR;
	private JLabel[] btns_bitsOUT;
	private JLabel[] btns_bitsMAR;
    
	public panelSap() {
		initComponentes();
	}

	private void initComponentes() {
		this.setBounds(0, 0, 400, 800);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        c.insets = new Insets(30, 30, 30, 0);
        

        // Encabezado
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        JLabel lblBus = new JLabel("BUS");
        this.add(lblBus, c);

        // Etiquetas de registros
        c.gridwidth = 1;
        c.gridy = 2;
        c.gridx = 0;
        JLabel lblRegA = new JLabel("Registro A");
        this.add(lblRegA, c);

        //  B
        c.gridy = 4;
        c.gridx = 0;
        JLabel lblRegB = new JLabel("Registro B");
        this.add(lblRegB, c);

        // ALU 
        c.gridy = 6;
        c.gridx = 0;
        JLabel lblALU = new JLabel("ALU");
        this.add(lblALU, c);

        // Line Break
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 12;
        this.add(new JLabel("=================================================="), c);
        c.gridwidth = 1;

        // IR 
        c.gridy = 8;
        c.gridx = 0;
        JLabel lblPC = new JLabel("Registro de Instrucción");
        this.add(lblPC, c);

        // Line Break
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 12;
        this.add(new JLabel("=================================================="), c);
        c.gridwidth = 1;

        // OUT 
        c.gridy = 12;
        c.gridx = 0;
        JLabel lblOut = new JLabel("Salida");
        this.add(lblOut, c);

        // Memory Address Register 
        c.gridy = 14;
        c.gridx = 0;
        JLabel lblMAR = new JLabel("MAR");
        this.add(lblMAR, c);



        // Lineas de Control 
        c.gridwidth = 1;
        c.gridy = 18;
        c.gridx = 0;
        


        // Prepara espacio display 
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 0);


        //  BUS 
        c.gridy = 0;
        c.gridx = 1;
        btns_bistBUS = new JLabel[12];
        for (int i = 0; i <= 11; i++) {
            c.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, c);
            btns_bistBUS[i] = b;
        }

        //  A 
        c.gridy = 2;
        c.gridx = 1;
        btns_bitsA = new JLabel[12];
        for (int i = 0; i <= 11; i++) {
            c.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, c);
            btns_bitsA[i] = b;
        }

        // B 
        c.gridy = 4;
        c.gridx = 1;
        btns_bitsB = new JLabel[12];
        for (int i = 0; i <= 11; i++) {
            c.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, c);
            btns_bitsB[i] = b;
        }

        //  ALU 
        c.gridy = 6;
        c.gridx = 1;
        btns_bitsALU = new JLabel[12];
        for (int i = 0; i <= 11; i++) {
            c.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, c);
            btns_bitsALU[i] = b;
        }

        //  IR 
        c.gridy = 8;
        c.gridx = 1;
        btns_bitsIR = new JLabel[12];
        for (int i = 0; i <= 11; i++) {
            c.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, c);
            btns_bitsIR[i] = b;
        }

        //  out 
        c.gridy = 12;
        c.gridx = 1;
        btns_bitsOUT = new JLabel[12];
        for (int i = 0; i <= 11; i++) {
            c.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, c);
            btns_bitsOUT[i] = b;
        }

        //  PC


        //  MAR
        c.gridy = 14;
        c.gridx = 1;
        btns_bitsMAR = new JLabel[6];
        for (int i = 0; i <= 5; i++) {
            c.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, c);
            btns_bitsMAR[i] = b;
        }

        this.repaint();
        
        

		
	}

    protected JLabel crearLabel(String str){
        JLabel b = new JLabel(str);
        b.setPreferredSize(BUTTON_SIZE);
        b.setBackground(BUTTON_UNSELECTED_BG);
        b.setFont(new java.awt.Font("Arial", 0, 15)); 
        b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b.setOpaque(true);
        return b;
    }

	public JLabel[] getBtns_bitsControl() {
		return btns_bitsControl;
	}

	public JLabel[] getBtns_bistBUS() {
		return btns_bistBUS;
	}

	public JLabel[] getBtns_bitsA() {
		return btns_bitsA;
	}

	public JLabel[] getBtns_bitsB() {
		return btns_bitsB;
	}

	public JLabel[] getBtns_bitsALU() {
		return btns_bitsALU;
	}

	public JLabel[] getBtns_bitsIR() {
		return btns_bitsIR;
	}

	public JLabel[] getBtns_bitsOUT() {
		return btns_bitsOUT;
	}

	public JLabel[] getBtns_bitsMAR() {
		return btns_bitsMAR;
	}

	public void setBtns_bitsControl(JLabel[] btns_bitsControl) {
		this.btns_bitsControl = btns_bitsControl;
	}

	public void setBtns_bistBUS(JLabel[] btns_bistBUS) {
		this.btns_bistBUS = btns_bistBUS;
	}

	public void setBtns_bitsA(JLabel[] btns_bitsA) {
		this.btns_bitsA = btns_bitsA;
	}

	public void setBtns_bitsB(JLabel[] btns_bitsB) {
		this.btns_bitsB = btns_bitsB;
	}

	public void setBtns_bitsALU(JLabel[] btns_bitsALU) {
		this.btns_bitsALU = btns_bitsALU;
	}

	public void setBtns_bitsIR(JLabel[] btns_bitsIR) {
		this.btns_bitsIR = btns_bitsIR;
	}

	public void setBtns_bitsOUT(JLabel[] btns_bitsOUT) {
		this.btns_bitsOUT = btns_bitsOUT;
	}

	public void setBtns_bitsMAR(JLabel[] btns_bitsMAR) {
		this.btns_bitsMAR = btns_bitsMAR;
	}
    
    
    

}
