package Presentacion;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import Dominio.Bebida;
import Dominio.Mesa;
import Dominio.Entrante;
import Dominio.Camarero;
import Dominio.DTOAlimento;
import Dominio.DTOBebida;
import Dominio.DTOComanda;
import Dominio.DTOMesa;
import Dominio.DTOEntrante;
import Dominio.DTOPrimerPlato;
import Dominio.DTOSegundoPlato;
import Dominio.DTOPostre;
import Dominio.DTOCamarero;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IU_AnotarComanda extends JFrame{

	private JFrame frame;
	private JLabel lblNumeroMesa;
	private JComboBox comboBox;
	private JButton btnConfirmarNMesa;
	private JList lstBebidas;
	private JList lstComandaActual;
	private JList lstEntrantes;
	private JList lstPrimerPlato;
	private JList lstSegundoPlato;
	private JList lstPostre;
	private JButton btnConfirmarComanda;
	private JLabel lblComandaActual;
	private JLabel lblBebidas;
	private JLabel lblEntrantes;
	private JLabel lblPrimerPlato;
	private JLabel lblSegundoPlato;
	private JLabel lblPostres;
	DefaultListModel modeloBebidas;
	DefaultListModel modeloComandaActual;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_AnotarComanda window = new IU_AnotarComanda(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IU_AnotarComanda(String camarero) {

		initialize(camarero);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String camarero) {
		frame = new JFrame();
		frame.setBounds(380, 170, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		lblNumeroMesa = new JLabel("Selecciona el número de mesa");
		lblNumeroMesa.setBounds(10, 11, 202, 14);
		frame.getContentPane().add(lblNumeroMesa);

		comboBox = new JComboBox();
		comboBox.setBounds(222, 7, 63, 29);
		frame.getContentPane().add(comboBox);
		LinkedList<Mesa> listaMesas=new LinkedList<Mesa>();
		DTOMesa.leerMesasPorCamarero(listaMesas, camarero);
		comboBox.addItem("   ----");
		for(Mesa mesa: listaMesas) {
			comboBox.addItem(mesa.getId());
		}


		btnConfirmarNMesa = new JButton("Confirmar número de mesa");
		btnConfirmarNMesa.addActionListener(new BtnConfirmarNMesaActionListener());
		btnConfirmarNMesa.setBounds(295, 7, 224, 29);
		frame.getContentPane().add(btnConfirmarNMesa);



		btnConfirmarComanda = new JButton("Confirmar comanda");
		btnConfirmarComanda.setEnabled(false);
		btnConfirmarComanda.addActionListener(new BtnConfirmarComandaActionListener());
		btnConfirmarComanda.setBounds(493, 401, 169, 23);
		frame.getContentPane().add(btnConfirmarComanda);

		lblComandaActual = new JLabel("Comanda Actual");
		lblComandaActual.setBounds(536, 48, 111, 14);
		frame.getContentPane().add(lblComandaActual);

		lblBebidas = new JLabel("Bebida");
		lblBebidas.setBounds(97, 61, 76, 14);
		frame.getContentPane().add(lblBebidas);

		lblEntrantes = new JLabel("Entrante");
		lblEntrantes.setBounds(300, 61, 66, 14);
		frame.getContentPane().add(lblEntrantes);

		lblPrimerPlato = new JLabel("Primer Plato");
		lblPrimerPlato.setBounds(93, 191, 104, 14);
		frame.getContentPane().add(lblPrimerPlato);

		lblSegundoPlato = new JLabel("Segundo Plato");
		lblSegundoPlato.setBounds(291, 187, 96, 14);
		frame.getContentPane().add(lblSegundoPlato);

		lblPostres = new JLabel("Postre");
		lblPostres.setBounds(207, 306, 66, 14);
		frame.getContentPane().add(lblPostres);

		lstBebidas = new JList();
		lstBebidas.addMouseListener(new LstBebidasMouseListener());
		lstBebidas.setBounds(41, 87, 156, 93);
		frame.getContentPane().add(lstBebidas);
		LinkedList<String> listaBebidas=new LinkedList<String>();
		DTOAlimento.leerNombreAlimentosPorTipo(listaBebidas, lblBebidas.getText());
		modeloBebidas = new DefaultListModel();
		for(String nombre: listaBebidas) {
			modeloBebidas.addElement(nombre);
		}
		lstBebidas.setModel(modeloBebidas);

		lstComandaActual = new JList(new DefaultListModel<String>());
		lstComandaActual.setBounds(477, 73, 199, 304);
		frame.getContentPane().add(lstComandaActual);
		modeloComandaActual = new DefaultListModel();
		lstComandaActual.setModel(modeloComandaActual);

		lstEntrantes = new JList();
		lstEntrantes.addMouseListener(new LstEntrantesMouseListener());
		lstEntrantes.setBounds(253, 87, 156, 93);
		frame.getContentPane().add(lstEntrantes);
		LinkedList<String> listaEntrantes=new LinkedList<String>();
		DTOAlimento.leerNombreAlimentosPorTipo(listaEntrantes, lblEntrantes.getText());
		DefaultListModel modeloEntrantes = new DefaultListModel();
		for(String nombre: listaEntrantes) {
			modeloEntrantes.addElement(nombre);
		}
		lstEntrantes.setModel(modeloEntrantes);

		lstPrimerPlato = new JList();
		lstPrimerPlato.addMouseListener(new LstPrimerPlatoMouseListener());
		lstPrimerPlato.setBounds(41, 212, 156, 93);
		frame.getContentPane().add(lstPrimerPlato);
		LinkedList<String> listaPrimerosPlatos=new LinkedList<String>();
		DTOAlimento.leerNombreAlimentosPorTipo(listaPrimerosPlatos, lblPrimerPlato.getText());
		DefaultListModel modeloPrimerosPlatos = new DefaultListModel();
		for(String nombre: listaPrimerosPlatos) {
			modeloPrimerosPlatos.addElement(nombre);
		}
		lstPrimerPlato.setModel(modeloPrimerosPlatos);

		lstSegundoPlato = new JList();
		lstSegundoPlato.addMouseListener(new LstSegundoPlatoMouseListener());
		lstSegundoPlato.setBounds(253, 212, 156, 93);
		frame.getContentPane().add(lstSegundoPlato);
		LinkedList<String> listaSegundosPlatos=new LinkedList<String>();
		DTOAlimento.leerNombreAlimentosPorTipo(listaSegundosPlatos, lblSegundoPlato.getText());
		DefaultListModel modeloSegundosPlatos = new DefaultListModel();
		for(String nombre: listaSegundosPlatos) {
			modeloSegundosPlatos.addElement(nombre);
		}
		lstSegundoPlato.setModel(modeloSegundosPlatos);

		lstPostre = new JList();
		lstPostre.addMouseListener(new LstPostreMouseListener());
		lstPostre.setBounds(148, 331, 156, 93);
		frame.getContentPane().add(lstPostre);
		LinkedList<String> listaPostres=new LinkedList<String>();
		DTOAlimento.leerNombreAlimentosPorTipo(listaPostres, lblPostres.getText());
		DefaultListModel modeloPostres = new DefaultListModel();
		for(String nombre: listaPostres) {
			modeloPostres.addElement(nombre);
		}
		lstPostre.setModel(modeloPostres);
	}
	private class LstBebidasMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			modeloComandaActual.addElement(lstBebidas.getSelectedValue());
			lstBebidas.clearSelection();
		}
	}
	private class LstEntrantesMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			modeloComandaActual.addElement(lstEntrantes.getSelectedValue());
			lstEntrantes.clearSelection();
		}
	}
	private class LstPrimerPlatoMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			modeloComandaActual.addElement(lstPrimerPlato.getSelectedValue());
			lstPrimerPlato.clearSelection();
		}
	}
	private class LstSegundoPlatoMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			modeloComandaActual.addElement(lstSegundoPlato.getSelectedValue());
			lstSegundoPlato.clearSelection();
		}
	}
	private class LstPostreMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			modeloComandaActual.addElement(lstPostre.getSelectedValue());
			lstPostre.clearSelection();
		}
	}
	private class BtnConfirmarComandaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			LinkedList<String> ingredientesComprobados=new LinkedList<String>();
			LinkedList<String> ingredientes=new LinkedList<String>();
			LinkedList<String> ingredientesSinStock=new LinkedList<String>();
			String mensajeIngredientesSinStock="";
			for(int i=0;i<modeloComandaActual.getSize();i++) {
				ingredientes.add(modeloComandaActual.getElementAt(i).toString());
			}
			ingredientesSinStock=DTOComanda.comprobarStock(ingredientes);
			if(ingredientesSinStock.size()==0) {
				DTOComanda.guardarComanda(comboBox.getSelectedItem().toString(), ingredientes);
				JOptionPane.showMessageDialog(null, "Comanda enviada a cocina", "Comanda realizada",JOptionPane.INFORMATION_MESSAGE);
			}else {

				while(ingredientesSinStock.size()!=0) {
					int contador=0;
					for(int i=0;i<modeloComandaActual.getSize();i++) {
						if(modeloComandaActual.get(i).equals(ingredientesSinStock.get(0))) {
							contador++;
						}
					}
					while(contador!=0) {
						modeloComandaActual.removeElement(ingredientesSinStock.get(0));
						contador--;
					}
					mensajeIngredientesSinStock=mensajeIngredientesSinStock.concat("- "+ingredientesSinStock.get(0)+"\n");
					ingredientesSinStock.remove(0);
				}
				JOptionPane.showMessageDialog(null, mensajeIngredientesSinStock, "Sin stock",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	private class BtnConfirmarNMesaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String idMesa=comboBox.getSelectedItem().toString();
			if(DTOMesa.estadoCorrecto(idMesa)) {
				btnConfirmarComanda.setEnabled(true);
			}else {
				JOptionPane.showMessageDialog(null, "Debe secuenciar el estado "
						+ "de la mesa:"+idMesa, "Estado incorrecto",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
