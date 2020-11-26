package Presentacion;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import Dominio.DTOReserva;
import Dominio.Reserva;

import java.awt.Rectangle;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class IU_CancelarReserva extends JFrame implements Fuente {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblCancelar_Cab;
	private JLabel lblCancelar;
	private JButton btnCancelar;
	private JTextField txtMesa;
	private JLabel lblMesa;
	private JTextField txtFecha;
	private JLabel lblHora;
	private JLabel lblComensales;
	private JTextArea txtaComensales;
	private JComboBox cmbBxReservas;
	private JLabel lblResultado;

	private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
	/**
	 * Create the frame.
	 */
	public IU_CancelarReserva(final Connection con, final LinkedList<Reserva> lista) {
		setResizable(false);
		setTitle("Fritura");
		setBounds(new Rectangle(380, 170, 700, 500));
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblCancelar_Cab = new JLabel("Sistema de cancelar reservas");
		lblCancelar_Cab.setBounds(161, 35, 332, 33);
		lblCancelar_Cab.setFont(FUENTE_CAB);
		panel.add(lblCancelar_Cab);

		lblCancelar = new JLabel("¿Que reserva desea cancelar?");
		lblCancelar.setBounds(205, 105, 232, 33);
		lblCancelar.setFont(FUENTE_LBL);
		panel.add(lblCancelar);

		txtMesa = new JTextField();
		txtMesa.setEditable(false);
		txtMesa.setBounds(318, 148, 298, 19);
		panel.add(txtMesa);
		txtMesa.setColumns(10);

		lblMesa = new JLabel("Mesa:");
		lblMesa.setBounds(255, 149, 53, 13);
		lblMesa.setFont(FUENTE_LBL);
		panel.add(lblMesa);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(318, 196, 298, 19);
		panel.add(txtFecha);

		lblHora = new JLabel("Fecha:");
		lblHora.setFont(FUENTE_LBL);
		lblHora.setBounds(252, 197, 56, 13);
		panel.add(lblHora);

		lblComensales = new JLabel("Comensales:");
		lblComensales.setFont(FUENTE_LBL);
		lblComensales.setBounds(210, 257, 98, 19);
		panel.add(lblComensales);

		txtaComensales = new JTextArea();
		txtaComensales.setEditable(false);
		txtaComensales.setBounds(318, 256, 298, 56);
		txtaComensales.setFont(FUENTE_LBL);
		panel.add(txtaComensales);

		cmbBxReservas = new JComboBox();
		cmbBxReservas.addItem("-------------");
		for(int i = 0; i < lista.size(); i++) 
			cmbBxReservas.addItem("Reserva "+lista.get(i).getId());

		cmbBxReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbBxReservas.getSelectedIndex()!=0) {
					txtMesa.setText(Integer.toString(lista.get(cmbBxReservas.getSelectedIndex()-1).getMesa()));
					txtFecha.setText(df.format(lista.get(cmbBxReservas.getSelectedIndex()-1).getFecha()));
					txtaComensales.setText(Integer.toString(lista.get(cmbBxReservas.getSelectedIndex()-1).getNumComensales()));
				}else {
					txtMesa.setText("");
					txtFecha.setText("");
					txtaComensales.setText("");
				}
			}
		});
		cmbBxReservas.setFont(FUENTE_LBL);
		cmbBxReservas.setBounds(50, 148, 123, 19);
		panel.add(cmbBxReservas);

		lblResultado = new JLabel("Resultado");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setVisible(false);
		lblResultado.setBounds(181, 416, 278, 27);
		lblResultado.setFont(FUENTE_LBL);
		panel.add(lblResultado);


		btnCancelar = new JButton("Cancelar reserva");
		btnCancelar.setBounds(252, 367, 123, 28);
		btnCancelar.setFont(FUENTE_BTN);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = cmbBxReservas.getSelectedItem().toString().substring(8);
				DTOReserva.borrarReserva(id, con);
				cmbBxReservas.removeItemAt(cmbBxReservas.getSelectedIndex());
				lblResultado.setText("Reserva cancelada con exito.");
				lblResultado.setForeground(Color.green);
				lblResultado.setVisible(true);
			}
		});
		panel.add(btnCancelar);

	}
}
