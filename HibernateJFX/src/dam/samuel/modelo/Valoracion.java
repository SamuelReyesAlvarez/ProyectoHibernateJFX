package dam.samuel.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "valoracion")
public class Valoracion implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Dato autogenerado que identifica de forma única al objeto entre los de su
	 * clase
	 */
	@Id
	@Column(name = "idValoracion")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idValoracion;

	/**
	 * Recomendación del usuario que realizó la valoración
	 */
	@Column(name = "voto")
	@Type(type = "boolean")
	@NotNull
	private boolean positivo;

	/**
	 * Breve opinión del usuario sobre el juego
	 */
	@Column(name = "comentario")
	@NotNull
	@Size(min = 5, max = 250)
	private String comentario;

	/**
	 * Identificador del juego al que hace referencia
	 */
	@ManyToOne
	@JoinColumn(name = "idJuego")
	@NotNull
	private Juego juego;

	/**
	 * Texto de la recomendación del usuario
	 */
	@Transient
	private String tipoVoto;

	/**
	 * Constructor principal de la clase
	 * 
	 * @param voto       recomendación del usuario
	 * @param comentario opinión del usuario
	 */
	public Valoracion(boolean voto, String comentario) {
		this.positivo = voto;
		this.comentario = comentario;
	}

	/**
	 * Constructor estándar
	 */
	public Valoracion() {
		super();
	}

	/**
	 * Devuelve el identificador de la valoración
	 * 
	 * @return idValoracion
	 */
	public int getIdValoracion() {
		return idValoracion;
	}

	/**
	 * Establece el identificador de la valoración
	 * 
	 * @param idValoracion es el identificador del objeto
	 */
	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
	}

	/**
	 * Devuelve la recomendación del usuario
	 * 
	 * @return positivo
	 */
	public boolean isPositivo() {
		return positivo;
	}

	/**
	 * Establece la recomendación hecha por el usuario
	 * 
	 * @param voto recomendación del usuario
	 */
	public void setPositivo(boolean voto) {
		this.positivo = voto;
	}

	/**
	 * Devuelve la opinión del usuario
	 * 
	 * @return comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Establece la opinión hecha por el usuario
	 * 
	 * @param comentario es la opinión del usuario
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Devuelve el juego al que hace referencia la valoración
	 * 
	 * @return juego
	 */
	public Juego getJuego() {
		return juego;
	}

	/**
	 * Establece el juego al que hace referencia la valoración
	 * 
	 * @param juego que el usuario ha valorado
	 */
	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	/**
	 * Genera un texto para las tablas con la recomendación del usuario
	 * 
	 * @return tipoVoto
	 */
	public String getTipoVoto() {
		if (positivo) {
			tipoVoto = "positivo";
		} else {
			tipoVoto = "negativo";
		}

		return tipoVoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idValoracion;
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
		Valoracion other = (Valoracion) obj;
		if (idValoracion != other.idValoracion)
			return false;
		return true;
	}

	/**
	 * Muestra la información principal de la valoración
	 */
	@Override
	public String toString() {
		return "Valoracion [idValoracion=" + idValoracion + ", positivo=" + positivo + ", comentario=" + comentario
				+ "]";
	}
}
