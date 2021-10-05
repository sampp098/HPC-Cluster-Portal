package bab.mvc.data.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import bab.mvc.Execute;
import bab.mvc.data.entities.complex.GroupJobsComplex;
import bab.mvc.data.entities.complex.JobsComplex;
import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.HPCTariff;
import bab.mvc.data.entities.pure.Jobs;
import bab.mvc.data.entities.pure.User;

@Component
public class JobsService {
	
	public List<Jobs> read() {

		List<Jobs> st = new ArrayList<Jobs>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Jobs s ").list();

			for (Jobs ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<Jobs> ReadByJid(int jid) {

		List<Jobs> st = new ArrayList<Jobs>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Jobs s where s.jid=:jid")
					.setInteger("jid", jid)
					.list();
			//"from User s where s.userName LIKE '%" + userName + "%' AND s.fname LIKE '%"+fname+"%' AND s.lname LIKE '%"+lname+"%'")

			for (Jobs ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<Jobs> ReadSearch(int uid, String jname) {

		List<Jobs> st = new ArrayList<Jobs>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Jobs s where s.jname LIKE :jname AND s.uid=:uid")
					.setString("jname", "%"+jname+"%")
					.setInteger("uid", uid)
					.list();
			//"from User s where s.userName LIKE '%" + userName + "%' AND s.fname LIKE '%"+fname+"%' AND s.lname LIKE '%"+lname+"%'")

			for (Jobs ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<Jobs> ReadByJobid(int jobid) {

		List<Jobs> st = new ArrayList<Jobs>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Jobs s where s.jobid=:jobid")
					.setInteger("jobid", jobid)
					.list();

			for (Jobs ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	public List<Jobs> ReadByUid(int uid) {

		List<Jobs> st = new ArrayList<Jobs>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Jobs s where s.uid=:uid")
					.setInteger("uid", uid)
					.list();

			for (Jobs ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<Jobs> ReadByUid(int uid, String appname, String date, String state) {

		List<Jobs> st = new ArrayList<Jobs>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from Jobs s where s.uid=:uid AND s.appname LIKE :appname AND s.state LIKE :state  AND s.createDate >= :date")
					.setInteger("uid", uid)
					.setString("appname", "%"+appname+"%")
					.setString("state", "%"+state+"%")
					.setString("date", date)
					.list();

			for (Jobs ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<JobsComplex> ReadByUidComplex(int uid, String appname, String date, String state) {

		List<JobsComplex> st = new ArrayList<JobsComplex>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(JobsComplex.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(HPCTariff.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"from JobsComplex s where s.user.uid=:uid AND s.appname LIKE :appname AND s.state LIKE :state  AND s.createDate >= :date order by s.createDate desc")
					.setInteger("uid", uid)
					.setString("appname", "%"+appname+"%")
					.setString("state", "%"+state+"%")
					.setString("date", date)
					.list();

			for (JobsComplex ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public void create(Jobs j) {
		
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.save(j);
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}
	
	public void delete(int jid) {

		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.createQuery(
					"delete from Jobs where jid=:jid")
					.setInteger("jid", jid)
					.executeUpdate();


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}
	
	public void update(Jobs j) {


		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.update(j);


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}

	public void updateHPCGroup(Jobs j) {


		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.update(j);


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}
	
	public GroupJobsComplex ReadGroupJobs(int fgid) {

		List<GroupJobsComplex> st = new ArrayList<GroupJobsComplex>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(JobsComplex.class)
				.addAnnotatedClass(HPCTariff.class)
				.addAnnotatedClass(GroupJobsComplex.class)
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"Select s from GroupJobsComplex s where s.fgid=:fgid")
					.setInteger("fgid", fgid)
					.list();

			for (GroupJobsComplex ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st.get(0);
	}
	
	
	
	public List<GroupJobsComplex> ReadGroupJobs() {

		List<GroupJobsComplex> st = new ArrayList<GroupJobsComplex>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(JobsComplex.class)
				.addAnnotatedClass(HPCTariff.class)
				.addAnnotatedClass(GroupJobsComplex.class)
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"Select gjc from GroupJobsComplex gjc ").list();

			for (GroupJobsComplex ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public List<GroupJobsComplex> ReadGroupJobsNotC(List<Integer> jobids) {

		List<GroupJobsComplex> st = new ArrayList<GroupJobsComplex>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(JobsComplex.class)
				.addAnnotatedClass(HPCTariff.class)
				.addAnnotatedClass(GroupJobsComplex.class)
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			st = session.createQuery(
					"Select gjc from GroupJobsComplex gjc JOIN fetch gjc.jobs j, JobsComplex jc where j.fgid = jc.fgid AND j.jid=jc.jid AND j.jobid in (:jobids) "
					+ "")
					.setParameterList("jobids", jobids)
					.list();
	        st = new ArrayList(new HashSet(st)); //no order

			for (GroupJobsComplex ss : st) {
				System.out.println(ss.toString());
			}


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
	
	public void updateQstat(List<FeesGroup> fgs, List<Jobs> js) {
		System.out.println("--------------updateQstat");
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(Jobs.class)
				.addAnnotatedClass(FeesGroup.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			for (Jobs j : js) {
				session.update(j);
				System.out.println("--------------session.update(j)");
			}
			
			for (FeesGroup fg : fgs) {
				session.update(fg);
				System.out.println("-------------session.update(fg)");
			}
			
			//session.save(j);
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}
	
	public List<Object[]> clusterSummary(int days) {
		List<Object[]> st = new ArrayList<Object[]>();
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();
		//SELECT sum(cputime) FROM jobs j, fees_group fg where fg.fgid=j.fgid and fg.uid='2';
		try {
			double full=(days*24*Execute.nodes*Execute.cores)*0.01;
			session.beginTransaction();
			st = session.createSQLQuery("SELECT sum(cputime) as cputime,sum(cputime)/("+full+") as utilization, count(jid), sum(corenum)/count(jid)  "
					+ "FROM jobs j where createdate > (now() - INTERVAL "+days+" DAY);").list();
			
			if(st.get(0) !=null && st.get(0)[0] !=null) {
				
			}else {
				st = new ArrayList<Object[]>();
				Object[] obj=new Object[4];
				obj[0]=new Double(0.0);
				obj[1]=new Double(0.0);
				obj[2]=new Double(0.0);
				obj[3]=new Double(0.0);
				st.add(obj);
			}
			
			for (Object[] ss : st) {
			//	if(ss !=null) {
					for (Object object : ss) {
			//			if(object !=null) {
							System.out.println("*********************_____>"+object.toString());
			//			}else {
			//				object=new Double(0.0);
			//				System.out.println("*********************_____>"+object.toString());
			//			}
					}
			//	}
				
			}
			
			
			System.out.println("------------------------>>>> "+st.get(0)[0].toString());

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		return st;
	}
}
