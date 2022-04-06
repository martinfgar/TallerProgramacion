package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.ConexionDB;
import System.GestorOperaciones;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("     Acceso Taller Hermanos Gutierrez S.A.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/Resources/llave-inglesa.png")));	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton botonLogin = new JButton("Acceder");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 6;
		botonLogin.addActionListener(this);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 5;
		contentPane.add(passwordField, gbc_passwordField);
		contentPane.add(botonLogin, gbc_btnNewButton);
		
		JLabel lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblUsuario, gbc_lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		contentPane.add(txtUsuario, gbc_textField);
		txtUsuario.setColumns(10);
		
		JLabel lblContra = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblContra, gbc_lblNewLabel_1);
	}
	
	@Override
	public void actionPerformed(ActionEvent click) {
		String usuario = txtUsuario.getText();
		String passwd = passwordField.getText();
		if(new ConexionDB().conectarOracle(usuario,passwd)!=null) {
			try {
				GestorOperaciones gestor = new GestorOperaciones(usuario,passwd);
				/*Inicio programa = new Inicio();*/
				ElegirVehiculoReparar programa = new ElegirVehiculoReparar(gestor);
				programa.setVisible(true);
				this.dispose();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Contrasenia incorrecta", "Liadon", JOptionPane.ERROR_MESSAGE);
		} 
	}

}
