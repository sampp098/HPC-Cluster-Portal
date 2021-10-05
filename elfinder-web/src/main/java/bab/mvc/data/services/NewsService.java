package bab.mvc.data.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bab.mvc.data.entities.pure.News;
import bab.mvc.data.entities.pure.User;

public class NewsService {
	public List<News> read() {

		List<News> st = new ArrayList<News>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(News.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from News s ").list();

			for (News ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<News> readAvailable(Date date) {
		
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		List<News> st = new ArrayList<News>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(News.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			//"from News s where expdate >= '"+format.format(d)+"'")
			st = session.createQuery(
					"from News s where s.expdate >= :date")
					.setDate("date", date)
					.list();

			for (News ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<News> ReadByNid(int nid) {

		List<News> st = new ArrayList<News>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(News.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from News s where s.nid=:nid")
					.setInteger("nid", nid)
					.list();

			for (News ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public int create(News n) {

		//List<News> st = new ArrayList<News>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(News.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.save(n);
			
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return n.getNid();
	}
	public int update(News n) {

		//List<News> st = new ArrayList<News>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(News.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			session.update(n);
			
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return n.getNid();
	}
	public void delete(int nid) {

		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(News.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.createQuery(
					"delete from News n where n.nid=:nid")
					.setInteger("nid", nid)
					.executeUpdate();


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}
	
}
