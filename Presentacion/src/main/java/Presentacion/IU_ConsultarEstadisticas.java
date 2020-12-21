package Presentacion;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Dominio.DTOEstadistica;
import Dominio.DTOMesa;
import Dominio.Mesa;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class IU_ConsultarEstadisticas {

	private JFrame frmFritura;
	private JLabel lblNumComensales;
	private JLabel lblNewLabel;
	private JComboBox cBoxNComensales;
	private JLabel lblSeleccioneElRestaurante;
	private JComboBox cBoxRestCiudad;
	private JButton btnConsultarEstadisticas;
	private JLabel lblTTomaComandas;
	private JLabel lblTiempoDe;
	private JLabel lblTiempoDeEntrega;
	private JLabel lblTiempoDePreparacion;
	private JRadioButton rdBtnRestaurante;
	private JRadioButton rdBtnCiudad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_ConsultarEstadisticas window = new IU_ConsultarEstadisticas();
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
	public IU_ConsultarEstadisticas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFritura = new JFrame();
		frmFritura.setResizable(false);
		frmFritura.setTitle("Fritura");
		frmFritura.setBounds(100, 100, 450, 300);
		frmFritura.setBounds(new Rectangle(380, 170, 700, 500));
		frmFritura.getContentPane().setLayout(null);
		
		lblNumComensales = new JLabel("Seleccione el numero de comensales: ");
		lblNumComensales.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumComensales.setBounds(99, 233, 239, 17);
		frmFritura.getContentPane().add(lblNumComensales);
		
		lblNewLabel = new JLabel("Consultar estadisticas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(220, 52, 204, 25);
		frmFritura.getContentPane().add(lblNewLabel);
		
		cBoxNComensales = new JComboBox();
		cBoxNComensales.setBounds(368, 232, 83, 22);
		frmFritura.getContentPane().add(cBoxNComensales);
		cBoxNComensales.addItem("2");
		cBoxNComensales.addItem("4");
		cBoxNComensales.addItem("6");
		
		lblSeleccioneElRestaurante = new JLabel("Seleccione opcion: ");
		lblSeleccioneElRestaurante.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccioneElRestaurante.setBounds(126, 142, 128, 17);
		frmFritura.getContentPane().add(lblSeleccioneElRestaurante);
		
		cBoxRestCiudad = new JComboBox();
		cBoxRestCiudad.setBounds(368, 184, 71, 22);
		frmFritura.getContentPane().add(cBoxRestCiudad);
		
		btnConsultarEstadisticas = new JButton("Consultar estadisticas");
		btnConsultarEstadisticas.addActionListener(new BtnConsultarEstadisticasActionListener());
		btnConsultarEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConsultarEstadisticas.setBounds(234, 301, 187, 23);
		frmFritura.getContentPane().add(btnConsultarEstadisticas);
		
		lblTTomaComandas = new JLabel("Tiempo de toma de comandas:");
		lblTTomaComandas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTTomaComandas.setBounds(10, 360, 313, 14);
		frmFritura.getContentPane().add(lblTTomaComandas);
		
		lblTiempoDe = new JLabel("Tiempo de preparacion de comidas:");
		lblTiempoDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTiempoDe.setBounds(347, 360, 329, 14);
		frmFritura.getContentPane().add(lblTiempoDe);
		
		lblTiempoDeEntrega = new JLabel("Tiempo de entrega de nota:");
		lblTiempoDeEntrega.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTiempoDeEntrega.setBounds(10, 411, 313, 14);
		frmFritura.getContentPane().add(lblTiempoDeEntrega);
		
		lblTiempoDePreparacion = new JLabel("Tiempo de preparacion de mesa:");
		lblTiempoDePreparacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTiempoDePreparacion.setBounds(347, 411, 329, 14);
		frmFritura.getContentPane().add(lblTiempoDePreparacion);
		
		rdBtnRestaurante = new JRadioButton("Restaurante");
		rdBtnRestaurante.addActionListener(new RdBtnRestauranteActionListener());
		rdBtnRestaurante.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdBtnRestaurante.setBounds(287, 139, 111, 23);
		frmFritura.getContentPane().add(rdBtnRestaurante);
		
		rdBtnCiudad = new JRadioButton("Ciudad");
		rdBtnCiudad.addActionListener(new RdBtnCiudadActionListener());
		rdBtnCiudad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdBtnCiudad.setBounds(413, 139, 111, 23);
		frmFritura.getContentPane().add(rdBtnCiudad);
	}
	private class BtnConsultarEstadisticasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(rdBtnCiudad.isSelected()) {
				lblTTomaComandas.setText("Tiempo de toma de comandas: "
						+ ""+DTOEstadistica.calcularTiempoTomaComandasCiudad(cBoxNComensales.getSelectedItem().toString(),cBoxRestCiudad.getSelectedItem().toString())+""
								+ " minutos");
				lblTiempoDe.setText("Tiempo de preparacion de comidas: "
						+ ""+DTOEstadistica.calcularTiempoPreparacionComandasCiudad(cBoxNComensales.getSelectedItem().toString(),cBoxRestCiudad.getSelectedItem().toString())+""
								+ " minutos");
				lblTiempoDeEntrega.setText("Tiempo de entrega de nota: "
						+ ""+DTOEstadistica.calcularTiempoEntregaNotaCiudad(cBoxNComensales.getSelectedItem().toString(),cBoxRestCiudad.getSelectedItem().toString())+""
								+ " minutos");
				lblTiempoDePreparacion.setText("Tiempo de preparacion de mesa: "
						+ ""+DTOEstadistica.calcularTiempoPreparacionMesaLibreCiudad(cBoxNComensales.getSelectedItem().toString(),cBoxRestCiudad.getSelectedItem().toString())+""
								+ " minutos");
			}else {
				lblTTomaComandas.setText("Tiempo de toma de comandas: "
						+ ""+DTOEstadistica.calcularTiempoTomaComandasRestaurante(cBoxNComensales.getSelectedItem().toString(),cBoxRestCiudad.getSelectedItem().toString())+""
								+ " minutos");
				lblTiempoDe.setText("Tiempo de preparacion de comidas: "
						+ ""+DTOEstadistica.calcularTiempoPreparacionComandasRestaurante(cBoxNComensales.getSelectedItem().toString(),cBoxRestCiudad.getSelectedItem().toString())+""
								+ " minutos");
				lblTiempoDeEntrega.setText("Tiempo de entrega de nota: "
						+ ""+DTOEstadistica.calcularTiempoEntregaNotaRestaurante(cBoxNComensales.getSelectedItem().toString(),cBoxRestCiudad.getSelectedItem().toString())+""
								+ " minutos");
				lblTiempoDePreparacion.setText("Tiempo de preparacion de mesa: "
						+ ""+DTOEstadistica.calcularTiempoPreparacionMesaLibreRestaurante(cBoxNComensales.getSelectedItem().toString(),cBoxRestCiudad.getSelectedItem().toString())+""
								+ " minutos");
			}
		}
	}
	private class RdBtnRestauranteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			rdBtnCiudad.setSelected(false);
			cBoxRestCiudad.removeAllItems();
			cBoxRestCiudad.setEnabled(true);
			LinkedList<String> listaRestaurantes=new LinkedList<String>();
			DTOEstadistica.leerRestaurantes(listaRestaurantes);
			cBoxRestCiudad.addItem("   ----");
			for(String rest: listaRestaurantes) {
				cBoxRestCiudad.addItem(rest);
			}
		}
	}
	private class RdBtnCiudadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			rdBtnRestaurante.setSelected(false);
			cBoxRestCiudad.removeAllItems();
			cBoxRestCiudad.setEnabled(true);
			LinkedList<String> listaCiudades=new LinkedList<String>();
			DTOEstadistica.leerCiudades(listaCiudades);
			cBoxRestCiudad.addItem("   ----");
			for(String ciudad: listaCiudades) {
				cBoxRestCiudad.addItem(ciudad);
			}
		}
	}
}
