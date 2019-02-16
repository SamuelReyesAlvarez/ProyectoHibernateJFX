package dam.samuel.proyecto.modelo;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class Valoracion {

	private int idValoracion;
	private boolean positivo;
	private String comentario;

	public Valoracion(int idValoracion, boolean voto, String comentario) {
		super();
		this.idValoracion = idValoracion;
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
