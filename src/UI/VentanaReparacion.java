package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import System.GestorOperaciones;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Modelos.Factura;
import Modelos.Reparacion;

public class VentanaReparacion extends JFrame {

	private JPanel contentPane;
	private JList listaPiezas;
	private GestorOperaciones gestor;
	private JPanel panel;
	private JSpinner spinner;
	private JButton btnAnadirPieza;
	private Factura factura;
	private Reparacion rep;
	/**
	 * Create the frame.
	 */
	public VentanaReparacion(GestorOperaciones gestor,Factura fact) {
		this.factura= factura;
		this.gestor=gestor;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 929, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		listaPiezas = new JList(gestor.piezasEnStock().toArray());
		JScrollPane panelPiezas = new JScrollPane(listaPiezas);
		panelPiezas.setViewportBorder(new TitledBorder(null, "Piezas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelPiezas, BorderLayout.WEST);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		spinner = new JSpinner();
		panel.add(spinner);
		
		btnAnadirPieza = new JButton("A\u00F1adir");
		panel.add(btnAnadirPieza);
	}

}
