package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bab.mvc.data.entities.pure.GroupUsers;;

public class GroupUsersService {
	public List<GroupUsers> ReadByUid(int uid) {

		List<GroupUsers> st = new ArrayList<GroupUsers>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(GroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from GroupUsers s where s.uid=:uid")
					.setInteger("uid", uid)
					.list();

			for (GroupUsers ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<GroupUsers> ReadByGid(int gid) {

		List<GroupUsers> st = new ArrayList<GroupUsers>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(GroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from GroupUsers s where s.gid=:gid")
					.setInteger("gid", gid)
					.list();

			for (GroupUsers ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public void create(GroupUsers g) {
		
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(GroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.save(g);
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}
	
}
