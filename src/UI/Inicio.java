package UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;



public class Inicio extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Container contenedor;
	JButton opcion1;
	JLabel opcion1T;
	private Inicio miInicio;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Inicio() {
		iniciarComponentes();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(00, 100, 567, 390);
		setSize(500,400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setTitle("Taller Mecanico de Aitor");
	}
	
	public void setInicio(Inicio miInicio) {
		this.miInicio=miInicio;
	}
	
	private void iniciarComponentes() {
		contenedor=getContentPane();//iniamos contenedor
		contenedor.setLayout(null);//controlamos los tamaños y posicon de los componentes
		//botones
		opcion1 = new JButton();
		opcion1.setText("Reparar coche");
		opcion1.setBounds(100, 80, 80, 23);
		opcion1.addActionListener(this);
		
		//etiquetas
		opcion1T= new JLabel();
		opcion1T.setText("Reparar???");
		opcion1T.setBounds(80,20,180,23);
		//añadimos a contenedor
		contenedor.add(opcion1);
		contenedor.add(opcion1T);
		//Evento
				
	}
	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource()==opcion1) {
			JOptionPane.showMessageDialog(null, "Funciona");
		}
	}


}
