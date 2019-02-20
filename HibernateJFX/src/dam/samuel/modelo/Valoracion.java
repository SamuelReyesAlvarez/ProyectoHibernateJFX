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
import javax.validation.Valid;
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

	@Id
	@Column(name = "idValoracion")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idValoracion;

	@Column(name = "voto")
	@Type(type = "boolean")
	@NotNull
	private boolean positivo;

	@Column(name = "comentario")
	@Size(min = 5, max = 250)
	private String comentario;

	@ManyToOne
	@JoinColumn(name = "idJuego")
	@NotNull
	@Valid
	private Juego juego;

	public Valoracion(boolean voto, String comentario) {
		this.positivo = voto;
		this.comentario = comentario;
	}

	public Valoracion() {
		super();
	}

	public int getIdValoracion() {
		return idValoracion;
	}

	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
	}

	public boolean isPositivo() {
		return positivo;
	}

	public void setPositivo(boolean voto) {
		this.positivo = voto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
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

	@Override
	public String toString() {
		return "Valoracion [idValoracion=" + idValoracion + ", positivo=" + positivo + ", comentario=" + comentario
				+ "]";
	}
}
