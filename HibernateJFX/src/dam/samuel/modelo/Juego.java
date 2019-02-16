package dam.samuel.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "juego")
public class Juego implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum EstiloJuego {
		rol, accion, aventura, estrategia
	}

	@Id
	@Column(name = "idJuego")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idJuego;

	@Column(name = "nombre")
	private String nombre;

	@Enumerated(EnumType.STRING)
	private EstiloJuego estilo;

	@Temporal(TemporalType.DATE)
	private Date publicacion;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "precio")
	private double precio;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "idJuego")
	private List<Desarrolla> listaDesarrolladores;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "idJuego")
	private List<Valoracion> listaValoraciones;

	public Juego(String nombre, EstiloJuego estilo, Date publicacion, String descripcion, double precio) {
		this.nombre = nombre;
		this.estilo = estilo;
		this.publicacion = publicacion;
		this.descripcion = descripcion;
		this.precio = precio;
		this.listaDesarrolladores = new ArrayList<>();
		this.listaValoraciones = new ArrayList<>();
	}

	public Juego() {
		super();
	}

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstiloJuego getEstilo() {
		return estilo;
	}

	public void setEstilo(EstiloJuego estilo) {
		this.estilo = estilo;
	}

	public Date getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Date publicacion) {
		this.publicacion = publicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Desarrolla> getListaDesarrolladores() {
		return listaDesarrolladores;
	}

	public void setListaDesarrolladores(List<Desarrolla> listaDesarrolladores) {
		this.listaDesarrolladores = listaDesarrolladores;
	}

	public List<Valoracion> getListaValoraciones() {
		return listaValoraciones;
	}

	public void setListaValoraciones(List<Valoracion> listaValoraciones) {
		this.listaValoraciones = listaValoraciones;
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