package Presentacion;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import Dominio.DTOMesa;
import Dominio.Mesa;
import Dominio.Alimento;
import Dominio.Bebida;
import Dominio.Cuenta;
import Dominio.DTOAlimento;
import Dominio.DTOCuenta;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class IU_MostrarCuenta {

	private JFrame frmFritura;
	private JLabel lblCuentaTotal;
	private JLabel lblMesa;
	private JComboBox cBoxMesas;
	private JScrollPane scrollPane;
	private JLabel lblTotalCuenta;
	private JLabel lblPrecio;
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JScrollPane scrollPane_2 = new JScrollPane();
	private final JScrollPane scrollPane_3 = new JScrollPane();
	private final JScrollPane scrollPane_4 = new JScrollPane();
	private final JLabel lblbebida = new JLabel("Bebida");
	private final JLabel lblentrantes = new JLabel("Entrantes");
	private final JLabel lblpPrimero = new JLabel("Primer Plato");
	private final JLabel lblsPlato = new JLabel("Segundo Plato");
	private final JLabel lblNewLabel = new JLabel("Postres");
	Cuenta cuenta;
	DefaultListModel modeloBebidas;
	DefaultListModel modeloEntrantes;
	DefaultListModel modeloPrimerPlato;
	DefaultListModel modeloSegundoPlato;
	DefaultListModel modeloPostre;
	private JList listBebida;
	private JList listEntrante;
	private JList listPrimerPlato;
	private JList listSegundoPlato;
	private JList listPostre;
	private JButton btnPagar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_MostrarCuenta window = new IU_MostrarCuenta(null);
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
	public IU_MostrarCuenta(String camarero) {
		initialize(camarero);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String camarero) {
		frmFritura = new JFrame();
		frmFritura.setTitle("Fritura");
		frmFritura.setBounds(new Rectangle(380, 170, 700, 500));
		frmFritura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFritura.getContentPane().setLayout(null);
		frmFritura.setVisible(true);
		
		btnPagar = new JButton("Pagar");
		btnPagar.setEnabled(false);
		btnPagar.addActionListener(new BtnPagarActionListener());
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPagar.setBounds(570, 414, 89, 23);
		frmFritura.getContentPane().add(btnPagar);

		lblCuentaTotal = new JLabel("Cuenta total");
		lblCuentaTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCuentaTotal.setBounds(248, 33, 110, 14);
		frmFritura.getContentPane().add(lblCuentaTotal);

		lblMesa = new JLabel("Seleccione mesa:");
		lblMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMesa.setBounds(12, 94, 123, 14);
		frmFritura.getContentPane().add(lblMesa);

		cBoxMesas = new JComboBox();
		cBoxMesas.addActionListener(new CBoxMesasActionListener());
		cBoxMesas.setBounds(174, 91, 93, 22);
		frmFritura.getContentPane().add(cBoxMesas);
		LinkedList<Mesa> listaMesas=new LinkedList<Mesa>();
		DTOMesa.leerMesasPorCamarero(listaMesas, camarero);
		cBoxMesas.addItem("   ----");
		for(Mesa mesa: listaMesas) {
			cBoxMesas.addItem(mesa.getId());
		}

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 183, 93, 190);
		frmFritura.getContentPane().add(scrollPane);

		listBebida = new JList();
		listBebida.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listBebida);

		lblTotalCuenta = new JLabel("Total a pagar:");
		lblTotalCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalCuenta.setBounds(12, 412, 93, 22);
		frmFritura.getContentPane().add(lblTotalCuenta);

		lblPrecio = new JLabel("");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio.setBounds(136, 411, 68, 24);
		frmFritura.getContentPane().add(lblPrecio);
		{
			scrollPane_1.setBounds(141, 183, 102, 190);
			frmFritura.getContentPane().add(scrollPane_1);
		}

		listEntrante = new JList();
		scrollPane_1.setViewportView(listEntrante);
		{
			scrollPane_2.setBounds(279, 183, 102, 190);
			frmFritura.getContentPane().add(scrollPane_2);
		}

		listPrimerPlato = new JList();
		scrollPane_2.setViewportView(listPrimerPlato);
		{
			scrollPane_3.setBounds(420, 183, 102, 190);
			frmFritura.getContentPane().add(scrollPane_3);
		}

		listSegundoPlato = new JList();
		scrollPane_3.setViewportView(listSegundoPlato);
		{
			scrollPane_4.setBounds(549, 183, 110, 190);
			frmFritura.getContentPane().add(scrollPane_4);
		}

		listPostre = new JList();
		scrollPane_4.setViewportView(listPostre);
		{
			lblbebida.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblbebida.setBounds(12, 154, 56, 16);
			frmFritura.getContentPane().add(lblbebida);
		}
		{
			lblentrantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblentrantes.setBounds(141, 154, 77, 16);
			frmFritura.getContentPane().add(lblentrantes);
		}
		{
			lblpPrimero.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblpPrimero.setBounds(279, 148, 102, 22);
			frmFritura.getContentPane().add(lblpPrimero);
		}
		{
			lblsPlato.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblsPlato.setBounds(420, 148, 102, 22);
			frmFritura.getContentPane().add(lblsPlato);
		}
		{
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(549, 154, 56, 16);
			frmFritura.getContentPane().add(lblNewLabel);
		}
	}
	private class CBoxMesasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(!cBoxMesas.getSelectedItem().toString().equals("   ----")) {
				btnPagar.setEnabled(true);
				lblPrecio.setText(String.valueOf(DTOCuenta.calcularPrecio(cBoxMesas.getSelectedItem().toString()))+" €");

				cuenta=DTOCuenta.devolverCuenta(cBoxMesas.getSelectedItem().toString());
				modeloBebidas = new DefaultListModel();
				for(Bebida bebida: cuenta.getBebida()) {
					modeloBebidas.addElement(bebida.getNombre());
				}
				listBebida.setModel(modeloBebidas);

				modeloEntrantes = new DefaultListModel();
				for(Alimento entrante: cuenta.getEntrante()) {
					modeloEntrantes.addElement(entrante.getNombre());
				}
				listEntrante.setModel(modeloEntrantes);

				modeloPrimerPlato = new DefaultListModel();
				for(Alimento primerp: cuenta.getPrimerPlato()) {
					modeloPrimerPlato.addElement(primerp.getNombre());
				}
				listPrimerPlato.setModel(modeloPrimerPlato);

				modeloSegundoPlato = new DefaultListModel();
				for(Alimento segundop: cuenta.getSegundoPlato()) {
					modeloSegundoPlato.addElement(segundop.getNombre());
				}
				listSegundoPlato.setModel(modeloSegundoPlato);

				modeloPostre = new DefaultListModel();
				for(Alimento postre: cuenta.getPostre()) {
					modeloPostre.addElement(postre.getNombre());
				}
				listPostre.setModel(modeloPostre);
			}else {
				btnPagar.setEnabled(false);
			}
		}
	}
	private class BtnPagarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showOptionDialog(null, "Selecciona el metodo de pago" ,"Metodo de pago"
					, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
				null,new Object[] { "Efectivo", "Tarjeta"},"Efectivo");
			IU_Camarero iuIntPrin=new IU_Camarero();
			frmFritura.setVisible(false);
		}
	}
}
