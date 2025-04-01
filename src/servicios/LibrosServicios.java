package servicios;

import java.util.List;

import interfaces.ILibro;
import modelo.Libro;
import repositorio.LibroRepositorio;

public class LibrosServicios implements ILibro{
	
	List<Libro> libros;
	
	@Override
	public List<Libro> obtenerTodos() {
		
		LibroRepositorio libroRepositorio = new LibroRepositorio();
		List<Libro> libros = libroRepositorio.cargarLibros();
		libroRepositorio = null;
		
		return libros;
	}

}
