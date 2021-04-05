package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import excepciones.CampoObligatorioException;
import excepciones.VotoErroneoException;
import modelo.Usuario;
import modelo.Voto;

public class VotoController {
	
	List<Voto>concursoList=new ArrayList<Voto>();
	
	public VotoController() throws IOException, CampoObligatorioException, VotoErroneoException {
		
		File file=new File("concurso.txt");
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileReader fr=new FileReader("concurso.txt");
		BufferedReader br=new BufferedReader(fr);
		
		
		String nombreUsuario; 
		String pais;
		
		String [] votos=null;
		String linea="";
		
		while ((linea=br.readLine())!=null) {
			
			votos=linea.split(",");
			
			
			nombreUsuario=votos[0];
			pais=votos[1];
			
			Voto voto=new Voto(nombreUsuario, pais);
			
			concursoList.add(voto);
			
			voto=null;
			
		}
		
		
		br.close();br=null;
		fr.close();fr=null;
		
		
	}
	
	public boolean agregarVoto(String nombreUsuario,String pais) throws CampoObligatorioException, VotoErroneoException {
		boolean agregado=false;
		
		Voto voto=new Voto(nombreUsuario, pais);
		
		if (!concursoList.contains(voto)) {
			concursoList.add(voto);
			agregado=true;
		}
		
		
		return agregado;
	}
	
	public boolean modificarVoto(String nombreUsuario,String pais) throws CampoObligatorioException, VotoErroneoException {
		boolean modificado=false;
		
		for (Voto voto2 : concursoList) {
			
			if (voto2.getNombreUsuario().equals(nombreUsuario)) {
				voto2.setPais(pais);
				modificado=true;
				break;
			}
		}
		
		
		return modificado;
	}
	
	public List<Voto> ganadoresConcurso(String paisGanador){
		
		List<Voto>ganadores=new ArrayList<Voto>();
		
		for (Voto voto : concursoList) {
			if (voto.getPais().equals(paisGanador)) {
				ganadores.add(voto);
			}
		}
		
		if (ganadores.size()==0) {
			ganadores=null;
		}
		
		return ganadores;
		
	}
	
	public void salvarConcurso() throws IOException {
		
		FileWriter fw=new FileWriter("concurso2.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		
		for (Voto	linea  : concursoList) {
			
			bw.write(linea.toString());
			bw.newLine();
			
		}
		
		bw.close();bw=null;
		fw.close();fw=null;
		
	}

	public List<Voto> getConcursoList() {
		return concursoList;
	}

	public void setConcursoList(List<Voto> concursoList) {
		this.concursoList = concursoList;
	}
	
	

}
