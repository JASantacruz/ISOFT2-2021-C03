package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
<<<<<<< HEAD
=======
import java.sql.*;
>>>>>>> main
import Dominio.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
<<<<<<< HEAD
import javax.swing.DefaultComboBoxModel;
=======
>>>>>>> main

@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class IU_ComprobarReserva extends JFrame implements Fuente{

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblComprobar_Cab;
	private JLabel lblSeleccionar;
<<<<<<< HEAD
	private JComboBox cmbBxReservas;
	private JLabel lblEstado;
	private JTextField txtEstado;	
	private JLabel lblCamarero;
	private JComboBox cmbBxCamarero;
	private JLabel lblCambEstado;
	private JButton btnConfirmar;
	private JLabel lblResultado;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JComboBox cmbBxEstados;
=======
	private JComboBox cmbBxMesas;
	private JLabel lblEstado;
	private JTextField txtEstado;	
	private JButton btnCliente;
	private JLabel lblCamarero;
	private JComboBox cmbbxCamarero;
	private JLabel lblCliente;
	private JButton btnConfirmar;
	private JLabel lblResultado;
>>>>>>> main

	/**
	 * Create the frame.
	 */
<<<<<<< HEAD
	public IU_ComprobarReserva(final LinkedList<Reserva> listaReserva, final LinkedList<Camarero> listaCamarero) {
=======
	public IU_ComprobarReserva(final Connection con, final LinkedList<Mesa> lista) {
>>>>>>> main
		setResizable(false);
		setTitle("Fritura");
		setBounds(new Rectangle(380, 170, 700, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
<<<<<<< HEAD

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

=======
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
>>>>>>> main
		lblComprobar_Cab = new JLabel("Sistema de comprobar reservas");
		lblComprobar_Cab.setFont(FUENTE_CAB);
		lblComprobar_Cab.setBounds(157, 43, 382, 33);
		panel.add(lblComprobar_Cab);
<<<<<<< HEAD

		lblSeleccionar = new JLabel("Selecciona la reserva que desea comprobar");
		lblSeleccionar.setBounds(64, 142, 359, 18);
		lblSeleccionar.setFont(FUENTE_LBL);
		panel.add(lblSeleccionar);

		cmbBxReservas = new JComboBox();
		cmbBxReservas.addItem("-------------");
		for(int i = 0; i < listaReserva.size(); i++) {
			cmbBxReservas.addItem("Reserva "+listaReserva.get(i).getId());
		}
		cmbBxReservas.setBounds(78, 170, 136, 21);
		cmbBxReservas.setFont(FUENTE_LBL);


		cmbBxReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbBxReservas.getSelectedIndex()!=0) {
					String id = cmbBxReservas.getSelectedItem().toString().substring(8);
					LinkedList<Reserva> listaRes = new LinkedList<Reserva>();
					DTOReserva.leerReserva(Integer.parseInt(id), listaRes);
					txtEstado.setText(listaRes.getFirst().getEstado());
					txtNombre.setText(listaRes.getFirst().getNombre());
				}
			}
		});

		panel.add(cmbBxReservas);

		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(64, 220, 63, 21);
		lblEstado.setFont(FUENTE_LBL);
		panel.add(lblEstado);

=======
		
		lblSeleccionar = new JLabel("Selecciona la mesa que desea comprobar");
		lblSeleccionar.setBounds(28, 132, 315, 18);
		lblSeleccionar.setFont(FUENTE_LBL);
		panel.add(lblSeleccionar);
		
		cmbBxMesas = new JComboBox();
		cmbBxMesas.addItem("-------------");
		for(int i = 0; i < lista.size(); i++) {
			cmbBxMesas.addItem("Mesa "+lista.get(i).getId());
			
		}
		cmbBxMesas.setBounds(78, 170, 136, 21);
		cmbBxMesas.setFont(FUENTE_LBL);
		
		
		cmbBxMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = cmbBxMesas.getSelectedItem().toString().substring(5);
				txtEstado.setText(lista.get(Integer.parseInt(id)-1).getEstado().toString());
			}
		});
		
		panel.add(cmbBxMesas);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(54, 218, 63, 21);
		lblEstado.setFont(FUENTE_LBL);
		panel.add(lblEstado);
		
>>>>>>> main
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setBounds(127, 218, 156, 22);
		txtEstado.setFont(FUENTE_RDBTN);
		panel.add(txtEstado);
		txtEstado.setColumns(10);
