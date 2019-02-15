package dam.samuel.proyecto.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class Juego {

	public enum EstiloJuego {
		rol, accion, aventura, estrategia
	}

	private int idJuego;
	private String nombre;
	private EstiloJuego estilo;
	private Date publicacion;
	private String descripcion;
	private double precio;
	private ArrayList<Empresa> desarrolladores;
	private ArrayList<Valoracion> valoraciones;

	public Juego(int idJuego, String nombre, EstiloJuego estilo, Date publicacion, String descripcion, double precio,
			ArrayList<Empresa> desarrolladores, ArrayList<Valoracion> valoraciones) {
		super();
		this.idJuego = idJuego;
		this.nombre = nombre;
		this.estilo = estilo;
		this.publicacion = publicacion;
		this.descripcion = descripcion;
		this.precio = precio;
		this.desarrolladores = desarrolladores;
		this.valoraciones = valoraciones;
	}

	public Juego() {
		super();
	}

	public int getIdJuego() {
		return idJuego;
	}

	public String getNombre() {
		return nombre;
	}

	public EstiloJuego getEstilo() {
		return estilo;
	}

	public Date getPublicacion() {
		return publicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public ArrayList<Empresa> getDesarrolladores() {
		return desarrolladores;
	}

	public ArrayList<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEstilo(EstiloJuego estilo) {
		this.estilo = estilo;
	}

	public void setPublicacion(Date publicacion) {
		this.publicacion = publicacion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setDesarrolladores(ArrayList<Empresa> desarrolladores) {
		this.desarrolladores = desarrolladores;
	}

	public void setValoraciones(ArrayList<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idJuego;
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
		Juego other = (Juego) obj;
		if (idJuego != other.idJuego)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Juego [idJuego=" + idJuego + ", nombre=" + nombre + ", estilo=" + estilo + ", publicacion="
				+ publicacion + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}
}
