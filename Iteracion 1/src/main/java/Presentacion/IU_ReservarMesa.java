package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import Dominio.*;

import java.util.LinkedList;
import java.util.Locale;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class IU_ReservarMesa extends JFrame implements Fuente{

	private JPanel contentPane;
	private JPanel pnlReservar;
	private JLabel lblReservarMesa;
	private JComboBox cmbBxMesas;
	private JLabel lblMesaComensales;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JButton btnReservar;
	private JLabel lblID;
	private JTextField txtMesa;
	private JLabel lblResultado;
	private JLabel lblTurno;
	private JRadioButton rdbtnComida;
	private JRadioButton rdbtnCena;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cmbbxTurnos;
	private JComboBox cmbbxComensales;
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm").localizedBy(new Locale("es-ES"));
	private JDateChooser dateChooser;
	private JLabel lblFecha;
	private JTextField txtNombre;
	private JLabel lblNombre;

	/**
	 * Create the frame.
	 */
	public IU_ReservarMesa(final LinkedList<Mesa> lista) {
		setTitle("Fritura");
		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(new Rectangle(380, 170, 960, 540));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pnlReservar = new JPanel();
		contentPane.add(pnlReservar, BorderLayout.CENTER);
		pnlReservar.setLayout(null);

		lblReservarMesa = new JLabel("Opción Reservar Mesa");
		lblReservarMesa.setBounds(155, 40, 350, 50);
		lblReservarMesa.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservarMesa.setFont(FUENTE_CAB);
		pnlReservar.add(lblReservarMesa);

		cmbBxMesas = new JComboBox();
		cmbBxMesas.addItem("-------------");
		for(int i = 0; i < lista.size(); i++)
			cmbBxMesas.addItem("Mesa "+lista.get(i).getId());
		cmbBxMesas.setBounds(54, 130, 129, 21);
		cmbBxMesas.setFont(FUENTE_LBL);
		cmbBxMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbBxMesas.getSelectedIndex()!=0) {
					String id = cmbBxMesas.getSelectedItem().toString().substring(5);
					DTOMesa.leerMesa(Integer.parseInt(id), lista);
					txtMesa.setText(id);
					buttonGroup.clearSelection();
					txtEstado.setText("");
					txtNombre.setText("");
				}
				else {
					txtMesa.setText("");
					buttonGroup.clearSelection();
					txtEstado.setText("");
					txtNombre.setText("");
				}
			}
		});
		pnlReservar.add(cmbBxMesas);

		lblMesaComensales = new JLabel("Nº Comensales: ");
		lblMesaComensales.setBounds(334, 175, 136, 21);
		lblMesaComensales.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesaComensales.setFont(FUENTE_LBL);
		pnlReservar.add(lblMesaComensales);

		lblEstado = new JLabel("Estado: ");
		lblEstado.setBounds(393, 339, 73, 21);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(FUENTE_LBL);
		pnlReservar.add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBounds(462, 330, 177, 30);
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		pnlReservar.add(txtEstado);

		btnReservar = new JButton("Reservar mesa");

		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalTime hm = LocalTime.parse((String)cmbbxTurnos.getSelectedItem(), df);
				LocalDate s = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDateTime fecha = LocalDateTime.of(s, hm);

				LinkedList<Reserva> listaAux = new LinkedList<Reserva>();
				DTOReserva.leerReservasAux(Integer.parseInt(txtMesa.getText()), fecha,   listaAux);

				if(listaAux.size()==0) {
					if(rdbtnComida.isSelected()) {
						DTOReserva.anadirReserva(cmbbxComensales.getSelectedItem().toString(), "Reservada", fecha, txtMesa.getText(), "Comida",txtNombre.getText());
						txtEstado.setText("Reservada");
						lblResultado.setText("Mesa reservada con éxito");
						lblResultado.setForeground(Color.green);
						lblResultado.setVisible(true);

					}else if(rdbtnCena.isSelected()) {
						DTOReserva.anadirReserva(cmbbxComensales.getSelectedItem().toString(), "Reservada", fecha, txtMesa.getText(), "Cena", txtNombre.getText());
						lblResultado.setText("Mesa reservada con éxito");
						lblResultado.setForeground(Color.green);
						lblResultado.setVisible(true);
					}
				}
				else {
					lblResultado.setText("Esa reserva ya existe");
					lblResultado.setForeground(Color.red);
					lblResultado.setVisible(true);		
				}
			}
		});
		btnReservar.setBounds(54, 408, 129, 25);
		btnReservar.setFont(FUENTE_BTN);
		pnlReservar.add(btnReservar);

		lblID = new JLabel("Id. mesa:");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Verdana", Font.ITALIC, 15));
		lblID.setBounds(377, 132, 93, 21);
		pnlReservar.add(lblID);

		txtMesa = new JTextField();
		txtMesa.setEditable(false);
		txtMesa.setColumns(10);
		txtMesa.setBounds(462, 130, 30, 30);
		pnlReservar.add(txtMesa);

		lblResultado = new JLabel("New label");
		lblResultado.setVisible(false);
		lblResultado.setBounds(193, 410, 286, 21);
		pnlReservar.add(lblResultado);

		lblTurno = new JLabel("Turno:");
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno.setFont(new Font("Verdana", Font.ITALIC, 15));
		lblTurno.setBounds(407, 250, 63, 21);
		pnlReservar.add(lblTurno);

		rdbtnComida = new JRadioButton("Comida");
		rdbtnComida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbbxTurnos.removeAllItems();
				cmbbxTurnos.addItem("13:00");
				cmbbxTurnos.addItem("14:30");
				cmbbxTurnos.addItem("16:00");
				cmbbxTurnos.setVisible(true);
			}
		});

		buttonGroup.add(rdbtnComida);
		rdbtnComida.setBounds(462, 250, 100, 19);
		rdbtnComida.setFont(FUENTE_RDBTN);
		pnlReservar.add(rdbtnComida);

		rdbtnCena = new JRadioButton("Cena");
		rdbtnCena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbbxTurnos.removeAllItems();
				cmbbxTurnos.addItem("20:00");
				cmbbxTurnos.addItem("21:30");
				cmbbxTurnos.addItem("23:00");
				cmbbxTurnos.setVisible(true);
			}
		});
		buttonGroup.add(rdbtnCena);
		rdbtnCena.setBounds(564, 250, 82, 19);
		rdbtnCena.setFont(FUENTE_RDBTN);
		pnlReservar.add(rdbtnCena);

		cmbbxTurnos = new JComboBox();
		cmbbxTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbbxTurnos.getItemCount()!=0) {
					LocalTime hm = LocalTime.parse((String)cmbbxTurnos.getSelectedItem(), df);
					LocalDate s = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDateTime fecha = LocalDateTime.of(s, hm);

					LinkedList<Reserva> listaAux = new LinkedList<Reserva>();
					DTOReserva.leerReservasAux(Integer.parseInt(txtMesa.getText()), fecha, listaAux);

					if(listaAux.size()!=0) {
						txtEstado.setText("Reservada");
					}
					else {
						txtEstado.setText("Libre");
					}
				}
			}
		});
		cmbbxTurnos.setVisible(false);
		cmbbxTurnos.setBounds(486, 279, 109, 30);
		pnlReservar.add(cmbbxTurnos);

		cmbbxComensales = new JComboBox();
		cmbbxComensales.setModel(new DefaultComboBoxModel(new String[] {"2", "4", "6"}));
		cmbbxComensales.setFont(FUENTE_RDBTN);
		cmbbxComensales.setBounds(461, 177, 43, 21);
		pnlReservar.add(cmbbxComensales);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(461, 208, 143, 30);
		dateChooser.setFont(FUENTE_LBL);
		pnlReservar.add(dateChooser);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setBounds(394, 219, 76, 19);
		lblFecha.setFont(FUENTE_LBL);
		pnlReservar.add(lblFecha);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(462, 370, 177, 30);
		pnlReservar.add(txtNombre);

		lblNombre = new JLabel("Nombre: ");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Verdana", Font.ITALIC, 15));
		lblNombre.setBounds(394, 379, 73, 21);
		pnlReservar.add(lblNombre);
	}
}
