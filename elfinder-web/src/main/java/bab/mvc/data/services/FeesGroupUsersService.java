package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bab.mvc.Execute;
import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.FeesGroupUsers;

public class FeesGroupUsersService {
	
	public List<FeesGroupUsers> read() {

		List<FeesGroupUsers> st = new ArrayList<FeesGroupUsers>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroupUsers s ").list();

			for (FeesGroupUsers ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<FeesGroupUsers> ReadByfid(int fgid) {

		List<FeesGroupUsers> st = new ArrayList<FeesGroupUsers>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroupUsers s where s.fgid=:fgid")
					.setInteger("fgid", fgid)
					.list();

			for (FeesGroupUsers ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<FeesGroupUsers> ReadByUid(int uid) {

		List<FeesGroupUsers> st = new ArrayList<FeesGroupUsers>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroupUsers s where s.uid=:uid")
					.setInteger("uid", uid)
					.list();

			for (FeesGroupUsers ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<FeesGroupUsers> ReadByFguid(int fguid) {

		List<FeesGroupUsers> st = new ArrayList<FeesGroupUsers>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroupUsers s where s.fguid=:fguid")
					.setInteger("fguid", fguid)
					.list();

			for (FeesGroupUsers ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}

	public void create(FeesGroupUsers fgu) {
		
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.save(fgu);
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}
	public void delete(FeesGroupUsers fgu) {
		
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			session.createQuery(
					"delete from FeesGroupUsers where fgid='" + fgu.getFgid() + "'  AND uid='"+fgu.getUid()+"' ")
					.executeUpdate();
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}
	
	public void update(FeesGroupUsers fgu) {
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupUsers.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.update(fgu);
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		

	}
	
	public List<FeesGroupUsers> getStorageEndTime() {

		List<FeesGroupUsers> st = new ArrayList<FeesGroupUsers>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroupUsers s where s.end < NOW() AND s.storage != :storage")
					.setInteger("storage", Execute.storageSize)
					.list();

			for (FeesGroupUsers ss : st) {
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
