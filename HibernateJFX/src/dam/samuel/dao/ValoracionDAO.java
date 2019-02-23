package dam.samuel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Valoracion;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 *         Clase con consultas HQL propias de los objetos Valoracion que
 *         extiende de GenericDAO para poder re4alizar consultas genéricas
 */
public class ValoracionDAO extends GenericDAO<Valoracion> {

	/**
	 * Permite obtener un listado de todas las valoraciones de los usuarios hechas
	 * sobre un juego determinado
	 * 
	 * @param juego sobre el que se desea obtener las valoraciones
	 * @return lista de valoraciones pertenecientes al juego especificado por
	 *         parámetro
	 */
	@SuppressWarnings("unchecked")
	public List<Valoracion> consultarPorJuego(Juego juego) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session
				.createQuery("SELECT v FROM Valoracion v INNER JOIN v.juego j WHERE j.idJuego = " + juego.getIdJuego());
		return consulta.list();
	}
}
