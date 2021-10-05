package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bab.mvc.data.entities.pure.Permissions;

public class PermissionsService {
	public List<Permissions> ReadByPid(int pid) {

		List<Permissions> st = new ArrayList<Permissions>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Permissions.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Permissions s where s.pid=:pid")
					.setInteger("pid", pid)
					.list();

			for (Permissions ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public static boolean exist(List<String> permissionList,String permissionName) {
		for (String p : permissionList) {
			if(p.equals(permissionName)) {
				return true;
			}
		}
		return false;
	}
}
