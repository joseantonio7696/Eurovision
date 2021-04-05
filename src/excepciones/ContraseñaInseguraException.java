package excepciones;

public class ContraseñaInseguraException extends Exception {

	public ContraseñaInseguraException() {
		
		super("LA CONTRASEÑA NO CUMPLE CON REQUISITOS DE CONTRASEÑA SEGURA");
	}

}
