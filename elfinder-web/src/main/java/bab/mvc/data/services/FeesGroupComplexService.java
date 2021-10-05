package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bab.mvc.data.entities.complex.FeesGroupComplex;
import bab.mvc.data.entities.pure.User;

public class FeesGroupComplexService {
	public List<FeesGroupComplex> Read() {

		List<FeesGroupComplex> st = new ArrayList<FeesGroupComplex>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupComplex.class)
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					//"select s from FeesGroupComplex s inner join s.owner s1 inner join s1.fee s2 inner join s2.queue").list();
					"select s from FeesGroupComplex s").list();
			for (FeesGroupComplex ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<FeesGroupComplex> Read(String fgname) {

		List<FeesGroupComplex> st = new ArrayList<FeesGroupComplex>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroupComplex.class)
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					//"select s from FeesGroupComplex s inner join s.owner s1 inner join s1.fee s2 inner join s2.queue").list();
					"select s from FeesGroupComplex s where s.fgname LIKE :fgname")
					.setString("fgname", "%"+fgname+"%")
					.list();
			for (FeesGroupComplex ss : st) {
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
