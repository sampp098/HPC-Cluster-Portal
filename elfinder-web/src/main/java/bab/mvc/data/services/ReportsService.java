package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.HPCTariff;
import bab.mvc.data.entities.pure.Jobs;
import bab.mvc.data.entities.pure.User;

@Component
public class ReportsService {
	public List<Object[]> getUserJobsWithCost(int uid) {

		List<Object[]> st = new ArrayList<Object[]>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"SELECT j, ( j.cputime*(t.corecost+(t.memcost*(j.memnum))) ) FROM Jobs j, HPCTariff t where j.hpctid=t.hpctid AND j.uid=:uid")
					.setInteger("uid",uid)
					.list();
			System.out.println("size= "+st.size());
			for (Object[] ss : st) {
				System.out.println(ss[0].toString()+" cost:"+ss[1].toString());
				
			}


			// commit
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<Object[]> getUserJobsPie(int uid) {

		List<Object[]> st = new ArrayList<Object[]>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"SELECT j.appname,AVG(j.memnum), AVG(j.corenum), SUM(j.cputime), SUM( j.cputime*(t.corecost+(t.memcost*(j.memnum))) ) FROM Jobs j, HPCTariff t where j.hpctid=t.hpctid AND j.uid=:uid GROUP BY j.appname")
					.setInteger("uid",uid)
					.list();
			System.out.println("size= "+st.size());
			for (Object[] ss : st) {
				System.out.println("app: "+ss[0].toString()+" memnumAVG: "+ss[1].toString()+" corenumAVG: "+ss[2].toString()+" CPUtimeSUM: "+ss[3].toString()+" CostSUM: "+ss[4].toString());
				
			}


			// commit
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

		return st;
	}
	public List<Object[]> getUserJobsCostPerDay(int uid) {

		List<Object[]> st = new ArrayList<Object[]>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createSQLQuery("SELECT date(createdate), jobs_num, cost\n" + 
					"FROM (select createdate, count(DISTINCT jid) as jobs_num, sum(( cputime*(t.corecost+t.memcost*(memnum)) ))cost\n" + 
					"FROM jobs j, HPCTariff t where j.hpctid= t.hpctid AND j.uid=:uid Group BY CAST(createdate AS DATE)) orders_per_day;")
					.setInteger("uid", uid)
					.list();
			System.out.println("size= "+st.size());
			for (Object[] ss : st) {
				System.out.println("date: "+ss[0].toString()+" num: "+ss[1].toString()+" cost: "+ss[2].toString());
				
			}


			// commit
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

		return st;
	}
