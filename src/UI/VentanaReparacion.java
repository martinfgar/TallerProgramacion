package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.Toolkit;
import System.CreadorPDF;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;


import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import System.GestorOperaciones;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Modelos.Factura;
import Modelos.Pieza;
import Modelos.Reparacion;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class VentanaReparacion extends JFrame  implements ActionListener {

	private JPanel contentPane;
	private JList listaPiezas;
	private GestorOperaciones gestor;
	private JPanel panel_izq;
	private JSpinner spinner;
	private JButton btnAnadirPieza;
	private Factura factura;
	private HashMap<Pieza,Integer> elegidos = new HashMap<Pieza,Integer>();
	private JTable tablaElegidos;
	private JPanel panelAnadir;
	private JPanel panelComentario;
	private JTextArea txtComentario;
	private JScrollPane panelElegidos;
	private JPanel panelPie;
	private JButton btnReparacion;
	private JButton btnFactura;
	private JLabel lblHoras;
	private JComboBox horasOperacion;
	/**
	 * Create the frame.
	 */
	public VentanaReparacion(GestorOperaciones gestor,Factura fact){
		this.factura= fact;
		this.gestor=gestor;

		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/Resources/imagenes/llave-inglesa.png")));	
		setTitle("     Hermanos Gutierrez S.A.");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 929, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_izq = new JPanel();
		contentPane.add(panel_izq, BorderLayout.WEST);
		panel_izq.setLayout(new BorderLayout(0, 0));
		listaPiezas = new JList(gestor.piezasEnStock().toArray());
		JScrollPane panelPiezas = new JScrollPane(listaPiezas);
		panel_izq.add(panelPiezas, BorderLayout.CENTER);
		panelPiezas.setViewportBorder(new TitledBorder(null, "Piezas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panelAnadir = new JPanel();
		panel_izq.add(panelAnadir, BorderLayout.SOUTH);
		
		spinner = new JSpinner();
		panelAnadir.add(spinner);
		
		btnAnadirPieza = new JButton("A\u00F1adir");
		panelAnadir.add(btnAnadirPieza);
		btnAnadirPieza.addActionListener(this);
		btnAnadirPieza.setActionCommand("anadir");
		
		panelComentario = new JPanel();
		panelComentario.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(panelComentario, BorderLayout.EAST);
		panelComentario.setLayout(new BoxLayout(panelComentario, BoxLayout.Y_AXIS));
		txtComentario = new JTextArea("Escriba aqui su comentario");
		panelComentario.add(txtComentario);
		
		lblHoras = new JLabel("Horas intervencion");
		lblHoras.setHorizontalAlignment(SwingConstants.CENTER);
		panelComentario.add(lblHoras);
		
		Float[] horas_T = new Float[] {0.5f,1f,1.5f,2f,2.5f,3f,3.5f,4f,4.5f,5f,5.5f,6f,6.5f,7f,7.5f,8f,8.5f,9f,9.5f,10f};
		horasOperacion = new JComboBox(horas_T);
		
		lblHoras.setLabelFor(horasOperacion);
		panelComentario.add(horasOperacion);
		
		
		
		
		tablaElegidos = new JTable(datosTabla());
		tablaElegidos.setRowSelectionAllowed(false);
		tablaElegidos.setEnabled(false);
		
		panelElegidos = new JScrollPane(tablaElegidos);
		panelElegidos.setViewportBorder(new TitledBorder(null, "Piezas utilizadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelElegidos, BorderLayout.CENTER);
		
		panelPie = new JPanel();
		contentPane.add(panelPie, BorderLayout.SOUTH);
		
		btnReparacion = new JButton("Finalizar Reparacion");
		btnReparacion.addActionListener(this);
		btnReparacion.setActionCommand("Reparacion");
		panelPie.add(btnReparacion);
		
		btnFactura = new JButton("Vehiculo Preparado");
		btnFactura.addActionListener(this);
		btnFactura.setActionCommand("Facturar");
		panelPie.add(btnFactura);
	}
	
	private DefaultTableModel datosTabla() {
		DefaultTableModel modelo = new DefaultTableModel(new String[]{"Marca","Modelo","Cantidad"},0);
		for(Entry<Pieza,Integer> linea : elegidos.entrySet()) {
			modelo.addRow(new String[] {linea.getKey().getMarca(),linea.getKey().getModelo(),Integer.toString(linea.getValue())});
		}
		return modelo;
	}
	
	@Override
	public void actionPerformed(ActionEvent click) {
		Reparacion rep = null;
		Inicio inc;
		switch(click.getActionCommand()) {
		case "anadir":
			if (((Pieza)listaPiezas.getSelectedValue()).getStock()>=(Integer)spinner.getValue())
			elegidos.put((Pieza)listaPiezas.getSelectedValue(), (Integer)spinner.getValue());
			tablaElegidos.setModel(datosTabla());
			break;
		case "Facturar":
			
			
			rep = new Reparacion(new Date(),(float)horasOperacion.getSelectedItem(), txtComentario.getText(), factura.getId(), elegidos);
			factura.getReparaciones().add(rep);
			try {
				gestor.anadirReparacion(rep);
				gestor.terminarReparacionCoche(factura);
				try {
					CreadorPDF.crearPdfFactura(factura);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Vehiculo listo para entregar.\nFactura generada en el directorio de facturas.","Exito",JOptionPane.INFORMATION_MESSAGE);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inc = new Inicio(gestor);
			inc.setVisible(true);
			dispose();
			break;
			
		case "Reparacion":
			rep = new Reparacion(new Date(),(float)horasOperacion.getSelectedItem(), txtComentario.getText(), factura.getId(), elegidos);
			factura.getReparaciones().add(rep);
			try {
				gestor.anadirReparacion(rep);
				JOptionPane.showMessageDialog(this,"Reparacion registrada con exito","Exito",JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inc = new Inicio(gestor);
			inc.setVisible(true);
			dispose();
			break;
		}
		
	}
	
}
