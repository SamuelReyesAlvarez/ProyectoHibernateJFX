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

	/**
	 * Establece los diferentes estilos de juego disponibles para la aplicación
	 */
	public enum EstiloJuego {
		rol, accion, aventura, estrategia
	}

	/**
	 * Dato autogenerado que identifica de forma única al objeto entre los de su
	 * clase
	 */
	@Id
	@Column(name = "idJuego")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idJuego;

	/**
	 * Nombre del Juego
	 */
	@Column(name = "nombre")
	@NotBlank
	@Size(min = 3, max = 25)
	private String nombre;

	/**
	 * Estilo de juego
	 */
	@Enumerated(EnumType.STRING)
	private EstiloJuego estilo;

	/**
	 * Fecha en la que se publicó
	 */
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date publicacion;

	/**
	 * Breve descripción del juego
	 */
	@Column(name = "descripcion")
	@Size(max = 250)
	private String descripcion;

	/**
	 * Precio de venta en euros
	 */
	@Column(name = "precio")
	@NotNull
	@Digits(integer = 4, fraction = 2)
	private double precio;

	/**
	 * Lista de la relación de empresa que lo desarrollaron
	 */
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "idJuego")
	private List<Desarrolla> listaDesarrolladores;

	/**
	 * Lista de las valoraciones de los usuarios sobre este juego
	 */
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "idJuego")
	private List<Valoracion> listaValoraciones;

	/**
	 * Valoración promedio de positivos del juego
	 */
	@Transient
	private String valoracion;

	/**
	 * Constructor principal de la clase
	 * 
	 * @param nombre      es el nombre del juego
	 * @param estilo      indica el estilo del juego, puede ser null
	 * @param publicacion es la fecha en que se publicacó en formato Date
	 * @param descripcion breve explicación del juego
	 * @param precio      valor de venta del Juego
	 */
	public Juego(String nombre, EstiloJuego estilo, LocalDate publicacion, String descripcion, double precio) {
		this.nombre = nombre;
		this.estilo = estilo;
		this.publicacion = Date.from(publicacion.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		this.descripcion = descripcion;
		this.precio = precio;
		this.listaDesarrolladores = new ArrayList<>();
		this.listaValoraciones = new ArrayList<>();
	}

	/**
	 * Constructor estándar
	 */
	public Juego() {
		super();
	}

	/**
	 * Devuelve el identificador del juego
	 * 
	 * @return idJuego
	 */
	public int getIdJuego() {
		return idJuego;
	}

	/**
	 * Establece el identificador del juego
	 * 
	 * @param idJuego es el identificador del juego en la base de datos
	 */
	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	/**
	 * Devuelve el nombre del juego
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del juego
	 * 
	 * @param nombre es el nombre del juego
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el estilo del juego
	 * 
	 * @return estilo
	 */
	public EstiloJuego getEstilo() {
		return estilo;
	}

	/**
	 * Establece el estilo del juego
	 * 
	 * @param estilo es el estilo de juego, puede ser null
	 */
	public void setEstilo(EstiloJuego estilo) {
		this.estilo = estilo;
	}

	/**
	 * Devuelve la fecha de publicación en formato Date
	 * 
	 * @return publicacion
	 */
	public Date getPublicacion() {
		return publicacion;
	}

	/**
	 * Establece la fecha de publicación convirtiendola de LocalDate a Date. La base
	 * de datos no admite el formato con el que se genera en la aplicación
	 * 
	 * @param publicacion es la fecha en que se publicó el juego
	 */
	public void setPublicacion(LocalDate publicacion) {
		this.publicacion = Date.from(publicacion.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Devuelve la descripcion del juego
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripcion del juego
	 * 
	 * @param descripcion breve explicación del juego
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve el precio en euros del juego
	 * 
	 * @return precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio del juego en euros
	 * 
	 * @param precio es el valor de venta
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve la relación de empresas que desarrollaron el juego
	 * 
	 * @return lista de la relacion de desarrolladores
	 */
	public List<Desarrolla> getListaDesarrolladores() {
		return listaDesarrolladores;
	}

	/**
	 * Establece la relación de empresas que desarrollaron el juego
	 * 
	 * @param listaDesarrolladores es la lista de la relación entre el juego y las
	 *                             empresas que lo desarrollaron
	 */
	public void setListaDesarrolladores(List<Desarrolla> listaDesarrolladores) {
		this.listaDesarrolladores = listaDesarrolladores;
	}

	/**
	 * Devuelve la lista de valoraciones de los usuarios sobre el juego
	 * 
	 * @return listaValoraciones
	 */
	public List<Valoracion> getListaValoraciones() {
		return listaValoraciones;
	}

	/**
	 * Establece la lista de valoraciones hechas por los usuarios sobre el juego
	 * 
	 * @param listaValoraciones es la nueva lista de opiniones y comentarios de los
	 *                          usuarios
	 */
	public void setListaValoraciones(List<Valoracion> listaValoraciones) {
		this.listaValoraciones = listaValoraciones;
	}

	/**
	 * Devuelve la media de valoración positiva sobre el juego
	 * 
	 * @return valoracion
	 */
	public String getValoracion() {
		return valoracion;
	}

	/**
	 * Calcula y establece la media de valoración positiva del juego
	 */
	public void setValoracion() {
		int totalVotos = getListaValoraciones().size();
		int votosPositivos = 0;

		for (Valoracion valoracion : listaValoraciones) {
			if (valoracion.isPositivo()) {
				votosPositivos++;
			}
		}

		if (totalVotos != 0) {
			valoracion = (votosPositivos * 100 / totalVotos) + "% positivos";
		} else {
			valoracion = "sin votos";
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

	/**
	 * Muestra la información principal del juego
	 */
	@Override
	public String toString() {
		return "Juego [idJuego=" + idJuego + ", nombre=" + nombre + ", estilo=" + estilo + ", publicacion="
				+ publicacion + ", descripcion=" + descripcion + ", precio=" + precio + "]";
	}
}
