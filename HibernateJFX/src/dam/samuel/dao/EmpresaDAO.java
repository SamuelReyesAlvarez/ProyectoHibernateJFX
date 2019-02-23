package dam.samuel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.HibernateUtil;

/**
 * 
 * @author Samuel Reyes Alvarez
 * 
 *         Esta clase permite realizar consultas HQL propias de los objetos
 *         Empresa y hereda de GenericDAO el cual le ofrece consultas comunes a
 *         todos los objetos persistidos
 *
 */
public class EmpresaDAO extends GenericDAO<Empresa> {

	/**
	 * Permite obtener el objeto completo almacenado en la base de datos o nulo si
	 * no fue persistido anteriormente
	 * 
	 * @param id de la empresa que se desea obtener la información
	 * @return unico objeto que coincida con el indicado por parámetro
	 */
	public Empresa consultarPorId(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Empresa) session.get(Empresa.class, id);
	}

	/**
	 * Permite obtener una lista completa de todas las empresas registradas en el
	 * programa
	 * 
	 * @return lista de empresas registradas ordenadas por nombre de forma
	 *         ascendente
	 */
	@SuppressWarnings("unchecked")
	public List<Empresa> consultarTodas() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query consulta = session.createQuery("SELECT e FROM dam.samuel.modelo.Empresa e ORDER BY nombre");
		return consulta.list();
	}

	/**
	 * Permite obtener los datos de una empresa por su nombre si fue persistida
	 * anteriormente
	 * 
	 * @param nombre de la empresa que se desea los datos
	 * @return unica empresa que coincida en nombre con la solicitada por parámetro
	 */
	public Empresa consultarPorNombre(String nombre) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return (Empresa) session
				.createQuery("SELECT e FROM dam.samuel.modelo.Empresa e WHERE nombre = '" + nombre + "'")
				.uniqueResult();
	}
}
