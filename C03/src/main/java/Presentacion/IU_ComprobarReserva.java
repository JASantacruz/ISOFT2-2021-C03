package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.*;
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

@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class IU_ComprobarReserva extends JFrame implements Fuente{

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblComprobar_Cab;
	private JLabel lblSeleccionar;
	private JComboBox cmbBxMesas;
	private JLabel lblEstado;
	private JTextField txtEstado;	
	private JButton btnCliente;
	private JLabel lblCamarero;
	private JComboBox cmbbxCamarero;
	private JLabel lblCliente;
	private JButton btnConfirmar;
	private JLabel lblResultado;

	/**
	 * Create the frame.
	 */
	public IU_ComprobarReserva(final Connection con, final LinkedList<Mesa> lista) {
		setResizable(false);
		setTitle("Fritura");
		setBounds(new Rectangle(380, 170, 700, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblComprobar_Cab = new JLabel("Sistema de comprobar reservas");
		lblComprobar_Cab.setFont(FUENTE_CAB);
		lblComprobar_Cab.setBounds(157, 43, 382, 33);
		panel.add(lblComprobar_Cab);
		
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
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setBounds(127, 218, 156, 22);
		txtEstado.setFont(FUENTE_RDBTN);
		panel.add(txtEstado);
		txtEstado.setColumns(10);
		
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
				lblResultado.setForeground(Color.green);
				lblResultado.setVisible(true);
			}
		});
		btnConfirmar.setBounds(527, 364, 92, 25);
		panel.add(btnConfirmar);
		
		lblResultado = new JLabel("New label");
		lblResultado.setVisible(false);
		lblResultado.setHorizontalAlignment(SwingConstants.LEFT);
		lblResultado.setBounds(452, 394, 224, 25);
		lblResultado.setFont(new Font("Verdana", Font.ITALIC, 13));
		panel.add(lblResultado);
		
	}
}
