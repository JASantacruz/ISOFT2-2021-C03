package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dominio.DTOPrevision;
import Dominio.Prevision;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class IU_Cocinero extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Cocinero frame = new IU_Cocinero();
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
	public IU_Cocinero() {
		setTitle("Fritura");
		setResizable(false);
		setBounds(new Rectangle(380, 170, 700, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAlmacen = new JButton("Comprobar Almacen");
		btnAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<Prevision> historial = new LinkedList<>();
				DTOPrevision.comprobarFecha(historial);
				
				IU_Almacen alm = new IU_Almacen(historial);
				alm.setVisible(true);
			}
		});
		btnAlmacen.setFont(new Font("Verdana", Font.PLAIN, 10));
		btnAlmacen.setBounds(130, 185, 154, 30);
		contentPane.add(btnAlmacen);
		
		JLabel lblBienvenida = new JLabel("Bienvenido al sistema Fritura");
		lblBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		lblBienvenida.setBounds(157, 56, 357, 33);
		contentPane.add(lblBienvenida);
		
		JButton btnAvisar = new JButton("Avisar camarero");
		btnAvisar.addActionListener(new BtnAvisarActionListener());
		btnAvisar.setFont(new Font("Verdana", Font.PLAIN, 10));
		btnAvisar.setBounds(377, 185, 137, 30);
		contentPane.add(btnAvisar);
	}
	private class BtnAvisarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			IU_AvisarCamarero iuAvisar= new IU_AvisarCamarero("Cocinero");
		}
	}
}
