package UI;
import javax.swing.*;

import System.GestorOperaciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Inicio extends JFrame implements ActionListener{
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btnSalir ;
	private GestorOperaciones gestor;
	
	private JLabel etiqueta;

	public Inicio(GestorOperaciones gestor) {
		this.gestor=gestor;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/Resources/imagenes/llave-inglesa.png")));	
		setTitle("     Hermanos Gutierrez S.A.");
		setSize(500, 400);
		setLocationRelativeTo(null);
		iniciarComponenetes();		
		
	}
	private void iniciarComponenetes() {
		getContentPane().setLayout(null);		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 361);
		getContentPane().add(panel);
		panel.setLayout(null);
	//PanelFondo p = new PanelFondo("/PruebasSwing/spanner-ga1b5b64da_1280.jpg");
		//panel.add(p);
		
		/*----------*/
	
		btn1 = new JButton();
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn1.setBounds(53, 109, 157, 29);
		
		btn1.setBackground(Color.WHITE);
		btn1.setText("Reparar un vehiculo");
		btn1.addActionListener(this);///añadir mas
		panel.add(btn1);
		
		btn2 = new JButton();
		btn2.setText("Registrar vehiculo");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn2.setBackground(Color.WHITE);
		btn2.setBounds(53, 149, 157, 29);
		btn2.addActionListener(this);///añadir mas
		panel.add(btn2);
		
		btn3 = new JButton();
		btn3.setText("Ver Facturas");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn3.setBackground(Color.WHITE);
		btn3.setBounds(53, 189, 157, 29);
		btn3.addActionListener(this);///añadir mas
		panel.add(btn3);
		
		btn4 = new JButton();
		btn4.setText("Pedir Piezas");
		btn4.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btn4.setBackground(Color.WHITE);
		btn4.setBounds(53, 229, 157, 29);
		btn4.addActionListener(this);///añadir mas
		panel.add(btn4);
		
		btn5 = new JButton();
		btn5.setText("Registrar Cliente");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn5.setBackground(Color.WHITE);
		btn5.setBounds(53, 69, 157, 29);
		btn5.addActionListener(this);///añadir mas
		panel.add(btn5);
		
		
		btnSalir = new JButton();
		btnSalir.setText("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(380, 332, 104, 29);
		btnSalir.addActionListener(this);///añadir mas
		panel.add(btnSalir);
		
		/*-----------*/
		etiqueta= new JLabel("");
		etiqueta.setBounds(39, 0, 388, 65);
		panel.add(etiqueta);
		etiqueta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Reparar un vehiculo" :
			etiqueta.setText("Estamos trabajando en ello...");
			ElegirVehiculoReparar nuevo = new ElegirVehiculoReparar(gestor);
			nuevo.setVisible(true);
			this.dispose();
			break;
			
		case "Registrar vehiculo" :
			etiqueta.setText("Opcion todavia no disponible");
			break;
		
			
		case "Ver Facturas" :
			etiqueta.setText("Opcion todavia no disponible");
			break;
		
			
		case "Pedir Piezas" :
			etiqueta.setText("Opcion todavia no disponible");
			break;
		
			
		case "Registrar Cliente" :
			etiqueta.setText("Opcion todavia no disponible");
			break;
		
			
		case "Salir" :
			etiqueta.setText("Hasta pronto!!");
			System.exit(WIDTH);
			
			break;
		
		
		
		
		
		}
	}
}