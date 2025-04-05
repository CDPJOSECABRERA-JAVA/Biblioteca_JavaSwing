package vista;

import java.io.IOException;
import java.util.List;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;
import repositorio.LibroRepositorio;
import servicios.LibrosServicios;
import vista.swing.FrmLibro;

public class Main {

	public static void main(String[] args) {
		
		//LibroRepositorio lr = new LibroRepositorio();
		
		FrmLibro frm = new FrmLibro();
		
		/*
		LibrosServicios ls = new LibrosServicios();
		for(Libro li : ls.obtenerTodos()){
			System.out.println(li);
			System.out.println();
		}
		*/
		/*
		System.out.println("===========================================================");
		
		String isbn = "9788419988508";
		String titulo = "1000 Chistes del Moyano";
		String autor = "Autor";
		String editorial = "Editorial";

		try {
			ls.agregarLibro(isbn, titulo, autor, editorial);
		} catch (IOException | IsbnException | CamposVaciosException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		for(Libro li : ls.obtenerTodos()){
			System.out.println(li);
			System.out.println();
		}
		*/
	}

}
