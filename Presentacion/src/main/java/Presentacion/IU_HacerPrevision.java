package Presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dominio.DTOComanda;
import Dominio.DTOPrevision;
import Dominio.DTOReceta;
import Dominio.Receta;
import Dominio.Repeticion;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
public class IU_HacerPrevision extends JFrame {

	private JPanel contentPane;
	private DefaultListModel modeloAlimentos;
	private DefaultListModel modeloIngredientes;
	private JLabel lblMensaje;
	private LocalDateTime fecha;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_HacerPrevision frame = new IU_HacerPrevision();
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
	public IU_HacerPrevision() {
		setResizable(false);
		setTitle("Fritura");
		setBounds(new Rectangle(380, 170, 700, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPedidos = new JLabel("Ultimos pedidos");
		lblPedidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedidos.setBounds(80, 61, 209, 21);
		lblPedidos.setFont(Fuente.FUENTE_LBL);
		contentPane.add(lblPedidos);

		final JSpinner spnCantidad = new JSpinner();
		spnCantidad.setBounds(475, 379, 56, 29);
		spnCantidad.setFont(Fuente.FUENTE_LBL);
		contentPane.add(spnCantidad);

		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredientes.setBounds(396, 61, 209, 19);
		lblIngredientes.setFont(Fuente.FUENTE_LBL);
		contentPane.add(lblIngredientes);


		modeloAlimentos = new DefaultListModel();
		modeloIngredientes = new DefaultListModel();

		final JList lstPedidos = new JList();
		lstPedidos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				modeloIngredientes.clear();
				LinkedList<Receta> recetario = new LinkedList<>();
				String nombre = String.valueOf(lstPedidos.getSelectedValue());
				nombre = nombre.substring(0, nombre.length()-4);
				DTOReceta.leerReceta(nombre, recetario);
				for(Receta rec : recetario) {
					modeloIngredientes.addElement(rec.getNombreIngrediente() + " - " + rec.getCantidad());
				}
			}
		});
		lstPedidos.setBounds(80, 92, 209, 270);
		contentPane.add(lstPedidos);

		final JList lstIngredientes = new JList();
		lstIngredientes.setBounds(396, 92, 209, 270);
		contentPane.add(lstIngredientes);

		LinkedList<String> pedido = new LinkedList<>();
		DTOComanda.leerAlimentosDeComandas(pedido);

		LinkedList<Repeticion> repe = new LinkedList<>();
		int repeticiones = 0;
		Set<String> set = new HashSet<>(pedido); //Hacemos un conjunto donde no haya valores repetidos
		Iterator it = set.iterator(); //Lo separamos en esos valores
		while(it.hasNext()) { //Leemos esos valores
			String al = String.valueOf(it.next());
			repeticiones = Collections.frequency(pedido, al); //Comprobamos cuantas veces aparecen esos valores en la lista Pedido
			repe.add(new Repeticion(al, repeticiones)); //anadimos el valor y las veces que se repite
		}
		pedido.clear();
		pedido.addAll(set);
		for(int i = 0; i < pedido.size(); i++)
			modeloAlimentos.addElement(pedido.get(i) + " - "+String.valueOf(repe.get(i).getRepeticiones()));
		lstPedidos.setModel(modeloAlimentos);
		lstIngredientes.setModel(modeloIngredientes);

		JLabel lblAniadir = new JLabel("Anadir ingredientes");
		lblAniadir.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAniadir.setBounds(289, 379, 164, 29);
		lblAniadir.setFont(Fuente.FUENTE_LBL);
		contentPane.add(lblAniadir);

		JButton btnAnadir = new JButton("Anadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lstIngredientes.getSelectedValue()!=null) {
					String nombre = String.valueOf(lstIngredientes.getSelectedValue());
					nombre = nombre.substring(0, nombre.length()-4);
					int cantidad = (Integer) spnCantidad.getValue();
					DTOPrevision.actualizarStock(nombre, cantidad);
					lblMensaje.setText("Cantidad anadida al stock");
					lblMensaje.setForeground(Color.green);
					lblMensaje.setEnabled(true);
					fecha = LocalDateTime.now();
					DTOPrevision.hacerPrevision(nombre, cantidad, fecha);
				}
			}
		});
		btnAnadir.setBounds(299, 413, 115, 29);
		btnAnadir.setFont(Fuente.FUENTE_BTN);
		contentPane.add(btnAnadir);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAtras.setFont(Fuente.FUENTE_BTN);
		btnAtras.setBounds(22, 413, 85, 29);
		contentPane.add(btnAtras);

		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblMensaje.setEnabled(false);
		lblMensaje.setBounds(439, 418, 214, 24);
		contentPane.add(lblMensaje);

		JLabel lblBienvenida = new JLabel("Bienvenido al sistema Fritura");
		lblBienvenida.setFont(new Font("Verdana", Font.BOLD, 20));
		lblBienvenida.setBounds(163, 10, 357, 33);
		contentPane.add(lblBienvenida);
	}
}
