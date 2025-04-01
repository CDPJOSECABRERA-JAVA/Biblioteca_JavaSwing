package excepciones;

public class CamposVaciosException extends Exception{
	
	public CamposVaciosException() {
		super("Este campo no puede estar vacío.");
	}
}
