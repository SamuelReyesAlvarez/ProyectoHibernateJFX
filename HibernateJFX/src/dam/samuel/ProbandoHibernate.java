package dam.samuel;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import dam.samuel.dao.EmpresaDAO;
import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.HibernateUtil;

/**
 * 
 * @author Samuel Reyes Alvarez
 * 
 * @version 0.6.3 (16/02/2019)
 * 
 *          Clase de inicio para testear la implementacion de Hibernate sin uso
 *          de JavaFX
 *
 */
public class ProbandoHibernate {

	private static Scanner teclado = new Scanner(System.in);

	private static EmpresaDAO empresaDAO = new EmpresaDAO();
	private static Session session;

	public static void main(String[] args) throws Exception {
		configurarSesion();
		System.out.println("\nInicio de la sesion");

		int opcion;

		do {
			opcion = menu();
			if (opcion != 0) {
				accion(opcion);
			}
		} while (opcion != 0);

		System.out.println("\nFin de la sesion");
		cerrarSesion();
	}

	private static int menu() {
		System.out.println("\n[0] Salir");
		System.out.println("[1] Crear Empresa");
		System.out.println("[2] Ver Empresa");
		System.out.println("[3] Ver todas las empresas");
		System.out.println("[4] Borrar empresa");
		System.out.print("Elige una opcion: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private static void accion(int opcion) throws Exception {
		String nombre = "";
		int id;

		switch (opcion) {
		case 1:
			System.out.print("Nombre de empresa: ");
			nombre = teclado.nextLine();

			Empresa e = new Empresa(nombre);

			empresaDAO.guardar(e);
			break;
		case 2:
			System.out.print("Introduce Id de empresa: ");
			id = Integer.parseInt(teclado.nextLine());

			Empresa emp = empresaDAO.consultarPorId(id);

			System.out.println(emp);
			break;
		case 3:
			List<Empresa> listado = empresaDAO.consultarTodas();

			for (Empresa empresa : listado) {
				System.out.println(empresa);
			}
			break;
		case 4:
			System.out.print("Introduce Id de empresa: ");
			id = Integer.parseInt(teclado.nextLine());

			Empresa em = empresaDAO.consultarPorId(id);
			empresaDAO.borrar(em);

			System.out.println(em);
			break;

		default:
			break;
		}
		System.out.println("\n");
	}

	private static void cerrarSesion() {
		HibernateUtil.closeSessionFactory();
	}

	private static void configurarSesion() {
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
