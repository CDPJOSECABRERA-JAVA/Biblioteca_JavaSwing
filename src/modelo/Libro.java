package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import excepciones.CamposVaciosException;
import excepciones.IsbnException;
import utilidades.Utilidades;

public class Libro implements Serializable{
	private static final long serialVersionUID = 1L;

	private String isbn, titulo, autor, editorial;
	private LocalDate fechaPrestamo, fechaDevolucion;
	private boolean prestado;
	
	public Libro(){}
	
	public Libro(String isbn, String titulo, String autor, String editorial) 
		throws IsbnException, CamposVaciosException {
		
		setIsbn(isbn);
		setTitulo(titulo);
		setAutor(autor);
		setEditorial(editorial);
		
	}
	public Libro(String isbn, String titulo, String autor, String editorial, String strFechaPrestamo,
			String strFechaDevolucion, String strPrestado) 
		throws IsbnException, CamposVaciosException {
		
		setIsbn(isbn);
		setTitulo(titulo);
		setAutor(autor);
		setEditorial(editorial);
		setPrestado(Boolean.parseBoolean(strPrestado));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		setFechaPrestamo(LocalDate.parse(strFechaPrestamo, formatter));
		
		setFechaDevolucion(LocalDate.parse(strFechaDevolucion, formatter));
		
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) throws CamposVaciosException, IsbnException {
		if(isbn.length() == 0) throw new CamposVaciosException();
		if(!Utilidades.comprobarIsbn(isbn)) throw new IsbnException();
		
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) throws CamposVaciosException {
		if(titulo.length() == 0) throw new CamposVaciosException();
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) throws CamposVaciosException {
		if(autor.length() == 0) throw new CamposVaciosException();
		this.autor = autor;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) throws CamposVaciosException {
		if(editorial.length() == 0) throw new CamposVaciosException();
		this.editorial = editorial;
	}
	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public boolean isPrestado() {
		return prestado;
	}
	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "ISBN:" + isbn + "\nTitulo: " + titulo + "\nAutor: " + autor + "\nEditorial: " + editorial
				+ "\nFechaPrestamo: " + fechaPrestamo + "\nFechaDevolucion: " + fechaDevolucion + "\nPrestado: " + prestado;
	}
	
	
	
}
