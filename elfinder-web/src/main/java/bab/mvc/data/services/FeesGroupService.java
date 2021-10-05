package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.FeesGroupUsers;
import bab.mvc.data.entities.pure.User;

@Component
public class FeesGroupService {
	
	public List<FeesGroup> read() {

		List<FeesGroup> st = new ArrayList<FeesGroup>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroup.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroup s ").list();

			for (FeesGroup ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	
	public List<FeesGroup> ReadByfgid(int fgid) {

		List<FeesGroup> st = new ArrayList<FeesGroup>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroup.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroup s where s.fgid=:fgid")
					.setInteger("fgid", fgid)
					.list();

			for (FeesGroup ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<FeesGroup> ReadByOwnerUid(int ownerUid) {

		List<FeesGroup> st = new ArrayList<FeesGroup>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroup.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroup s where s.uid=:ownerUid")
					.setInteger("ownerUid", ownerUid)
					.list();

			for (FeesGroup ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<FeesGroup> ReadByMemberUid(int memberUid) {

		List<FeesGroup> st = new ArrayList<FeesGroup>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroup.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(FeesGroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"select fg from FeesGroup fg, FeesGroupUsers fgu where fg.fgid=fgu.fgid AND fgu.uid=:memberUid")
					.setInteger("memberUid", memberUid)
					.list();

			for (FeesGroup ss : st) {
				System.out.println(ss.toString());
			}

			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
		
	public List<FeesGroup> ReadByFeesName(String feesgroupname) {

		List<FeesGroup> st = new ArrayList<FeesGroup>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroup.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from FeesGroup s where s.fgname=:feesgroupname")
					.setString("feesgroupname", feesgroupname)
					.list();

			for (FeesGroup ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public int create(FeesGroup fg) {
		int fgid =-1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroup.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.save(fg);
			fgid=fg.getFgid();
			// commit
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
			return fgid;
		}
	}
	
	public void update(FeesGroup fg) {
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(FeesGroup.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.update(fg);
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		

	}
	
}
