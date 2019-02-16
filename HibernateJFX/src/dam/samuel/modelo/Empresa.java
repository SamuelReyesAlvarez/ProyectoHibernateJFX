package dam.samuel.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idEmpresa")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEmpresa;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "idEmpresa")
	private List<Desarrolla> juegos;

	public Empresa(String nombre) {
		this.nombre = nombre;
		this.juegos = new ArrayList<>();
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

	public List<Desarrolla> getJuegos() {
		return juegos;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setJuegos(List<Desarrolla> juegos) {
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
