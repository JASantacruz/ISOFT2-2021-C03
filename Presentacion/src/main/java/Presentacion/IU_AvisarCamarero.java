package Presentacion;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Dominio.Camarero;
import Dominio.DTOCamarero;
import Dominio.DTOComanda;
import Dominio.DTOMesa;
import Dominio.Mesa;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class IU_AvisarCamarero {

	private JFrame frame;
	private JLabel lblAvisarCamarero;
	private JLabel lblSelecciona;
	private JComboBox cBoxCamareros;
	private JLabel lblSeleccionaMesa;
	private JComboBox cBoxMesas;
	private JButton btnConfirmar;
	private JLabel lblConfirmacion;
	private String tipoEmpleado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_AvisarCamarero window = new IU_AvisarCamarero("");
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
	public IU_AvisarCamarero(String tipoEmpleado) {
		initialize(tipoEmpleado);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String tipoEmpleado) {
		frame = new JFrame();
		frame.setBounds(new Rectangle(380, 170, 700, 500));
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		this.tipoEmpleado=tipoEmpleado;

		lblAvisarCamarero = new JLabel("Avisar a camarero");
		lblAvisarCamarero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvisarCamarero.setBounds(246, 64, 174, 33);
		frame.getContentPane().add(lblAvisarCamarero);

		lblSelecciona = new JLabel("Selecciona el camarero a avisar:");
		lblSelecciona.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelecciona.setBounds(109, 166, 203, 17);
		frame.getContentPane().add(lblSelecciona);

		cBoxCamareros = new JComboBox();
		cBoxCamareros.addActionListener(new CBoxCamarerosActionListener());
		cBoxCamareros.setBounds(359, 165, 98, 22);
		frame.getContentPane().add(cBoxCamareros);
		LinkedList<Camarero> listaCamareros=new LinkedList<Camarero>();
		DTOCamarero.leerCamareros(listaCamareros);
		cBoxCamareros.addItem("   ----");

		lblSeleccionaMesa = new JLabel("Selecciona la mesa:");
		lblSeleccionaMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccionaMesa.setBounds(189, 243, 123, 17);
		frame.getContentPane().add(lblSeleccionaMesa);

		cBoxMesas = new JComboBox();
		cBoxMesas.addActionListener(new CBoxMesasActionListener());
		cBoxMesas.setEnabled(false);
		cBoxMesas.setBounds(359, 242, 98, 22);
		frame.getContentPane().add(cBoxMesas);

		btnConfirmar = new JButton("Confirmar preparacion");
		btnConfirmar.addActionListener(new BtnConfirmarActionListener());
		btnConfirmar.setEnabled(false);
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmar.setBounds(246, 314, 173, 23);
		frame.getContentPane().add(btnConfirmar);

		lblConfirmacion = new JLabel("Aviso enviado correctamente");
		lblConfirmacion.setVisible(false);
		lblConfirmacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmacion.setBounds(247, 370, 187, 22);
		frame.getContentPane().add(lblConfirmacion);
		for(Camarero camarero: listaCamareros) {
			cBoxCamareros.addItem(camarero.getNombre());
		}
	}
	private class CBoxCamarerosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(cBoxCamareros.getSelectedIndex()!=0) {
				lblConfirmacion.setVisible(false);
				cBoxMesas.setEnabled(true);
				cBoxMesas.removeAllItems();
				LinkedList<Mesa> listaMesas=new LinkedList<Mesa>();
				DTOMesa.leerMesasPorCamarero(listaMesas, cBoxCamareros.getSelectedItem().toString());
				cBoxMesas.addItem("   ----");
				for(Mesa mesa: listaMesas) {
					cBoxMesas.addItem(mesa.getId());
				}
			}
		}
	}
	private class CBoxMesasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(cBoxMesas.getSelectedIndex()!=0) {
				lblConfirmacion.setVisible(false);
				btnConfirmar.setEnabled(true);
			}
		}
	}
	private class BtnConfirmarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(tipoEmpleado.equals("Cocinero")) {
				DTOComanda.subirAvisoCocina(cBoxMesas.getSelectedItem().toString(), 
						cBoxCamareros.getSelectedItem().toString());
				lblConfirmacion.setVisible(true);
			}else {
				DTOComanda.subirAvisoCamareroBarra(cBoxMesas.getSelectedItem().toString(), 
						cBoxCamareros.getSelectedItem().toString());
				lblConfirmacion.setVisible(true);
			}
		}
	}
}
