package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bab.mvc.data.entities.pure.Groups;

public class GroupsService {
	public List<Groups> ReadByGid(int gid) {

		List<Groups> st = new ArrayList<Groups>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Groups.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Groups s where s.gid=:gid")
					.setInteger("gid", gid)
					.list();

			for (Groups ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<Groups> ReadByName(String gname) {

		List<Groups> st = new ArrayList<Groups>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Groups.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Group s where s.gname=:gname")
					.setString("gname", gname)
					.list();

			for (Groups ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public static boolean exist(List<String> grouplist,String groupname) {
		for (String p : grouplist) {
			if(p.equals(groupname)) {
				return true;
			}
		}
		return false;
	}
}
