package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bab.mvc.data.entities.pure.GroupPermissions;

public class GroupPermissionsService {
	public List<GroupPermissions> ReadByGid(int gid) {

		List<GroupPermissions> st = new ArrayList<GroupPermissions>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(GroupPermissions.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from GroupPermissions s where s.gid=:gid")
					.setInteger("gid", gid)
					.list();

			for (GroupPermissions ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<GroupPermissions> ReadByPid(int pid) {

		List<GroupPermissions> st = new ArrayList<GroupPermissions>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(GroupPermissions.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from GroupPermissions s where s.pid=:pid")
					.setInteger("pid", pid)
					.list();

			for (GroupPermissions ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}

}
