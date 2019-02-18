package dam.samuel.dao;

import javax.validation.ConstraintViolationException;

import org.hibernate.Session;

import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.ValoratorException;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class GenericDAO<T> {
	public void guardar(T entidad) throws ValoratorException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.saveOrUpdate(entidad);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new ValoratorException("Error al realizar el guardado");
		}
	}

	public void borrar(T entidad) throws Exception {
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
