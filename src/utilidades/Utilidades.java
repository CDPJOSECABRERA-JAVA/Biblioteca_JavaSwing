package utilidades;

public class Utilidades {
	public static boolean comprobarIsbn(String isbn){
		
		isbn = isbn.replaceAll("[\\s-]", "");
		if (isbn.length() != 13 || !isbn.matches("\\d{13}")) return false;
      

        int suma = 0;
        for (int i = 0; i < 12; i++) {
            int digito = Character.getNumericValue(isbn.charAt(i));

            if (i % 2 == 0) {
                suma += digito;
            } else {
                suma += digito * 3;
            }
        }

        int digitoControl = 10 - (suma % 10);
        if (digitoControl == 10) {
            digitoControl = 0;
        }


        int ultimoDigito = Character.getNumericValue(isbn.charAt(12));
        return digitoControl == ultimoDigito;
	}
}
