package bab.mvc.data.services;

import java.util.ArrayList;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import bab.mvc.data.entities.pure.Applications;

@Component
public class ApplicationsService {
	public List<Applications> ReadActives() {

		List<Applications> st = new ArrayList<Applications>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Applications.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Applications s where active='1' ORDER BY s.appname ASC").list();

			for (Applications ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<Applications> ReadAll() {

		List<Applications> st = new ArrayList<Applications>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Applications.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Applications s ORDER BY s.appname ASC").list();

			for (Applications ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<Applications> ReadByAppidActives(int appid) {

		List<Applications> st = new ArrayList<Applications>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Applications.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Applications s where s.appid=:appid and active='1'")
					.setInteger("appid", appid)
					.list();

			for (Applications ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<Applications> ReadByAppidAll(int appid) {

		List<Applications> st = new ArrayList<Applications>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Applications.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Applications s where s.appid=:appid")
					.setInteger("appid", appid)
					.list();

			for (Applications ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<Applications> ReadByAppNameActives(String appname) {

		List<Applications> st = new ArrayList<Applications>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Applications.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Applications s where s.appname=:appname and active='1'")
					.setString("appname", appname)
					.list();

			for (Applications ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public void update(Applications a) {
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Applications.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.update(a);
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		

	}
	
	public void create(Applications a) {
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Applications.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.save(a);
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		

	}
}
