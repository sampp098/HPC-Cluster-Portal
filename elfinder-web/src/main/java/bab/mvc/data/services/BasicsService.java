package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bab.mvc.data.entities.pure.Basics;

public class BasicsService {
	public List<Basics> Read() {

		List<Basics> st = new ArrayList<Basics>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Basics s ").list();

			for (Basics ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}                    

	
	public String getEmailId() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='email.id'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}

	}
	
	public String getEmailPass() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='email.pass'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}

	}
	public String getClusterHost() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.host'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}

	}
	public String getClusterUser() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.user'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}

	}                                 
	public String getClusterPass() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.pass'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}

	}               
	public int getClusterPort() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.port'").list();
			
			session.getTransaction().commit();
			return Integer.parseInt(st.get(0).getValue());
		} finally {
			factory.close();
		}

	}                
	public String getClusterHome() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.home'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}

	}                
	public int getClusterDefaultStorageSize() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.default.storageSize'").list();
			
			session.getTransaction().commit();
			return Integer.parseInt(st.get(0).getValue());
		} finally {
			factory.close();
		}

	}         
	public int getClusterStorageCost() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.storage.cost'").list();
			
			session.getTransaction().commit();
			return Integer.parseInt(st.get(0).getValue());
		} finally {
			factory.close();
		}

	}  
	public int getClusterCores() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.cores'").list();
			
			session.getTransaction().commit();
			return Integer.parseInt(st.get(0).getValue());
		} finally {
			factory.close();
		}

	}
	public int getClusterNodes() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='cluster.nodes'").list();
			
			session.getTransaction().commit();
			return Integer.parseInt(st.get(0).getValue());
		} finally {
			factory.close();
		}

	}
	public int getPortalSessionTimout() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='portal.session.timout'").list();
			
			session.getTransaction().commit();
			return Integer.parseInt(st.get(0).getValue());
		} finally {
			factory.close();
		}

	}                
	               
	public String getPortalDomain() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='portal.domain'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}

	}
	public int getPortalMonitorPeriod() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='portal.monitor.period'").list();
			
			session.getTransaction().commit();
			return Integer.parseInt(st.get(0).getValue());
		} finally {
			factory.close();
		}

	}
	public int getPortalActivationExpire() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='portal.activation.expire'").list();
			
			session.getTransaction().commit();
			return Integer.parseInt(st.get(0).getValue());
		} finally {
			factory.close();
		}

	}
			
	public String getPortalFileRepository() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='portal.file.repository'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}
	}
	public String getGangliaHome() {

		List<Basics> st = new ArrayList<Basics>();
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Basics.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			st = session.createQuery(
					"from Basics s where s.name='ganglia.home'").list();
			
			session.getTransaction().commit();
			return st.get(0).getValue();
		} finally {
			factory.close();
		}
	}
}
