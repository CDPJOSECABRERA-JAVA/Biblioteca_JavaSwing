package servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import interfaces.ILibro;
import modelo.Libro;
import repositorio.LibroRepositorio;

public class LibrosServicios implements ILibro{
	
	List<Libro> libros;

	
	@Override
	public List<Libro> obtenerTodos() {
		
		LibroRepositorio libroRepositorio = new LibroRepositorio();
		libros = libroRepositorio.cargarLibros();
		libroRepositorio = null;
		
		return libros;
	}
	
	public boolean agregarLibro(String isbn,String titulo, String autor, String editorial) 
	throws FileNotFoundException, IOException, IsbnException, CamposVaciosException{
		
		Libro l = new Libro(isbn,titulo,autor,editorial);
		LibroRepositorio libroRepositorio = new LibroRepositorio();
		boolean agregado = libroRepositorio.agregarLibro(l);
		libroRepositorio = null;
		
		return agregado;
		
	}

	@Override
	public boolean prestarLibro(String isbn, String fechaPrestamo, boolean prestadoChckbx) {
		
		LibroRepositorio libroRepositorio = new LibroRepositorio();
		boolean prestado = libroRepositorio.prestarLibro(isbn, fechaPrestamo, prestadoChckbx);
		libroRepositorio = null;
		return prestado;

	}

	@Override
	public boolean borrarLibro(String isbn) {
		LibroRepositorio libroRepositorio = new LibroRepositorio();
		boolean borrado = libroRepositorio.borrarLibro(isbn);
		libroRepositorio = null;
		return borrado;
	}
	

}
