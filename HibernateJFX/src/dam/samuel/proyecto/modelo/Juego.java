package dam.samuel.proyecto.modelo;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class Juego {

	public enum EstiloJuego {
		rol, accion, aventura, estrategia
	}

	private final IntegerProperty idJuego;
	private final StringProperty nombre;
	private final StringProperty estilo;
	private final ObjectProperty<Date> publicacion;
	private final StringProperty descripcion;
	private final DoubleProperty precio;
	private final StringProperty desarrolladores;
	private final IntegerProperty valoracion;
	private ArrayList<Empresa> listaDesarrolladores;
	private ArrayList<Valoracion> listaValoraciones;

	public Juego(int idJuego, String nombre, EstiloJuego estilo, Date publicacion, String descripcion, double precio,
			ArrayList<Empresa> desarrolladores, ArrayList<Valoracion> valoraciones) {
		this.idJuego = new SimpleIntegerProperty(idJuego);
		this.nombre = new SimpleStringProperty(nombre);
		this.estilo = new SimpleStringProperty(estilo.toString());
		this.publicacion = new SimpleObjectProperty<Date>(publicacion);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.precio = new SimpleDoubleProperty(precio);
		this.desarrolladores = setDesarroladores(desarrolladores);
		this.valoracion = setValoracion(valoraciones);
		this.listaDesarrolladores = desarrolladores;
		this.listaValoraciones = valoraciones;
	}

	public Juego() {
		this(0, null, null, null, null, 0, null, null);
	}

	public int getIdJuego() {
		return idJuego.get();
	}

	public String getNombre() {
		return nombre.get();
	}

	public String getEstilo() {
		return estilo.get();
	}

	public Date getPublicacion() {
		return publicacion.get();
	}

	public String getDescripcion() {
		return descripcion.get();
	}

	public double getPrecio() {
		return precio.get();
	}

	public String getDesarrolladores() {
		return desarrolladores.get();
	}

	public int getValoracion() {
		return valoracion.get();
	}

	public ArrayList<Empresa> getListaDesarrolladores() {
		return listaDesarrolladores;
	}

	public ArrayList<Valoracion> getListaValoraciones() {
		return listaValoraciones;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego.set(idJuego);
	}

	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}

	public void setEstilo(EstiloJuego estilo) {
		this.estilo.set(estilo.toString());
	}

	public void setPublicacion(Date publicacion) {
		this.publicacion.set(publicacion);
	}

	public void setDescripcion(String descripcion) {
		this.descripcion.set(descripcion);
	}

	public void setPrecio(double precio) {
		this.precio.set(precio);
	}

	public StringProperty setDesarroladores(ArrayList<Empresa> desarrolladores) {
		StringBuilder lista = new StringBuilder();

		for (Empresa empresa : desarrolladores) {
			if (lista.length() > 0) {
				lista.append(", ");
			}
			lista.append(empresa.getNombre());
		}

		this.desarrolladores.set(lista.toString());

		return this.desarrolladores;
	}

	public IntegerProperty setValoracion(ArrayList<Valoracion> valoraciones) {
		int positivas = 0;

		for (Valoracion valoracion : valoraciones) {
			if (valoracion.isPositivo()) {
				positivas++;
			}
		}

		this.valoracion.set(positivas * 100 / valoraciones.size());

		return this.valoracion;
	}

	public void setListaDesarrolladores(ArrayList<Empresa> desarrolladores) {
		this.listaDesarrolladores = desarrolladores;
	}

	public void setListaValoraciones(ArrayList<Valoracion> valoraciones) {
		this.listaValoraciones = valoraciones;
	}

	public IntegerProperty propiedadIdJuego() {
		return idJuego;
	}

	public StringProperty propiedadNombre() {
		return nombre;
	}

	public StringProperty propiedadEstilo() {
		return estilo;
	}

	public ObjectProperty<Date> propiedadPublicacion() {
		return publicacion;
	}

	public StringProperty propiedadDescripcion() {
		return descripcion;
	}

	public DoubleProperty propiedadPrecio() {
		return precio;
	}

	public StringProperty propiedadDesarrolladores() {
		return desarrolladores;
	}

	public IntegerProperty propiedadValoracion() {
		return valoracion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idJuego.get();
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
		if (idJuego.get() != other.idJuego.get())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Juego [idJuego=" + getIdJuego() + ", nombre=" + getNombre() + ", estilo=" + getEstilo()
				+ ", publicacion=" + getPublicacion() + ", descripcion=" + getDescripcion() + ", precio=" + getPrecio()
				+ "]";
	}
}
