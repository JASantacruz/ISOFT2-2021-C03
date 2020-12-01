package Presentacion;

import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class IU_Principal implements Fuente{

	private JFrame frmFritura;
	private JPanel pnlPrincipal;
	private JLabel lblBienvenida;
	public static JLabel lblConexion;
	static Connection con = null;
	private JRadioButton rdbtnJefeDeSala;
	private JRadioButton rdbtnCamarero;
	private JButton btnEntrar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Principal window = new IU_Principal();
					window.frmFritura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IU_Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFritura = new JFrame();
		frmFritura.setTitle("Fritura");
		frmFritura.setLocationRelativeTo(null);
		frmFritura.setResizable(false);
		frmFritura.setBounds(new Rectangle(380, 170, 700, 500));

		frmFritura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(null);
		frmFritura.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);

		lblBienvenida = new JLabel("Bienvenido a la aplicación Fritura");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER); 
		lblBienvenida.setBounds(new Rectangle(134, 21, 421, 50));
		lblBienvenida.setFont(FUENTE_CAB);

		pnlPrincipal.add(lblBienvenida);

		lblConexion = new JLabel("Conexion");
		lblConexion.setVisible(false);
		lblConexion.setFont(FUENTE_LBL);
		lblConexion.setBounds(175, 400, 79, 21);
		pnlPrincipal.add(lblConexion);

		rdbtnJefeDeSala = new JRadioButton("Jefe de Sala");
		buttonGroup.add(rdbtnJefeDeSala);
		rdbtnJefeDeSala.setBounds(235, 117, 132, 35);
		rdbtnJefeDeSala.setFont(FUENTE_RDBTN);
		pnlPrincipal.add(rdbtnJefeDeSala);

		rdbtnCamarero = new JRadioButton("Camarero");
		buttonGroup.add(rdbtnCamarero);
		rdbtnCamarero.setFont(FUENTE_RDBTN);
		rdbtnCamarero.setBounds(235, 169, 112, 50);
		pnlPrincipal.add(rdbtnCamarero);

		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnJefeDeSala.isSelected()) {
					IU_Bienvenida_JefeSala bienjs = new IU_Bienvenida_JefeSala();
					bienjs.setVisible(true);
				}else if(rdbtnCamarero.isSelected()) {
					//TODO 
				}
			}
		});
		btnEntrar.setBounds(254, 254, 112, 35);
		pnlPrincipal.add(btnEntrar);
	}
}