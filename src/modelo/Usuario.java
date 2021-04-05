package modelo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import excepciones.CampoObligatorioException;
import excepciones.ContraseñaInseguraException;
import excepciones.DniException;

public class Usuario implements Comparable<Usuario> {

	private String nombre;
	private String apellidos;
	private String dni;
	private String poblacion;
	private String nombreUsuario;
	private String pass;
	private String telefono;
	private java.sql.Date fechaNacimiento;

	public Usuario(String nombre, String apellidos, String dni, String poblacion, String nombreUsuario, String pass,
			String telefono, String fechaNacimiento) throws CampoObligatorioException, ContraseñaInseguraException,
			ParseException, NumberFormatException, DniException {
		super();
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setDni(dni);
		this.setPoblacion(poblacion);
		this.setNombreUsuario(nombreUsuario);
		this.setPass(pass);
		this.setTelefono(telefono);
		this.setFechaNacimiento(fechaNacimiento);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws CampoObligatorioException {

		if (nombre.equals("")) {
			throw new CampoObligatorioException("NOMBRE");
		}
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) throws CampoObligatorioException {
		if (apellidos.equals("")) {
			throw new CampoObligatorioException("APELLIDOS");
		}
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) throws CampoObligatorioException, DniException, NumberFormatException {
		if (dni.equals("")) {
			throw new CampoObligatorioException("DNI");
		} else {

			String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";

			if (dni.length() == 9) {

				dni = dni.toUpperCase();

				int numDni = Integer.parseInt(dni.substring(0, 8));

				char letraDni = dni.charAt(8); // LO CONVERTIMOS A MAYUSCULAS PORQUE NUESTRA CADENA DE COMPROBACION ES
												// MAYUSCULA

				int resto = numDni % 23;

				char letra = letrasDNI.charAt(resto);

				if (letra == letraDni) {

					this.dni = dni;

				} else {
					throw new DniException();

				}

			} else {
				throw new DniException();
			}

		}

	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) throws CampoObligatorioException {
		if (poblacion.equals("")) {
			throw new CampoObligatorioException("POBLACION");
		}
		this.poblacion = poblacion;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) throws CampoObligatorioException {
		if (nombreUsuario.equals("")) {
			throw new CampoObligatorioException("NOMBRE DE USUARIO");
		}
		this.nombreUsuario = nombreUsuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) throws ContraseñaInseguraException, CampoObligatorioException {

		boolean tieneMay = false, tieneDig = false, tieneMin = false, tieneEsp = false;

		char caracter = ' ';

		if (pass.equals("")) {

			throw new CampoObligatorioException("DNI");

		} else {

			if (pass.length() >= 8) {

				for (int i = 0; i < pass.length(); i++) {

					caracter = pass.charAt(i);

					if (Character.isDigit(caracter)) {

						tieneDig = true;

					}

					if (Character.isLowerCase(caracter)) {

						tieneMin = true;

					}

					if (Character.isUpperCase(caracter)) {

						tieneMay = true;

					}

					if (!Character.isDigit(caracter) && !Character.isLetter(caracter)) {

						tieneEsp = true;
					}

				}

				if (tieneEsp && tieneDig && tieneMay && tieneMin) {

					this.pass = pass;
				} else {
					throw new ContraseñaInseguraException();
				}
			} else {
				throw new ContraseñaInseguraException();
			}
		}

	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) throws CampoObligatorioException {

		if (telefono.equals("")) {
			throw new CampoObligatorioException("TELEFONO");
		}
		this.telefono = telefono;
	}

	public java.sql.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) throws ParseException, CampoObligatorioException {

		if (fechaNacimiento.equals("")) {
			throw new CampoObligatorioException("FECHA DE NACIMIENTO");
		} else {

			java.util.Date fechaUtil = null;

			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			formateador.setLenient(false);

			fechaUtil = formateador.parse(fechaNacimiento);

			this.fechaNacimiento = new java.sql.Date(fechaUtil.getTime());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre + "," + apellidos + "," + dni + "," + poblacion + "," + nombreUsuario + "," + pass + ","
				+ telefono + "," + fechaNacimiento;
	}

	@Override
	public int compareTo(Usuario usuario) {
		
		return fechaNacimiento.compareTo(usuario.getFechaNacimiento());
	}
	
	

}
