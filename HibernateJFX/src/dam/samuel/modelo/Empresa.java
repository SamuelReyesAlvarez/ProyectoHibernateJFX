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
@Table(name = "empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Dato autogenerado que identifica de forma única a este objeto entre los de su
	 * clase
	 */
	@Id
	@Column(name = "idEmpresa")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEmpresa;

	/**
	 * Nombre de la empresa
	 */
	@Column(name = "nombre")
	@NotBlank
	@Size(min = 3, max = 25)
	private String nombre;

	/**
	 * Lista de la relacion con los juegos que ha desarrollado. Se almacena en la
	 * base de datos dentro de la tabla Desarrolla
	 */
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "idEmpresa")
	private List<Desarrolla> juegos;

	/**
	 * Constructor principal de la clase
	 * 
	 * @param nombre en el nombre de la empresa
	 */
	public Empresa(String nombre) {
		this.nombre = nombre;
		this.juegos = new ArrayList<>();
	}

	/**
	 * Constructor estándar
	 */
	public Empresa() {
		super();
	}

	/**
	 * Devuelve el identificador de la empresa
	 * 
	 * @return idEmpresa
	 */
	public int getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * Devuelve el nombre de la empresa
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve la relación con los juegos desarrollados por la empresa
	 * 
	 * @return lista de juegos
	 */
	public List<Desarrolla> getJuegos() {
		return juegos;
	}

	/**
	 * Establece el identificador de la empresa
	 * 
	 * @param idEmpresa es el identificador de la empresa en la base de datos
	 */
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * Establece el nombre de la empresa
	 * 
	 * @param nombre es el nombre de la empresa
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Establece la relacion con los juegos desarrollados
	 * 
	 * @param juegos es la relación de juegos desarrollados por la empresa
	 */
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

	/**
	 * Muestra los datos principales de la empresa
	 */
	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", nombre=" + nombre + "]";
	}
}
