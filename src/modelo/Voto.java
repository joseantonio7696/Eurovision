package modelo;

import excepciones.CampoObligatorioException;
import excepciones.VotoErroneoException;

public class Voto {
	
	private String nombreUsuario;
	private String pais;

	public Voto() {
		
		this.nombreUsuario=null;
		this.pais=null;
	
		
	}
	
	
	

	public Voto(String nombreUsuario, String pais) throws CampoObligatorioException, VotoErroneoException {
		super();
		this.setNombreUsuario(nombreUsuario);
		this.setPais(pais);
	}




	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) throws CampoObligatorioException, VotoErroneoException {
		
		if (pais.equals("")) {
			
			throw new CampoObligatorioException("VOTO");
			
		}else {
			
			int numPais=Integer.parseInt(pais);
			
			if (numPais>=0 && numPais<5) {
				
				this.pais = pais;
				
			}else {
				throw new VotoErroneoException();
			}
		}
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Voto other = (Voto) obj;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  nombreUsuario + ","+ pais;
	}
	
	
	
	
	

}
