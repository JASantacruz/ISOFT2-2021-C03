package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import Dominio.DTOCamarero;
import Dominio.Camarero;

import java.awt.Font;
import javax.swing.JComboBox;

public class IU_Camarero extends JFrame{

	private JFrame frame;
	private JButton btnCambiarEstadoMesa;
	private JButton btnAnotarComanda;
	private JButton btnEnviarComanda;
	private JLabel lblSeleccionCamarero;
	private JComboBox cBoxCamareros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Camarero window = new IU_Camarero();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IU_Camarero() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(380, 170, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnCambiarEstadoMesa = new JButton("Secuenciar Estado Mesa");
		btnCambiarEstadoMesa.addActionListener(new BtnCambiarEstadoMesaActionListener());
		btnCambiarEstadoMesa.setEnabled(false);
		btnCambiarEstadoMesa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCambiarEstadoMesa.setBounds(222, 239, 229, 31);
		frame.getContentPane().add(btnCambiarEstadoMesa);
		
		btnAnotarComanda = new JButton("Anotar Comanda");
		btnAnotarComanda.setEnabled(false);
		btnAnotarComanda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAnotarComanda.addActionListener(new BtnAnotarComandaActionListener());
		btnAnotarComanda.setBounds(33, 239, 172, 31);
		frame.getContentPane().add(btnAnotarComanda);
		
		btnEnviarComanda = new JButton("Enviar Comanda");
		btnEnviarComanda.addActionListener(new BtnEnviarComandaActionListener());
		btnEnviarComanda.setEnabled(false);
		btnEnviarComanda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEnviarComanda.setBounds(473, 239, 172, 31);
		frame.getContentPane().add(btnEnviarComanda);
		
		lblSeleccionCamarero = new JLabel("Identificación del camarero:");
		lblSeleccionCamarero.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSeleccionCamarero.setBounds(116, 143, 229, 31);
		frame.getContentPane().add(lblSeleccionCamarero);
		
		cBoxCamareros = new JComboBox();
		cBoxCamareros.addActionListener(new CBoxCamarerosActionListener());
		cBoxCamareros.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cBoxCamareros.setBounds(369, 147, 143, 22);
		frame.getContentPane().add(cBoxCamareros);
		LinkedList<Camarero> listaCamareros=new LinkedList<Camarero>();
		DTOCamarero.leerCamareros(listaCamareros);
		cBoxCamareros.addItem("   ----");
		for(Camarero camarero: listaCamareros) {
			cBoxCamareros.addItem(camarero.getNombre());
		}
	}
	private class BtnAnotarComandaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			IU_AnotarComanda iuAnotarComanda= new IU_AnotarComanda(cBoxCamareros.getSelectedItem().toString());
		}
	}
	private class CBoxCamarerosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(!cBoxCamareros.getSelectedItem().toString().equals("   ----")) {
				btnEnviarComanda.setEnabled(true);
				btnAnotarComanda.setEnabled(true);
				btnCambiarEstadoMesa.setEnabled(true);
			}
		}
	}
	private class BtnCambiarEstadoMesaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			IU_SecuenciarEstados iuSecuenciarEstado= new IU_SecuenciarEstados(cBoxCamareros.getSelectedItem().toString());
			
		}
	}
	private class BtnEnviarComandaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		}
	}
}
