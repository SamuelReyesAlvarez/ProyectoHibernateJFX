package dam.samuel.dao;

import org.hibernate.Session;

import dam.samuel.modelo.HibernateUtil;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class GenericDAO<T> {
	public void guardar(T entidad) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(entidad);
		session.getTransaction().commit();
	}

	public void borrar(T entidad) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(entidad);
		session.getTransaction().commit();
	}
}
