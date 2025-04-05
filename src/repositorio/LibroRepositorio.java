package repositorio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import modelo.Libro;

public class LibroRepositorio {
	
	private List<Libro> libros;
	
	public LibroRepositorio(){
		//crearFichero();
		//cargarLibros();
	}
	
	private void crearFichero() {
		
		libros = new ArrayList<Libro>();
		
		try {
			Libro libro;
			libros.add(new Libro("9788466320535", "Cien años de soledad", "Gabriel García Márquez",
					"Editorial Sudamericana"));
			libros.add(new Libro("9788408167877", "El Principito", "Antoine de Saint-Exupéry", "Editorial Salamandra"));
			libros.add(new Libro("9788497592208", "1984", "George Orwell", "Editorial Debolsillo"));
			libro = new Libro("9788420471839", "La sombra del viento", "Carlos Ruiz Zafón", "Editorial Planeta",
			"27/03/2025","17/04/2025","true");
			libros.add(libro);
			libro = new Libro("9788498381498", "Harry Potter y la piedra filosofal", "J.K. Rowling",
			"Editorial Salamandra","27/02/2025","17/03/2025","true");
			libro.setPrestado(true);
			libros.add(libro);
			libros.add(new Libro("9788497593793", "Orgullo y prejuicio", "Jane Austen", "Editorial Debolsillo"));
			libros.add(
			new Libro("9788408139249", "El nombre del viento", "Patrick Rothfuss", "Editorial Plaza & Janés"));
			libros.add(new Libro("9788497592437", "Crónica de una muerte anunciada", "Gabriel García Márquez",
			"Editorial Debolsillo"));
			libros.add(new Libro("9788401328510", "Los pilares de la Tierra", "Ken Follett", "Editorial Debolsillo"));
			libro = null;
	
		} catch (IsbnException | CamposVaciosException e) {
			System.out.println("Error en la inserción de libros.");
			e.printStackTrace();
		}
				
		
		try(FileOutputStream fos = new FileOutputStream("libros.dat"); ObjectOutputStream oos = new ObjectOutputStream(fos)){
			
			oos.writeObject(libros);
			
		}catch(IOException e) {
			System.out.println("Error en la creación del fichero.");
		}catch(Exception e) {
			System.out.println("Error desconocido:");
			System.out.println(e.getMessage());
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Libro> cargarLibros() {
		
		try(FileInputStream fis = new FileInputStream("libros.dat"); ObjectInputStream ois = new ObjectInputStream(fis)){
			
			libros = (List<Libro>) ois.readObject();
			
		}catch(IOException e){
			System.out.println("Error en la lectura del fichero.");
		}catch(Exception e){
			System.out.println("Error desconocido:");
			System.out.println(e.getMessage());
		}
		
		return libros;
	}

	
	public List<Libro> obtenerTodos() {
		return libros;
	}
	
	public void guardarLibros() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("libros.dat"));
		oos.writeObject(libros);
		oos.close();
	}
	
	public boolean agregarLibro(Libro l) throws FileNotFoundException, IOException {
		libros = cargarLibros();
		
		if(libros.contains(l)) return false;
		
		libros.add(l);
		guardarLibros();
		return true;
	}

	public boolean prestarLibro(String isbn, String fechaPrestamo, boolean chckbxPrestado) {
		
		libros = cargarLibros();
		
		Libro libro = buscarLibro(isbn);
		
		if(libro == null) return false;
		
		LocalDate ahora = LocalDate.now();
		
		if(!libro.isPrestado() && chckbxPrestado) {
			libro.setPrestado(true);
			libro.setFechaPrestamo(ahora);
			libro.setFechaDevolucion(ahora.plusDays(10));
		}
		else if(libro.isPrestado() && !chckbxPrestado){
			libro.setPrestado(false);
			libro.setFechaPrestamo(null);
			libro.setFechaDevolucion(null);
		}else if(libro.isPrestado() && chckbxPrestado){
			libro.setFechaDevolucion(ahora.plusDays(3));
			System.out.println("Fecha aumentada en 3 dias");
		}
		
		try {
			guardarLibros();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
			
		
		
		return true;
		
	}
	public boolean borrarLibro(String isbn) {
		libros = cargarLibros();
		Libro libro = buscarLibro(isbn);
		
		if(libro == null) return false;
		
		try {
			libros.remove(libro);
			guardarLibros();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public Libro buscarLibro(String isbn){
		
		return  libros.stream()
				.filter(l -> l.getIsbn().equals(isbn))
				.findFirst()
				.orElse(null);
	}


}
