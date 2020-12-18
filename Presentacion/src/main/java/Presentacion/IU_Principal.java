package Presentacion;

import java.awt.EventQueue;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Dominio.DTOReserva;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;

public class IU_Principal implements Fuente{

	private JFrame frmFritura;
	private JPanel pnlPrincipal;
	private JLabel lblBienvenida;
	static Connection con = null;
	private JRadioButton rdbtnJefeDeSala;
	private JRadioButton rdbtnCamarero;
	private JButton btnEntrar;
	private JRadioButton rdbtnCocinero;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	//private IU_AnotarComanda iu=new IU_AnotarComanda();
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

		TimerTask timerTask = new TimerTask()
		{
			public void run() 
			{
				DTOReserva.comprobarClienteTardio();
			}
		};

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 1, 60000);

		pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(null);
		frmFritura.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);

		lblBienvenida = new JLabel("Bienvenido a la aplicación Fritura");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER); 
		lblBienvenida.setBounds(new Rectangle(134, 21, 421, 50));
		lblBienvenida.setFont(FUENTE_CAB);

		pnlPrincipal.add(lblBienvenida);

		rdbtnJefeDeSala = new JRadioButton("Jefe de Sala");
		buttonGroup.add(rdbtnJefeDeSala);
		rdbtnJefeDeSala.setBounds(234, 128, 132, 35);
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
					IU_Camarero iuCamarero = new IU_Camarero();
				}else if(rdbtnCocinero.isSelected()) {
					IU_Cocinero iuCocinero = new IU_Cocinero();
					iuCocinero.setVisible(true);
				}
			}
		});
		btnEntrar.setBounds(254, 291, 112, 35);
		pnlPrincipal.add(btnEntrar);
		
		rdbtnCocinero = new JRadioButton("Cocinero");
		buttonGroup.add(rdbtnCocinero);
		rdbtnCocinero.setFont(new Font("Verdana", Font.PLAIN, 16));
		rdbtnCocinero.setBounds(236, 222, 112, 50);
		pnlPrincipal.add(rdbtnCocinero);
	}
}