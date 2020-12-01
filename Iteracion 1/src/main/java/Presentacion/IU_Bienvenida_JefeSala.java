package Presentacion;

import java.awt.Font;

import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Dominio.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class IU_Bienvenida_JefeSala extends JFrame implements Fuente{

	private JPanel contentPane;
	private JPanel pnlBienvenida;
	private JLabel lblBienvenida;
	public Font fuente = new Font("verdana", 1, 20);
	private JLabel lblDeseoJefeDeSala;
	private JButton btnAccesoReserva;
	private JButton btnAccesoCancelar;
	private JButton btnAccesoComprobar;
	
	
	/**
	 * Create the frame.
	 */
	public IU_Bienvenida_JefeSala() {
		setTitle("Fritura");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(380, 170, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnlBienvenida = new JPanel();
		pnlBienvenida.setBounds(5, 5, 676, 453);
		contentPane.add(pnlBienvenida);
		pnlBienvenida.setLayout(null);
		
		lblBienvenida = new JLabel("Bienvenido al sistema Fritura");
		lblBienvenida.setBounds(158, 26, 357, 33);
		lblBienvenida.setFont(FUENTE_CAB);
		pnlBienvenida.add(lblBienvenida);
		
		lblDeseoJefeDeSala = new JLabel("¿Qué desea hacer?");
		lblDeseoJefeDeSala.setBounds(250, 88, 153, 21);
		lblDeseoJefeDeSala.setFont(FUENTE_LBL);
		pnlBienvenida.add(lblDeseoJefeDeSala);
		
		btnAccesoReserva = new JButton("Reservar Mesa");
		btnAccesoReserva.setHorizontalAlignment(SwingConstants.LEFT);
		btnAccesoReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LinkedList<Mesa> lista = new LinkedList<Mesa>();
				DTOMesa.leerMesas(lista);
				IU_ReservarMesa iu_reserv = new IU_ReservarMesa(lista);
				iu_reserv.setVisible(true);
			}
		});
		btnAccesoReserva.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAccesoReserva.setBounds(57, 185, 123, 33);
		btnAccesoReserva.setFont(FUENTE_BTN);
		pnlBienvenida.add(btnAccesoReserva);
		
		btnAccesoCancelar = new JButton("Cancelar Reserva");
		btnAccesoCancelar.setHorizontalAlignment(SwingConstants.LEFT);
		btnAccesoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LinkedList<Reserva> lista = new LinkedList<Reserva>();
				DTOReserva.leerReservas(lista);
				IU_CancelarReserva iu_canc = new IU_CancelarReserva(lista);
				iu_canc.setVisible(true);
				
			}
		});
		btnAccesoCancelar.setHorizontalTextPosition(SwingConstants.LEFT);
		btnAccesoCancelar.setBounds(276, 185, 132, 33);
		btnAccesoCancelar.setFont(FUENTE_BTN);
		pnlBienvenida.add(btnAccesoCancelar);
		
		btnAccesoComprobar = new JButton("Comprobar estados");
		btnAccesoComprobar.setHorizontalAlignment(SwingConstants.LEFT);
		btnAccesoComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LinkedList<Camarero> listaCamarero = new LinkedList<Camarero>();
				DTOCamarero.leerCamareros(listaCamarero);
				LinkedList<Reserva> listaReserva = new LinkedList<Reserva>();
				DTOReserva.leerReservas(listaReserva);
				IU_ComprobarReserva iu_compr = new IU_ComprobarReserva(listaReserva, listaCamarero);
				iu_compr.setVisible(true);
			}
		});
		btnAccesoComprobar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAccesoComprobar.setBounds(492, 185, 141, 33);
		btnAccesoComprobar.setFont(FUENTE_BTN);
		pnlBienvenida.add(btnAccesoComprobar);
	}
}
