package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public interface ILibro {

	public List<Libro> obtenerTodos();
	
	public boolean agregarLibro(String isbn,String titulo, String autor, String editorial) 
			throws FileNotFoundException, IOException, IsbnException, CamposVaciosException;
	
	public boolean prestarLibro(String isbn, String fechaPrestamo, boolean prestado);
	
	public boolean borrarLibro(String isbn);

}
