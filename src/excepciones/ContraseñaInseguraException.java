package excepciones;

public class Contrase�aInseguraException extends Exception {

	public Contrase�aInseguraException() {
		
		super("LA CONTRASE�A NO CUMPLE CON REQUISITOS DE CONTRASE�A SEGURA");
	}

}
