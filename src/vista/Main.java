package vista;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import controlador.UsuarioController;
import controlador.VotoController;
import excepciones.CampoObligatorioException;
import excepciones.ContraseñaInseguraException;
import excepciones.DniException;
import excepciones.VotoErroneoException;
import modelo.Usuario;
import modelo.Voto;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		String nombre = "JOSE ANTONIO";
		String apellidos = "MARQUEZ";
		String dni = "49190989S";
		String poblacion = "SANLUCAR DE BARRAMEDA";
		String nombreUsuario = "JOSEANTONIO7696";
		String pass = "1Aa!1234";
		String telefono = "677259520";
		String fechaNacimiento = "1996-06-07";

		try {
			Usuario usuario = new Usuario(nombre, apellidos, dni, poblacion, nombreUsuario, pass, telefono,
					fechaNacimiento);
			// System.out.println(usuario.toString());
		} catch (CampoObligatorioException | ContraseñaInseguraException | ParseException | NumberFormatException
				| DniException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

		UsuarioController registro = null;

		List<Usuario> lista = new ArrayList<Usuario>();

		try {
			registro = new UsuarioController();

			/*
			 * lista = registro.getRegistro();
			 * 
			 * for (Usuario usuario : lista) {
			 * 
			 * System.out.println(usuario);
			 * 
			 * }
			 */
		} catch (NumberFormatException | IOException | CampoObligatorioException | ContraseñaInseguraException
				| ParseException | DniException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

		System.out.println();

		

		nombreUsuario = "JOSEANTONIO7696";
		String pais = "0";

		try {
			Voto voto = new Voto(nombreUsuario, pais);
			// System.out.println(voto);
		} catch (CampoObligatorioException | VotoErroneoException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

		VotoController concurso = null;

		List<Voto> listaConcurso = new ArrayList<Voto>();

		System.out.println();

		try {
			concurso = new VotoController();

			listaConcurso = concurso.getConcursoList();
			/*
			 * for (Voto voto : listaConcurso) { System.out.println(voto); }
			 */
		} catch (IOException | CampoObligatorioException | VotoErroneoException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

		int opcion = 0;
		
		String paisGanador="";
		
		String paises[] = { "España", "Suiza", "Belgica", "Alemania", "Francia" };
		
		List<Usuario>datosGanadores=new ArrayList<Usuario>();

		do {

			System.out.println(
					"1.- Registrarse en la plataforma ( dni y usuario deben ser únicos , pass contraseña segura");
			System.out.println("2.- Votar concurso Eurovisión ( me pedirá usuario y contraseña )");
			System.out.println("3.- Generar Pais Ganador Eurovisión. (mostrar)");
			System.out.println("4.- Mostrar Concursantes que han acertado. (Ordenado fecha de nacimiento )");
			System.out.println("5.- Mostrar ganador (Todos sus datos )");
			System.out.println("6.- Salir de la aplicacion");

			System.out.println("INTRODUZCA UNA OPCION DEL MENU");

			Scanner entrada = new Scanner(System.in);

			opcion = entrada.nextInt();

			switch (opcion) {
			case 1:

				nombre = "MIGUEL ANGEL";
				apellidos = "TEJERO";
				dni = "31589999M";
				poblacion = "SANLUCAR DE BARRAMEDA";
				nombreUsuario = "MIGUELANG";
				pass = "1Aa!1234";
				telefono = "665540180";
				fechaNacimiento = "1983-04-17";

				boolean registrado = false;

				try {

					registrado = registro.registrarUsuario(nombre, apellidos, dni, poblacion, nombreUsuario, pass,
							telefono, fechaNacimiento);

					if (registrado) {
						System.out.println("EL USUARIO SE CREO CORRECTAMENTE");

						lista = registro.getRegistro();

						for (Usuario usuario : lista) {

							System.out.println(usuario);

						}

					} else {
						System.err.println("EL USUARIO YA EXISTE EN EL REGISTRO");
					}
				} catch (NumberFormatException | CampoObligatorioException | ContraseñaInseguraException
						| ParseException | DniException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}

				break;

			case 2:

				System.out.println("INTRODUZCA SU USUARIO");
				nombreUsuario = "MIGUELANG";
				System.out.println("INTRODUZCA SU CONTRASEÑA");
				pass = "1Aa!1234";

				boolean logueaUsuario = false;

				logueaUsuario = registro.logueaUsuario(nombreUsuario, pass);

				if (logueaUsuario) {

					Scanner leer = new Scanner(System.in);
					int eleccion = 0;
					System.out.println("1.- Votar");
					System.out.println("2.- Modificar Voto");
					eleccion = leer.nextInt();

					leer = null;

					switch (eleccion) {
					
					case 1:
						
						System.out.println("ELIJE UN NUMERO SEGUN EL PAIS QUE QUIERAS");
						for (int i = 0; i < paises.length; i++) {
							System.out.println(i + "-" + paises[i]);
						}
						
						nombreUsuario = "MIGUELANG";
						String voto = "0";

						boolean votado = false;

						try {
							votado = concurso.agregarVoto(nombreUsuario, pais);

							if (votado) {
								System.out.println("SU VOTO SE AGREGO CORRECTAMENTE");
							} else {
								System.err.println(
										"USTED YA HA VOTADO Y TIENE QUE MODIFICAR SU VOTO EN EL OTRO APARTADO");
							}

						} catch (CampoObligatorioException | VotoErroneoException e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
						}

						break;

					case 2:
						
						System.out.println("ELIJE UN NUMERO SEGUN EL PAIS QUE QUIERAS");
						for (int i = 0; i < paises.length; i++) {
							System.out.println(i + "-" + paises[i]);
						}

						
						nombreUsuario = "MIGUELANG";
						voto = "1";

						boolean modificado = false;

						try {

							modificado = concurso.modificarVoto(nombreUsuario, pais);

							if (modificado) {
								System.out.println("SU VOTO SE MODIFICO CORRECTAMENTE");
							} else {
								System.err.println(
										"SU VOTO NO ESTA EN LA BASE DE DATOS, POR FAVOR VOTE PRIMERO PARA MODIFICAR SU VOTO");
							}
						} catch (CampoObligatorioException | VotoErroneoException e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
						}

						break;

					default:
						System.err.println("INTRODUZCA UNA OPCION CORRECTA");
						break;
					}
				} else {
					System.err.println("EL USUARIO O CONTRASEÑA NO SON CORRECTOS");
				}

				break;

			case 3:
				
				int numAleatorio=0;
				
				//System.out.println(paises.length);
				
				numAleatorio = (int) Math.floor(Math.random()*((paises.length-1)-0+1)+0);
				
				//System.out.println(numAleatorio);
				
				System.out.println(paises[numAleatorio]);
				
				paisGanador=Integer.toString(numAleatorio);

				break;

			case 4:
				
				if (paisGanador.equals("")) {
					System.err.println("PRIMERO HAY QUE REALIZAR EL SORTEO DEL PAIS GANADOR");
				}else {
				
				List<Voto>ganadores=new ArrayList<Voto>();
				
				ganadores=concurso.ganadoresConcurso(paisGanador);	//BUSCAMOS LOS GANADORES DEL CONCURSO COMPARANDO SU VOTO CON EL NUMERO ALEATORIO GENERADO
				
				//{ "España", "Suiza", "Belgica", "Alemania", "Francia" };
				
				/*
				for (Voto voto : ganadores) {
					System.out.println(voto);
				}
				*/
				
				
				
				datosGanadores=registro.concursantesAciertos(ganadores);	//AQUI SACAMOS LOS CONCURSANTES DEL REGISTRO PRINCIPAL DESORDENADOS
				
				if (datosGanadores!=null) {
					
					Collections.sort(datosGanadores);	//NOS ORDENA POR EL METODO POR DEFECTO QUE HEMOS IMPLEMENTADO CON EL COMPARABLE
					
					for (Usuario usuario : datosGanadores) {
						System.out.println(usuario);
					}
					
				}else {
					System.err.println("NO HAY NINGUN CONCURSANTE GANADOR");
				}
				
				}
				break;

			case 5:
				
				
				
				int numAleatorioGanad = (int) Math.floor(Math.random()*((datosGanadores.size()-1)-0+1)+0);
				
				
				
				if (datosGanadores!=null) {
					System.out.println(datosGanadores.get(numAleatorioGanad));
				}else {
					System.out.println("NO HAY NINGUN GANADOR");
				}
				

				break;

			case 6:
				
				
				try {
					registro.salvarUsuarios();
					concurso.salvarConcurso();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				
				
				System.out.println("GRACIAS POR UTILIZAR NUESTRA APLICACION");
				
				

				break;

			default:
				System.err.println("INTRODUZCA UN VALOR DEL MENU POR FAVOR");
				break;
			}

		} while (opcion != 6);

	}

}
