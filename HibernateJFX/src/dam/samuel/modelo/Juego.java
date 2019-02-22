package dam.samuel.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;

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
	@NotBlank
	@Size(min = 3, max = 25)
	private String nombre;

	@Enumerated(EnumType.STRING)
	private EstiloJuego estilo;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date publicacion;

	@Column(name = "descripcion")
	@Size(max = 250)
	private String descripcion;

	@Column(name = "precio")
	@NotNull
	@Digits(integer = 4, fraction = 2)
	private double precio;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "idJuego")
	@Valid
	private List<Desarrolla> listaDesarrolladores;

	@OneToMany
	@JoinColumn(name = "idJuego")
	@Valid
	private List<Valoracion> listaValoraciones;

	@Transient
	private int valoracion;

	public Juego(String nombre, EstiloJuego estilo, LocalDate publicacion, String descripcion, double precio) {
		this.nombre = nombre;
		this.estilo = estilo;
		this.publicacion = Date.from(publicacion.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
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

	public void setPublicacion(LocalDate publicacion) {
		this.publicacion = Date.from(publicacion.atStartOfDay(ZoneId.systemDefault()).toInstant());
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

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion() {
		int totalVotos = getListaValoraciones().size();
		int votosPositivos = 0;

		for (Valoracion valoracion : listaValoraciones) {
			if (valoracion.isPositivo()) {
				votosPositivos++;
			}
		}

		if (totalVotos != 0) {
			valoracion = votosPositivos * 100 / totalVotos;
		} else {
			valoracion = 0;
		}
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