/*	String str="SELECT r.createdate, r.jobs_num, r.cost\n" + 
	"FROM (Select j.createdate, count(DISTINCT j.jid) as jobs_num, sum(( j.cputime*(10+5*(j.memnum)) ))as cost\n" + 
	"FROM Jobs j, HPCTariff t WHERE j.hpctid=t.hpctid Group BY CAST(createdate AS DATE)) r orders_per_day;";
	
	*/
	
	
	//*************************Owner******************
	
	public List<Object[]> getOwnerJobsWithCost(int fgid) {

		List<Object[]> st = new ArrayList<Object[]>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.addAnnotatedClass(HPCTariff.class)
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"SELECT j, SUM( j.cputime*(t.corecost+(t.memcost*(j.memnum))) ), SUM( j.cputime), AVG(j.corenum), AVG(j.memnum),  u FROM Jobs j, HPCTariff t, User u where j.hpctid=t.hpctid AND j.fgid=:fgid AND j.uid=u.uid GROUP BY j.uid")
					.setInteger("fgid",fgid)
					.list();
			System.out.println("size= "+st.size());
			for (Object[] ss : st) {
				System.out.println(ss[0].toString()+" cost:"+ss[1].toString()+" CPUTime:"+ss[2].toString()+" AVGCores:"+ss[3].toString()+" AVGMemory:"+ss[4].toString()+" user:"+ss[5].toString());
				
			}


			// commit
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

		return st;
	}
	public List<Object[]> getOwnerJobsPie(int fgid) {

		List<Object[]> st = new ArrayList<Object[]>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"SELECT j, SUM( j.cputime*(t.corecost+(t.memcost*(j.memnum))) ), SUM( j.cputime), AVG(j.corenum), AVG(j.memnum) FROM Jobs j, HPCTariff t where j.hpctid=t.hpctid AND j.fgid=:fgid GROUP BY j.appname")
					.setInteger("fgid",fgid)
					.list();
			System.out.println("size= "+st.size());
			for (Object[] ss : st) {
				System.out.println(ss[0].toString()+" cost:"+ss[1].toString()+" CPUTime:"+ss[2].toString()+" AVGCores:"+ss[3].toString()+" AVGMemory:"+ss[4].toString());
			}


			// commit
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<Object[]> getOwnerJobsCostPerDay(int fgid) {

		List<Object[]> st = new ArrayList<Object[]>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createSQLQuery("SELECT date(createdate), jobs_num, cost\n" + 
					"FROM (select createdate, count(DISTINCT jid) as jobs_num, sum(( cputime*(t.corecost+t.memcost*(memnum)) ))cost\n" + 
					"FROM jobs j, HPCTariff t where j.hpctid= t.hpctid AND j.fgid=:fgid Group BY CAST(createdate AS DATE)) orders_per_day;")
					.setInteger("fgid", fgid)
					.list();
			System.out.println("size= "+st.size());
			for (Object[] ss : st) {
				System.out.println("date: "+ss[0].toString()+" num: "+ss[1].toString()+" cost: "+ss[2].toString());
				
			}


			// commit
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

		return st;
	}
	
	//*************************Admin******************
	
		public List<Object[]> getAdminJobsWithCost() {

			List<Object[]> st = new ArrayList<Object[]>();
			// session factory creation
			SessionFactory factory = new Configuration().configure("hibernate.xml")
					.addAnnotatedClass(Jobs.class)
					.addAnnotatedClass(HPCTariff.class)
					.addAnnotatedClass(FeesGroup.class)
					.buildSessionFactory();
			// session creation
			Session session = factory.getCurrentSession();

			try {

				session.beginTransaction();
				st = session.createQuery(
						"SELECT j, SUM( j.cputime*(t.corecost+(t.memcost*(j.memnum))) ), SUM( j.cputime), AVG(j.corenum), AVG(j.memnum),  fg FROM Jobs j, HPCTariff t, FeesGroup fg where j.hpctid=t.hpctid AND j.fgid=fg.fgid GROUP BY j.fgid")
						//.setInteger("fgid",fgid)
						.list();
				System.out.println("size= "+st.size());
				for (Object[] ss : st) {
					System.out.println(ss[0].toString()+" cost:"+ss[1].toString()+" CPUTime:"+ss[2].toString()+" AVGCores:"+ss[3].toString()+" AVGMemory:"+ss[4].toString()+" user:"+ss[5].toString());
					
				}


				// commit
				session.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				factory.close();
			}

			return st;
		}
		public List<Object[]> getAdminJobsPie() {

			List<Object[]> st = new ArrayList<Object[]>();
			// session factory creation
			SessionFactory factory = new Configuration().configure("hibernate.xml")
					.addAnnotatedClass(Jobs.class)
					.addAnnotatedClass(HPCTariff.class)
					.buildSessionFactory();
			// session creation
			Session session = factory.getCurrentSession();

			try {

				session.beginTransaction();
				st = session.createQuery(
						"SELECT j, SUM( j.cputime*(t.corecost+(t.memcost*(j.memnum))) ), SUM( j.cputime), AVG(j.corenum), AVG(j.memnum) FROM Jobs j, HPCTariff t where j.hpctid=t.hpctid GROUP BY j.appname")
						//.setInteger("fgid",fgid)
						.list();
				System.out.println("size= "+st.size());
				for (Object[] ss : st) {
					System.out.println(ss[0].toString()+" cost:"+ss[1].toString()+" CPUTime:"+ss[2].toString()+" AVGCores:"+ss[3].toString()+" AVGMemory:"+ss[4].toString());
				}


				// commit
				session.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				factory.close();
			}

			return st;
		}
		
		public List<Object[]> getAdminJobsCostPerDay() {

			List<Object[]> st = new ArrayList<Object[]>();
			// session factory creation
			SessionFactory factory = new Configuration().configure("hibernate.xml")
					.addAnnotatedClass(Jobs.class)
					.addAnnotatedClass(HPCTariff.class)
					.buildSessionFactory();
			// session creation
			Session session = factory.getCurrentSession();

			try {

				session.beginTransaction();
				st = session.createSQLQuery("SELECT date(createdate), jobs_num, cost\n" + 
						"FROM (select createdate, count(DISTINCT jid) as jobs_num, sum(( cputime*(t.corecost+t.memcost*(memnum)) ))cost\n" + 
						"FROM jobs j, HPCTariff t where j.hpctid= t.hpctid Group BY CAST(createdate AS DATE)) orders_per_day;")
						//.setInteger("fgid", fgid)
						.list();
				System.out.println("size= "+st.size());
				for (Object[] ss : st) {
					System.out.println("date: "+ss[0].toString()+" num: "+ss[1].toString()+" cost: "+ss[2].toString());
					
				}


				// commit
				session.getTransaction().commit();
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				factory.close();
			}

			return st;
		}
}