<<<<<<< HEAD

		lblCamarero = new JLabel("¿Qué camarero desea asignar?");
		lblCamarero.setBounds(274, 268, 245, 33);
		lblCamarero.setFont(FUENTE_LBL);
		panel.add(lblCamarero);

		cmbBxCamarero = new JComboBox();
		cmbBxCamarero.setBounds(323, 306, 111, 21);

		cmbBxCamarero.addItem("-----");

		for(int i = 0; i < listaCamarero.size(); i++)
			cmbBxCamarero.addItem(listaCamarero.get(i).getIdCamarero()+": "+listaCamarero.get(i).getNombre());

		panel.add(cmbBxCamarero);


		lblCambEstado = new JLabel("Asigne estado:");
		lblCambEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambEstado.setFont(FUENTE_LBL);
		lblCambEstado.setBounds(64, 275, 120, 18);
		panel.add(lblCambEstado);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = cmbBxReservas.getSelectedItem().toString().substring(8);
				DTOReserva.cambiarEstado(Integer.parseInt(id), cmbBxEstados.getSelectedItem().toString());

				LinkedList<Reserva> listaReserva = new LinkedList<Reserva>();
				DTOReserva.leerReserva(Integer.parseInt(id) ,listaReserva);
				DTOMesa.asignarCamarero(listaCamarero.get(cmbBxCamarero.getSelectedIndex()-1), listaReserva.getFirst().getMesa());
				txtEstado.setText(listaReserva.getFirst().getEstado());
				lblResultado.setText("Cambios realizados con éxito.");
=======
		
		btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String id = cmbBxMesas.getSelectedItem().toString().substring(5);
				//DTOMesa.CambiarEstadoMesa(lista.get(Integer.parseInt(id)-1), "Ocupada", con); Cuando llega el cliente la establece ocupada
				
				LinkedList<Camarero> listaCamareros = new LinkedList<Camarero>();
				DTOCamarero.leerCamareros(con, listaCamareros);
				
				cmbbxCamarero.addItem("-------------");
				
				for(int i = 0; i < listaCamareros.size(); i++) 
					cmbbxCamarero.addItem(listaCamareros.get(i).getNombre());
				lblCamarero.setVisible(true);
				cmbbxCamarero.setVisible(true);
				btnConfirmar.setVisible(true);
			}
		});
		btnCliente.setBounds(101, 301, 116, 33);
		panel.add(btnCliente);
		
		lblCamarero = new JLabel("¿Qué camarero desea asignar?");
		lblCamarero.setVisible(false);
		lblCamarero.setBounds(64, 358, 245, 33);
		lblCamarero.setFont(FUENTE_LBL);
		panel.add(lblCamarero);
		
		cmbbxCamarero = new JComboBox();
		cmbbxCamarero.setVisible(false);
		cmbbxCamarero.setBounds(319, 366, 80, 21);
		panel.add(cmbbxCamarero);
		
		lblCliente = new JLabel("¿Ha llegado ya el cliente?");
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setFont(FUENTE_LBL);
		lblCliente.setBounds(28, 275, 263, 18);
		panel.add(lblCliente);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setVisible(false);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = cmbBxMesas.getSelectedItem().toString().substring(5);
				LinkedList<Camarero> listaCamareros = new LinkedList<Camarero>();
				DTOCamarero.leerCamareros(con, listaCamareros);
				DTOMesa.asignarCamarero(listaCamareros.get(cmbbxCamarero.getSelectedIndex()-1), lista.get(Integer.parseInt(id)), con);
				lblResultado.setText("Camarero asignado con éxito.");
>>>>>>> main
				lblResultado.setForeground(Color.green);
				lblResultado.setVisible(true);
			}
		});
<<<<<<< HEAD
		btnConfirmar.setBounds(274, 355, 99, 33);
		panel.add(btnConfirmar);

		lblResultado = new JLabel("New label");
		lblResultado.setVisible(false);
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(197, 398, 257, 25);
		lblResultado.setFont(new Font("Verdana", Font.ITALIC, 13));
		panel.add(lblResultado);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 16));
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(409, 216, 156, 22);
		panel.add(txtNombre);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.ITALIC, 15));
		lblNombre.setBounds(338, 218, 68, 21);
		panel.add(lblNombre);

		cmbBxEstados = new JComboBox();
		cmbBxEstados.setModel(new DefaultComboBoxModel(new String[] {"-------------", "Libre", "Reservada", "Ocupada", "Pidiendo", "En espera", "Servidos", "Esperando", "Pagando", "En preparación"}));
		cmbBxEstados.setFont(FUENTE_LBL);
		cmbBxEstados.setBounds(64, 304, 129, 21);
		panel.add(cmbBxEstados);

=======
		btnConfirmar.setBounds(527, 364, 92, 25);
		panel.add(btnConfirmar);
		
		lblResultado = new JLabel("New label");
		lblResultado.setVisible(false);
		lblResultado.setHorizontalAlignment(SwingConstants.LEFT);
		lblResultado.setBounds(452, 394, 224, 25);
		lblResultado.setFont(new Font("Verdana", Font.ITALIC, 13));
		panel.add(lblResultado);
		
>>>>>>> main
	}
}
