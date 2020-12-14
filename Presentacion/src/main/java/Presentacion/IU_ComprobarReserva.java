package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class IU_ComprobarReserva extends JFrame implements Fuente{

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblComprobar_Cab;
	private JLabel lblSeleccionar;
	private JComboBox cmbBxReservas;
	private JLabel lblCamarero;
	private JComboBox cmbBxCamarero;
	private JButton btnConfirmar;
	private JLabel lblResultado;
	private JTextField txtNombre;
	private JLabel lblNombre;

	/**
	 * Create the frame.
	 */
	public IU_ComprobarReserva(final LinkedList<Integer> listaReserva, final LinkedList<Camarero> listaCamarero) {
		DTOMesa.actualizarEstadoMesasPorTurno();
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

		lblComprobar_Cab = new JLabel("Sistema de asignación de camareros");
		lblComprobar_Cab.setFont(FUENTE_CAB);
		lblComprobar_Cab.setBounds(136, 43, 425, 33);
		panel.add(lblComprobar_Cab);

		lblSeleccionar = new JLabel("Selecciona la reserva que desea comprobar");
		lblSeleccionar.setBounds(64, 142, 359, 18);
		lblSeleccionar.setFont(FUENTE_LBL);
		panel.add(lblSeleccionar);

		cmbBxReservas = new JComboBox();
		cmbBxReservas.addItem("-------------");
		for(int i = 0; i < listaReserva.size(); i++) {
			cmbBxReservas.addItem(listaReserva.get(i));
		}
		cmbBxReservas.setBounds(78, 170, 136, 21);
		cmbBxReservas.setFont(FUENTE_LBL);


		cmbBxReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbBxReservas.getSelectedIndex()!=0) {
					String id = cmbBxReservas.getSelectedItem().toString();
					LinkedList<Reserva> listaRes = new LinkedList<Reserva>();
					DTOReserva.leerReserva(Integer.parseInt(id), listaRes);
					txtNombre.setText(listaRes.getFirst().getNombre());
				}
			}
		});

		panel.add(cmbBxReservas);

		lblCamarero = new JLabel("¿Qué camarero desea asignar?");
		lblCamarero.setBounds(95, 262, 245, 33);
		lblCamarero.setFont(FUENTE_LBL);
		panel.add(lblCamarero);

		cmbBxCamarero = new JComboBox();
		cmbBxCamarero.setBounds(371, 270, 111, 21);

		cmbBxCamarero.addItem("-----");

		for(int i = 0; i < listaCamarero.size(); i++)
			cmbBxCamarero.addItem(listaCamarero.get(i).getIdCamarero()+": "+listaCamarero.get(i).getNombre());

		panel.add(cmbBxCamarero);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String id = cmbBxReservas.getSelectedItem().toString().substring(8);
				//DTOReserva.cambiarEstado(Integer.parseInt(id), cmbBxEstados.getSelectedItem().toString());
				String idReserva=cmbBxReservas.getSelectedItem().toString();
				//String nombreCamarero=
				LinkedList<Reserva> listaReserva = new LinkedList<Reserva>();
				DTOReserva.leerReserva(Integer.parseInt(idReserva) ,listaReserva);
				DTOMesa.cambiarMesaOcupada(idReserva);
				DTOMesa.asignarCamarero(listaCamarero.get(cmbBxCamarero.getSelectedIndex()-1), idReserva);
				lblResultado.setText("Cambios realizados con éxito.");
				lblResultado.setForeground(Color.green);
				lblResultado.setVisible(true);
				cmbBxReservas.setSelectedIndex(0);
				cmbBxCamarero.setSelectedIndex(0);
				txtNombre.setText("");
			}
		});
		btnConfirmar.setBounds(281, 330, 99, 33);
		panel.add(btnConfirmar);

		lblResultado = new JLabel("New label");
		lblResultado.setVisible(false);
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(201, 380, 257, 25);
		lblResultado.setFont(new Font("Verdana", Font.ITALIC, 13));
		panel.add(lblResultado);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.PLAIN, 16));
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(371, 216, 156, 22);
		panel.add(txtNombre);

		lblNombre = new JLabel("Reserva a nombre de:");
		lblNombre.setFont(new Font("Verdana", Font.ITALIC, 15));
		lblNombre.setBounds(159, 218, 181, 21);
		panel.add(lblNombre);
	}
}
