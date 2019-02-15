package dam.samuel.proyecto.modelo;

import java.util.ArrayList;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class Empresa {

	private int idEmpresa;
	private String nombre;
	private ArrayList<Juego> juegos;

	public Empresa(int idEmpresa, String nombre, ArrayList<Juego> juegos) {
		super();
		this.idEmpresa = idEmpresa;
		this.nombre = nombre;
		this.juegos = juegos;
	}

	public Empresa() {
		super();
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Juego> getJuegos() {
		return juegos;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setJuegos(ArrayList<Juego> juegos) {
		this.juegos = juegos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEmpresa;
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
		Empresa other = (Empresa) obj;
		if (idEmpresa != other.idEmpresa)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", nombre=" + nombre + "]";
	}
}
