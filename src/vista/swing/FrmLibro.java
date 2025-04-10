package vista.swing;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;
import servicios.LibrosServicios;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.awt.event.ActionEvent;

public class FrmLibro {

	private JFrame frame;
	private JTextField fldIdLibro;
	private JTextField fldTitulo;
	private JTextField fldAutor;
	private JTextField fldEditorial;
	private JTextField fldIsbn;
	private JTextField fldFecha;
	private List<JTextField> camposDeTexto;
	private List<JButton> botonesNavegador;
	
	JCheckBox chckbxPrestado;
	JLabel lblError;
	JPanel pnlFormulario;
	private JPanel pnlMantenimiento;
	private JButton btnNuevo, btnEditar, btnBorrar, btnDeshacer, btnGuardar;
	private JPanel pnlNavegador;
	private JButton btnPrimero;
	private JButton btnAtras;
	private JButton btnAdelante;
	private JButton btnUltimo;
	
	private boolean nuevo = false;
	private int puntero;
	private List<Libro> libros;
	
	
	public FrmLibro() {
		definirVentana();
		 
		camposDeTexto = new ArrayList<>();
		camposDeTexto.add(fldEditorial); camposDeTexto.add(fldAutor); camposDeTexto.add(fldIdLibro);
		camposDeTexto.add(fldTitulo); camposDeTexto.add(fldIsbn); camposDeTexto.add(fldFecha);
		
		botonesNavegador = new ArrayList<>();
		botonesNavegador.add(btnAdelante); botonesNavegador.add(btnAtras); botonesNavegador.add(btnPrimero);
		botonesNavegador.add(btnUltimo);
		
		puntero = 0;
		
		LibrosServicios ls = new LibrosServicios();
		try {
			libros = ls.obtenerTodos();
			ls = null;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		habilitarPanelLibros(false);
		habilitarBotonesNavegador(true);
		deshabilitarBotonesMantenimiento(); //habilitarBotonesMantenimiento();
		mostrarLibro(puntero);
		eventos();
	}

	private void definirVentana() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("FORMULARIO BIBLIOTECA");
		
		pnlFormulario = new JPanel();
		pnlFormulario.setBounds(27, 106, 372, 246);
		frame.getContentPane().add(pnlFormulario);
		pnlFormulario.setLayout(null);
		
		pnlFormulario.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), " Libro "));
		
		JLabel lblIdLibro = new JLabel("ID Libro");
		lblIdLibro.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblIdLibro.setBounds(10, 21, 77, 31);
		pnlFormulario.add(lblIdLibro);
		
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblTitulo.setBounds(10, 51, 77, 31);
		pnlFormulario.add(lblTitulo);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblAutor.setBounds(10, 83, 77, 31);
		pnlFormulario.add(lblAutor);
		
		JLabel Editorial = new JLabel("Editorial");
		Editorial.setFont(new Font("Verdana", Font.PLAIN, 11));
		Editorial.setBounds(10, 112, 77, 31);
		pnlFormulario.add(Editorial);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblIsbn.setBounds(10, 143, 77, 31);
		pnlFormulario.add(lblIsbn);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblFecha.setBounds(10, 173, 52, 31);
		pnlFormulario.add(lblFecha);
		
		fldIdLibro = new JTextField();
		fldIdLibro.setBounds(59, 23, 40, 26);
		pnlFormulario.add(fldIdLibro);
		fldIdLibro.setColumns(10);
		
		fldTitulo = new JTextField();
		fldTitulo.setColumns(10);
		fldTitulo.setBounds(59, 53, 280, 26);
		pnlFormulario.add(fldTitulo);
		
		fldAutor = new JTextField();
		fldAutor.setColumns(10);
		fldAutor.setBounds(59, 85, 280, 26);
		pnlFormulario.add(fldAutor);
		
		fldEditorial = new JTextField();
		fldEditorial.setColumns(10);
		fldEditorial.setBounds(59, 114, 280, 26);
		pnlFormulario.add(fldEditorial);
		
		fldIsbn = new JTextField();
		fldIsbn.setColumns(10);
		fldIsbn.setBounds(59, 145, 280, 26);
		pnlFormulario.add(fldIsbn);
		
		fldFecha = new JTextField();
		fldFecha.setColumns(10);
		fldFecha.setBounds(59, 178, 140, 26);
		pnlFormulario.add(fldFecha);
		
		JLabel lblFormatoFecha = new JLabel("aaaa-MM-dd");
		lblFormatoFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormatoFecha.setBounds(201, 182, 77, 26);
		pnlFormulario.add(lblFormatoFecha);
		
		chckbxPrestado = new JCheckBox("Prestado");
		chckbxPrestado.setFont(new Font("Verdana", Font.PLAIN, 11));
		chckbxPrestado.setBounds(10, 203, 99, 23);
		pnlFormulario.add(chckbxPrestado);
		
		lblError = new JLabel("");
		lblError.setBounds(99, 221, 263, 14);
		pnlFormulario.add(lblError);
		
		pnlMantenimiento = new JPanel();
		pnlMantenimiento.setBounds(27, 11, 372, 84);
		
		frame.getContentPane().add(pnlMantenimiento);
		pnlMantenimiento.setLayout(null);
		pnlMantenimiento.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), " Mantenimiento Libros "));
		
		btnNuevo = new JButton(new ImageIcon("imagenes3/botonAgregar.jpg"));
		btnNuevo.setBounds(10, 17, 64, 56);
		pnlMantenimiento.add(btnNuevo);
		
		btnEditar = new JButton(new ImageIcon("imagenes3/botonEditar.jpg"));
		btnEditar.setBounds(80, 17, 64, 56);
		pnlMantenimiento.add(btnEditar);
		
		btnBorrar = new JButton(new ImageIcon("imagenes3/borrar.jpg"));
		btnBorrar.setBounds(154, 17, 64, 56);
		pnlMantenimiento.add(btnBorrar);
		
		btnGuardar = new JButton(new ImageIcon("imagenes3/botonGuardar.jpg"));
		btnGuardar.setBounds(228, 17, 64, 56);
		pnlMantenimiento.add(btnGuardar);
		
		btnDeshacer = new JButton(new ImageIcon("imagenes3/botonDeshacer.jpg"));
		btnDeshacer.setBounds(302, 17, 60, 56);
		pnlMantenimiento.add(btnDeshacer);
		
		pnlNavegador = new JPanel();
		pnlNavegador.setBounds(74, 363, 274, 84);
		pnlNavegador.setBorder(new TitledBorder(new LineBorder(Color.blue,2), "Navegador"));
		frame.getContentPane().add(pnlNavegador);
		pnlNavegador.setLayout(null);
		
		btnPrimero = new JButton(new ImageIcon("imagenes3/navPri.jpg"));
		btnPrimero.setBounds(23, 21, 51, 52);
		pnlNavegador.add(btnPrimero);
		
		btnAtras = new JButton(new ImageIcon("imagenes3/navIzq.jpg"));
		btnAtras.setBounds(84, 21, 51, 52);
		pnlNavegador.add(btnAtras);
		
		btnAdelante = new JButton(new ImageIcon("imagenes3/navDer.jpg"));
		btnAdelante.setBounds(145, 21, 51, 52);
		pnlNavegador.add(btnAdelante);
		
		btnUltimo = new JButton(new ImageIcon("imagenes3/navUlt.jpg"));
		btnUltimo.setBounds(206, 21, 51, 52);
		pnlNavegador.add(btnUltimo);
		
		frame.setVisible(true);
		
	}
	
	
	// METODOS PARA HABILITAR Y DESHABILITAR CAMPOS Y BOTONES.
	public void habilitarBotonesNavegador(boolean bol){
		for(JButton btn : botonesNavegador){
			btn.setEnabled(bol);
		}
	}
	
	public void habilitarPanelLibros(boolean bol){
		
		for(JTextField campo : camposDeTexto) {
			campo.setEditable(bol);
		}
		chckbxPrestado.setEnabled(bol);
	}
	
	public void habilitarBotonesMantenimiento(){
		btnNuevo.setEnabled(false);
		btnEditar.setEnabled(false);
		btnBorrar.setEnabled(false);
		btnDeshacer.setEnabled(true);
		btnGuardar.setEnabled(true);
	}
	
	public void deshabilitarBotonesMantenimiento(){
		btnNuevo.setEnabled(true);
		btnEditar.setEnabled(true);
		btnBorrar.setEnabled(true);
		btnDeshacer.setEnabled(false);
		btnGuardar.setEnabled(false);
	}
	
	// BORRAR CONTENIDO DE CAJAS DE TEXTO
	public void borrarCamposTexto(){
		for(JTextField campo : camposDeTexto){
			campo.setText("");
		}
		chckbxPrestado.setEnabled(false);
	}
	
	public void mostrarLibro(int i){
		borrarCamposTexto();
		
		fldTitulo.setText(libros.get(i).getTitulo());
		fldAutor.setText(libros.get(i).getAutor());
		fldEditorial.setText(libros.get(i).getEditorial());
		fldIsbn.setText(libros.get(i).getIsbn());
		
		if(libros.get(i).getFechaPrestamo() != null)
		fldFecha.setText(libros.get(i).getFechaPrestamo().toString());
		
		chckbxPrestado.setSelected(libros.get(i).isPrestado());
		
		if(puntero == 0) {
			btnAtras.setEnabled(false);
			btnPrimero.setEnabled(false);
		}else{
			btnAtras.setEnabled(true);
			btnPrimero.setEnabled(true);
		}
		
		if(puntero == libros.size()-1) {
			btnAdelante.setEnabled(false);
			btnUltimo.setEnabled(false);
		}else{
			btnAdelante.setEnabled(true);
			btnUltimo.setEnabled(true);
		}
		
	}

	
	//EVENTOS
	public void eventos(){
		
		//EVENTOS DE NAVEGADOR
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				puntero = 0;
				mostrarLibro(puntero);
				
			}
		});
		
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				puntero = libros.size()-1;
				mostrarLibro(puntero);
				
			}
		});
		
		btnAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(libros.size()-1 < puntero+1) return;
				
				mostrarLibro(++puntero);
			}
		});
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(puntero == 0) return;
				
				mostrarLibro(--puntero);
			}
		});
		//EVENTOS DE BOTONES MANTENIMIENTO
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCamposTexto();
				habilitarBotonesMantenimiento();
				habilitarPanelLibros(true);
				habilitarBotonesNavegador(false);
				nuevo = true;
				fldFecha.setEnabled(false);
				chckbxPrestado.setEnabled(false);
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarBotonesMantenimiento();
				habilitarBotonesNavegador(false);
				chckbxPrestado.setEnabled(true);
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(nuevo) {
					LibrosServicios ls = new LibrosServicios();
					try {						
						boolean agregado = ls.agregarLibro
								(fldIsbn.getText(),fldTitulo.getText(), fldAutor.getText(), fldEditorial.getText());
						
						libros = ls.obtenerTodos();
						ls = null;
						
						if(agregado) JOptionPane.showMessageDialog(frame, "Libro agregado.");
						else JOptionPane.showMessageDialog(frame, "Error. Libro no agregado. Es probable que este ISBN ya esté siendo utilizado.");
						
						deshabilitarBotonesMantenimiento();
						habilitarPanelLibros(false);
						habilitarBotonesNavegador(true);
						borrarCamposTexto();
						puntero = (agregado) ? libros.size()-1 : puntero;
						mostrarLibro(puntero);
						nuevo = false;
						lblError.setText("");
						
						
					} catch (FileNotFoundException e1) {
						lblError.setText("Error. Fichero no encontrado.");
					} catch (IOException e1) {
						lblError.setText("Error relacionado con el fichero.");
					} catch (IsbnException e1) {
						lblError.setText("Error. ISBN invalido.");
					} catch (CamposVaciosException e1) {
						lblError.setText("Error. No pueden haber campos vacíos.");
					}
				}
				else{
					LibrosServicios ls = new LibrosServicios();
					boolean encontrado = ls.prestarLibro(fldIsbn.getText(), fldFecha.getText(), chckbxPrestado.isSelected());
					ls = null;
					
					if(encontrado) JOptionPane.showMessageDialog(frame, "El libro se ha editado");
					else JOptionPane.showMessageDialog(frame, "Libro no encontrado");
					
					deshabilitarBotonesMantenimiento();
					habilitarPanelLibros(false);
					habilitarBotonesNavegador(true);
					borrarCamposTexto();
					
					LibrosServicios libroService = new LibrosServicios();
					libros = libroService.obtenerTodos();
					libroService = null;
					
					mostrarLibro(puntero);
				}
			}
		});
		btnDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshabilitarBotonesMantenimiento();
				borrarCamposTexto();
				habilitarPanelLibros(false);
				habilitarBotonesNavegador(true);
				mostrarLibro(0);
				nuevo = false;
				lblError.setText("");
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String msg = "¿Seguro que quieres eliminar \"" + fldTitulo.getText() + "\"?";
				int confirmado = JOptionPane.showConfirmDialog(frame,
																msg,
																"Eliminar libro",
																JOptionPane.YES_NO_OPTION,
																JOptionPane.QUESTION_MESSAGE);
				
				if(confirmado == JOptionPane.NO_OPTION) return;
				
				LibrosServicios libroService = new LibrosServicios();
				
				boolean borrado = libroService.borrarLibro(fldIsbn.getText());
				libroService = null;
				
				if(borrado) JOptionPane.showMessageDialog(frame, "Libro eliminado.");
				else JOptionPane.showMessageDialog(frame, "Error. El libro no se ha podido eliminar.");
				
				libroService = new LibrosServicios();
				libros = libroService.obtenerTodos();
				puntero = 0;
				mostrarLibro(puntero);
				
			}
			
		});
	}
}
