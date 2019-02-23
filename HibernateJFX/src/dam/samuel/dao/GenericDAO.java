package dam.samuel.dao;

import javax.validation.ConstraintViolationException;

import org.hibernate.Session;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.ValoratorException;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 *         Clase generica que ofrece consultas comunes de HQL para todos las
 *         clases persistidas en MySQL
 */
public class GenericDAO<T> {

	/**
	 * Guarda el objeto pasado por parámetro a través de una transacción. Si no
	 * puede ser guardado, la base de datos volverá al estado anterior al comienzo
	 * del proceso, deshaciendo los cambios por cascada
	 * 
	 * @param entidad que se desea persistir
	 * @throws ValoratorException                         controla las validaciones
	 *                                                    de hibernate sobre los
	 *                                                    objetos
	 * @throws MySQLIntegrityConstraintViolationException controla las validaciones
	 *                                                    de los datos en MySQL
	 */
	public void guardar(T entidad) throws ValoratorException, MySQLIntegrityConstraintViolationException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.save(entidad);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new ValoratorException("Error al realizar el guardado");
		}
	}

	/**
	 * Actualiza la información de un objeto que fue persistido pero del que se ha
	 * modificado sus datos
	 * 
	 * @param entidad que fue modificada
	 * @throws ValoratorException controla las validaciones de hibernate sobre los
	 *                            objetos
	 */
	public void actualizar(T entidad) throws ValoratorException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.update(entidad);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new ValoratorException("Error al realizar la actualizaciÃ³n");
		}
	}

	/**
	 * Elimina el objeto de la base de datos y los hijos con los que esté
	 * relacionado y se halla especificado DeleteOnCascade
	 * 
	 * @param entidad que se desea borrar
	 * @throws ValoratorException controla las validaciones de hibernate sobre los
	 *                            objetos
	 */
	public void borrar(T entidad) throws ValoratorException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.delete(entidad);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new ValoratorException("Error al realizar el borrado");
		}
	}
}
