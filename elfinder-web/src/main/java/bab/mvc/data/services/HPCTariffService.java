package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import bab.mvc.data.entities.pure.HPCTariff;

@Component
public class HPCTariffService {
	
	public List<HPCTariff> readAll() {

		List<HPCTariff> st = new ArrayList<HPCTariff>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from HPCTariff s ").list();

			for (HPCTariff ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<HPCTariff> read() {

		List<HPCTariff> st = new ArrayList<HPCTariff>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from HPCTariff s where s.active=1").list();

			for (HPCTariff ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<HPCTariff> read(String name) {

		List<HPCTariff> st = new ArrayList<HPCTariff>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from HPCTariff s where s.name LIKE :name ")
					.setString("name", "%"+name+"%")
					.list();

			for (HPCTariff ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<HPCTariff> getUserTarrifList(String userGroups) {

		List<HPCTariff> st = new ArrayList<HPCTariff>();
		
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from HPCTariff s where s.active=1 and s.name in("+userGroups+")")
					.list();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+userGroups);
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<HPCTariff> ReadByHPCTid(int hpctid) {

		List<HPCTariff> st = new ArrayList<HPCTariff>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from HPCTariff s where s.hpctid=:hpctid")
					.setInteger("hpctid", hpctid)
					.list();

			for (HPCTariff ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<HPCTariff> ReadByHPCTariffName(String tariffname) {

		List<HPCTariff> st = new ArrayList<HPCTariff>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from HPCTariff s where s.name=:tariffname")
					.setString("tariffname", tariffname)
					.list();

			for (HPCTariff ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public int create(HPCTariff t) {
		int hpctid =-1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(HPCTariff.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.save(t);
			hpctid=t.getHpctid();
			System.out.println("-------------------------------------------> fees add:"+t.getHpctid());
			// commit
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
			return hpctid;
		}
	}
	
	public int update(HPCTariff t) {
		int hpctid =-1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(HPCTariff.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.update(t);
			hpctid=t.getHpctid();
			System.out.println("-------------------------------------------> fees add:"+t.getHpctid());
			// commit
			session.getTransaction().commit();
			
		} finally {
			factory.close();
			return hpctid;
		}
	}
}
