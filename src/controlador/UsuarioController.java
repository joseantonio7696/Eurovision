package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import excepciones.CampoObligatorioException;
import excepciones.ContraseñaInseguraException;
import excepciones.DniException;
import modelo.Usuario;
import modelo.Voto;

public class UsuarioController {
	
	List<Usuario>registro=new ArrayList<Usuario>();

	public UsuarioController() throws IOException, NumberFormatException, CampoObligatorioException, ContraseñaInseguraException, ParseException, DniException {
		
		File file=new File("oyentes.txt");
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileReader fr=new FileReader("oyentes.txt");
		BufferedReader br=new BufferedReader(fr);
		
		String nombre; 
		String apellidos;
		String dni;
		String poblacion; 
		String nombreUsuario; 
		String pass ;
		String telefono; 
		String fechaNacimiento;
		
		String [] usuario=null;
		String linea="";
		
		while ((linea=br.readLine())!=null) {
			
			usuario=linea.split(",");
			
			nombre=usuario[0];
			apellidos=usuario[1];
			dni=usuario[2];
			poblacion=usuario[3];
			nombreUsuario=usuario[4];
			pass=usuario[5];
			telefono=usuario[6];
			fechaNacimiento=usuario[7];
			
			Usuario datoUser=new Usuario(nombre, apellidos, dni, poblacion, nombreUsuario, pass, telefono, fechaNacimiento);
			
			registro.add(datoUser);
			
			datoUser=null;
			
		}
		
		
		br.close();br=null;
		fr.close();fr=null;
		
		
		
	}
	
	public boolean buscarUsuario(String dni,String nombreUsuario) {
		
		boolean existe=false;
		
		for (Usuario usuario : registro) {
			
			if (usuario.getDni().equals(dni)||usuario.getNombreUsuario().equals(nombreUsuario)) {
				
				existe=true;
				break;
				
			}
		}
		
		
		return existe;
	}
	
	public boolean registrarUsuario(String nombre,String apellidos,String dni,String poblacion,String nombreUsuario,String pass,String telefono,String fechaNacimiento) throws NumberFormatException, CampoObligatorioException, ContraseñaInseguraException, ParseException, DniException {
		
		boolean registrado=false;
		boolean existe=false;
		
		existe=buscarUsuario(dni, nombreUsuario);
		
		if (existe==false) {
			Usuario usuario=new Usuario(nombre, apellidos, dni, poblacion, nombreUsuario, pass, telefono, fechaNacimiento);
			registro.add(usuario);
			registrado=true;
		}
		
		
		return registrado;
		
	}
	
public boolean logueaUsuario(String nombreUsuario,String pass) {
		
		boolean existe=false;
		
		for (Usuario usuario : registro) {
			
			if (usuario.getPass().equals(pass)&&usuario.getNombreUsuario().equals(nombreUsuario)) {
				
				existe=true;
				break;
				
			}
		}
		
		
		return existe;
	}
	
public List<Usuario> concursantesAciertos(List<Voto> concursantesAcertados){
	
	List<Usuario>usuariosAcertados=new ArrayList<Usuario>();
	
	for (Voto usuario : concursantesAcertados) {
		
		for (Usuario usuario2 : registro) {
			
			if (usuario.getNombreUsuario().equals(usuario2.getNombreUsuario())) {
				
				usuariosAcertados.add(usuario2);
				break;
				
			}
			
		}
		
		
	}
	
	if (usuariosAcertados.size()==0) {
		usuariosAcertados=null;
	}
	
	return usuariosAcertados;
	
}

	public void salvarUsuarios() throws IOException {
		
		FileWriter fw=new FileWriter("oyentes2.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		
		for (Usuario usuario : registro) {
			
			bw.write(usuario.toString());
			bw.newLine();
			
		}
		
		bw.close();bw=null;
		fw.close();fw=null;
		
	}

	public List<Usuario> getRegistro() {
		return registro;
	}

	public void setRegistro(List<Usuario> registro) {
		this.registro = registro;
	}
	
	
	
	

}
