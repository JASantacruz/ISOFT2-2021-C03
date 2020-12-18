package Presentacion;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dominio.Ingrediente;
import Dominio.Prevision;
import Dominio.DTOPrevision;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IU_Almacen extends JFrame implements Fuente{

	private JPanel contentPane;
	DefaultListModel modeloAlimentos;
	private JTextField txtCantidad;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	/**
	 * Create the frame.
	 */
	public IU_Almacen(LinkedList<Prevision> historial) {
		setResizable(false);
		setTitle("Fritura");
		setBounds(new Rectangle(380, 170, 700, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("Sistema de Almacenamiento");
		lblTitulo.setBounds(183, 34, 329, 38);
		lblTitulo.setFont(Fuente.FUENTE_CAB);
		contentPane.add(lblTitulo);
		
		final JList lstAlimentos = new JList();
		lstAlimentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int stock = DTOPrevision.leerStock(lstAlimentos.getSelectedValue().toString());
				txtCantidad.setText(String.valueOf(stock));
			}
		});
		lstAlimentos.setBounds(74, 138, 167, 238);
		contentPane.add(lstAlimentos);
		modeloAlimentos = new DefaultListModel();

		LinkedList<Ingrediente> lista = new LinkedList<>(); 
		DTOPrevision.leerIngredientes(lista);
		
		for(int i = 0; i < lista.size(); i++)
				modeloAlimentos.addElement(lista.get(i).getNombre());
		lstAlimentos.setModel(modeloAlimentos);
		JLabel lblIngredientes = new JLabel("Ingredientes:");
		lblIngredientes.setBounds(86, 108, 117, 20);
		lblIngredientes.setFont(FUENTE_LBL);
		contentPane.add(lblIngredientes);
		
		JLabel lblCantidad = new JLabel("Cantidad en stock: ");
		lblCantidad.setBounds(298, 109, 158, 19);
		lblCantidad.setFont(FUENTE_LBL);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setEditable(false);
		txtCantidad.setBounds(308, 136, 113, 19);
		contentPane.add(txtCantidad);
		txtCantidad.setFont(FUENTE_LBL);
		txtCantidad.setColumns(10);
		
		JButton btnPrevision = new JButton("Hacer prevision");
		btnPrevision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IU_HacerPrevision iu_pre = new IU_HacerPrevision();
				iu_pre.setVisible(true);
			}
		});
		btnPrevision.setBounds(362, 366, 117, 38);
		btnPrevision.setFont(Fuente.FUENTE_BTN);
		contentPane.add(btnPrevision);
		
		for(int i = 0; i < historial.size(); i++) {
			String fecha = dtf.format(historial.get(i).getFecha());
			JOptionPane.showMessageDialog(null, "La prevision de alimentos del día "+fecha+" ha caducado y ha sido retirada del almacen.", "Comida caducada" ,JOptionPane.WARNING_MESSAGE);
		}
	}
}
