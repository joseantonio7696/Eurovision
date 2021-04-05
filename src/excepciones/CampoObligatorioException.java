package excepciones;

public class CampoObligatorioException extends Exception {

	public CampoObligatorioException(String campo) {
		
		super("El "+campo+" es un campo obligatorio de rellenar");
	}

}
