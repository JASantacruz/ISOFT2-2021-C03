package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Dominio.DTOCamarero;
import Dominio.DTOMesa;
import Dominio.DTOReserva;
import Dominio.Mesa;
import Dominio.Camarero;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;

public class IU_SecuenciarEstados {

	private JFrame frame;
	private JLabel lblSeleccionMesa;
	private JComboBox cBoxMesas;
	private JLabel lblEstadoActual;
	private JLabel lblEstado;
	private JLabel lblTitulo;
	private JButton btnSecuenciarEstado;
	private JLabel lblInfoNuevoEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_SecuenciarEstados window = new IU_SecuenciarEstados(null);
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
	public IU_SecuenciarEstados(String camarero) {
		initialize(camarero);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String camarero) {
		frame = new JFrame();
		frame.setBounds(380, 170, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		cBoxMesas = new JComboBox();
//		LinkedList<Camarero> listaCamareros=new LinkedList<Camarero>();
//		DTOCamarero.leerCamareros(listaCamareros);
//		for(Camarero camareroAux: listaCamareros) {
//			cBoxCamareros.addItem(camareroAux.getNombre());
//		}

		lblSeleccionMesa = new JLabel("Seleccione la mesa:");
		lblSeleccionMesa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSeleccionMesa.setBounds(175, 150, 155, 43);
		frame.getContentPane().add(lblSeleccionMesa);

		cBoxMesas.addActionListener(new CBoxMesasActionListener());
		cBoxMesas.setEnabled(false);
		cBoxMesas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cBoxMesas.setBounds(385, 160, 122, 22);
		frame.getContentPane().add(cBoxMesas);
		cBoxMesas.addItem("   ----");
		cBoxMesas.setEnabled(true);
//		int tamanio=cBoxMesas.getModel().getSize();
//		for(int i=1;i<tamanio;i++) {
//			cBoxMesas.removeItemAt(1);
//		}
		LinkedList<Mesa> listaMesas=new LinkedList<Mesa>();
		DTOMesa.leerMesasPorCamarero(listaMesas, camarero);
		for(Mesa mesa: listaMesas) {
			cBoxMesas.addItem(mesa.getId());

		}

		DTOMesa.actualizarEstadoMesasPorTurno();

		lblEstadoActual = new JLabel("Estado actual de la mesa:");
		lblEstadoActual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstadoActual.setBounds(126, 231, 203, 43);
		frame.getContentPane().add(lblEstadoActual);

		lblEstado = new JLabel();
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstado.setBounds(385, 231, 271, 43);
		frame.getContentPane().add(lblEstado);

		lblTitulo = new JLabel("Secuenciador de estados");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitulo.setBounds(200, 31, 263, 57);
		frame.getContentPane().add(lblTitulo);

		btnSecuenciarEstado = new JButton("Secuenciar estado");
		btnSecuenciarEstado.addActionListener(new BtnSecuenciarEstadoActionListener());
		btnSecuenciarEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSecuenciarEstado.setBounds(242, 317, 203, 36);
		frame.getContentPane().add(btnSecuenciarEstado);
		lblInfoNuevoEstado = new JLabel();
		lblInfoNuevoEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInfoNuevoEstado.setVisible(false);
		lblInfoNuevoEstado.setBounds(176, 395, 480, 22);
		frame.getContentPane().add(lblInfoNuevoEstado);
	}

	private class CBoxMesasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(!cBoxMesas.getSelectedItem().toString().equals("   ----")) {
				String estadoMesa=DTOMesa.consultarEstadoMesa(cBoxMesas.getSelectedItem().toString());
				lblEstado.setText(estadoMesa);
			}
		}
	}
	private class BtnSecuenciarEstadoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String estadoSiguiente=DTOMesa.secuenciarEstado(lblEstado.getText());
			if(estadoSiguiente.equals("reservada") || estadoSiguiente.equals("ocupada")) {
				lblInfoNuevoEstado.setVisible(true);
				lblInfoNuevoEstado.setText("No puede secuenciar al siguiente estado");
			}else {
				DTOReserva.cambiarTiempoEstado(cBoxMesas.getSelectedItem().toString(),estadoSiguiente);
				int resultadoUpdate=DTOMesa.cambiarEstado(cBoxMesas.getSelectedItem().toString(), estadoSiguiente);
				if(resultadoUpdate==0) {
					lblInfoNuevoEstado.setVisible(true);
					lblInfoNuevoEstado.setText("Error en la actualizacion del estado");
				}else {
					lblInfoNuevoEstado.setVisible(true);
					lblInfoNuevoEstado.setText("La mesa "+cBoxMesas.getSelectedItem().toString()+" ha "
							+ "pasado al estado: "+estadoSiguiente);
				}
			}
		}
	}
}
