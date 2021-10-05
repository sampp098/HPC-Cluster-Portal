package bab.mvc.data.services;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import bab.mvc.data.entities.complex.GroupUsersComplex;
import bab.mvc.data.entities.complex.UserComplex;
import bab.mvc.data.entities.pure.FeesGroup;
import bab.mvc.data.entities.pure.FeesGroupUsers;
import bab.mvc.data.entities.pure.GroupUsers;
import bab.mvc.data.entities.pure.Groups;
import bab.mvc.data.entities.pure.User;


@Component
public class UserService {

	public User auth(String userName, String pass) {
		System.out.println("------------in UserAuth--> user: " + userName
				+ " pass:" + pass);

		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			/*st1 = session.createQuery(
					"from User s where s.userName='" + userName
							+ "' AND s.pass= '" + pass + "'").list();*/
			
			st1 = session.createQuery(
					"from User s where s.userName=:username AND s.pass=:pass AND s.isactive in (0,1)").setParameter("username", userName).setParameter("pass", pass).list();
			
			for (User ss : st1) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		if (st1.size() >= 1) {
			return st1.get(0);
		}
		return null;
	}
	
	
	public User getEarlyRegisterdUser(String userName) {
		System.out.println("------------in UserAuth--> user: " + userName);

		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			/*st1 = session.createQuery(
					"from User s where s.userName='" + userName + "'")
					.list();*/
			st1 = session.createQuery(
					"from User s where s.userName=:username AND s.isactive in (2)").setParameter("username", userName)
					.list();

			for (User ss : st1) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		if (st1.size() >= 1) {
			return st1.get(0);
		}
		return null;
	}
	
	public User getUser(String userName) {
		System.out.println("------------in UserAuth--> user: " + userName);

		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			/*st1 = session.createQuery(
					"from User s where s.userName='" + userName + "'")
					.list();*/
			st1 = session.createQuery(
					"from User s where s.userName=:username AND s.isactive in (0,1)").setParameter("username", userName)
					.list();

			for (User ss : st1) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		if (st1.size() >= 1) {
			return st1.get(0);
		}
		return null;
	}
	public User getUserByEmail(String username,String email) {
		System.out.println("------------in UserAuth--> user: " + email);

		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			/*st1 = session.createQuery(
					"from User s where s.userName='" + userName + "'")
					.list();*/
			st1 = session.createQuery(
					"from User s where s.email=:email AND s.userName=:username AND s.isactive in (0,1)")
					.setParameter("email", email)
					.setParameter("username", username)
					.list();

			for (User ss : st1) {
				System.out.println("-----> user email"+ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		if (st1.size() >= 1) {
			return st1.get(0);
		}
		return null;
	}
	
	public User getUserByUid(int uid) {

		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			/*st1 = session.createQuery(
					"from User s where s.uid='" + uid + "'")
					.list();*/
			
			st1 = session.createQuery(
					"from User s where s.uid=:uid AND s.isactive in (0,1)").setInteger("uid", uid)
					.list();

			for (User ss : st1) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		if (st1.size() >= 1) {
			return st1.get(0);
		}
		return null;
	}
	
	public List<User> getUsers(String fname, String lname, String userName) {
		System.out.println("------------in UserAuth--> user: " + userName);

		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			st1 = session.createQuery(
					"from User s where s.userName LIKE :username AND s.fname LIKE :fname AND s.lname LIKE :lname AND s.isactive in (0,1)")
					.setString("username", "%"+userName+"%")
					.setString("fname", "%"+fname+"%")
					.setString("lname", "%"+lname+"%")
					.list();

			for (User ss : st1) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return st1;
	}

	public List<User> getOwnerableUsers(String fname, String lname, String userName) {
		System.out.println("------------in UserAuth--> user: " + userName);

		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(FeesGroupUsers.class)
				.addAnnotatedClass(FeesGroup.class)
				.addAnnotatedClass(Groups.class)
				.addAnnotatedClass(GroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			st1 = session.createQuery(
					"select u from User u, Groups g, GroupUsers gu where"
							+ " u.userName LIKE :username AND u.fname LIKE :fname AND u.lname LIKE :lname"
							+ " AND u.uid not in (select fg.uid from FeesGroupUsers fg ) "
							+ " AND u.uid not in (select f.uid from FeesGroup f )  AND u.isactive in (0,1)"
							+ " AND	u.uid=gu.uid AND gu.gid=g.gid AND g.gname='HPCUser'")
					.setString("username", "%"+userName+"%")
					.setString("fname", "%"+fname+"%")
					.setString("lname", "%"+lname+"%")
					.list();

			for (User ss : st1) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return st1;
	}
	public List<User> getHPCUsers(String fname, String lname, String userName) {
		System.out.println("------------in UserAuth--> user: " + userName);

		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).addAnnotatedClass(GroupUsers.class).addAnnotatedClass(FeesGroup.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			st1 = session.createQuery(
					"select u from User u, GroupUsers gu where u.userName LIKE :username AND u.fname LIKE :fname AND u.lname LIKE :lname"
							+ " AND u.uid= gu.uid AND gu.gid='2' AND u.isactive in (0,1)")
					.setString("username", "%"+userName+"%")
					.setString("fname", "%"+fname+"%")
					.setString("lname", "%"+lname+"%")
					.list();

			for (User ss : st1) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return st1;
	}
	public List<User> readWithoutHPCGroups() {

		List<User> us;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(FeesGroupUsers.class)
				.addAnnotatedClass(Groups.class)
				.addAnnotatedClass(GroupUsers.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			//us = session.createQuery("from User u where u.uid not in (select f.uid from FeesGroupUsers f )  AND u.isactive in (0,1) ").list();
			us = session.createQuery("select u from User u, Groups g, GroupUsers gu  where u.uid not in (select f.uid from FeesGroupUsers f )  AND u.isactive in (0,1) AND	u.uid=gu.uid AND gu.gid=g.gid AND g.gname='HPCUser'").list();
			for (User ss : us) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return us;
	}
	
	public List<Object[]> readHPCGroupMembers(int fgid) {

		List<Object[]> us;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).addAnnotatedClass(FeesGroupUsers.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			us = session.createQuery("select u,f from User u, FeesGroupUsers f where u.uid=f.uid AND f.fgid=:fgid AND u.isactive in (0,1)")
					.setInteger("fgid", fgid)
					.list();

			for (Object[] ss : us) {
				System.out.println(((User) ss[0]).toString());
				System.out.println(((FeesGroupUsers) ss[1]).toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return us;
	}
	
	public List<User> readHPCGroupOwners() {

		List<User> us;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).addAnnotatedClass(FeesGroup.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			us = session.createQuery("select u from User u, FeesGroup f where u.uid=f.uid AND u.isactive in (0,1)").list();

			for (User ss : us) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return us;
	}
	
	public List<User> read() {

		List<User> us;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			us = session.createQuery("from User s AND s.isactive in (0,1)").list();

			for (User ss : us) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return us;
	}
	
	public List<UserComplex> readUsersComplex(String fname, String lname, String userName) {
		System.out.println("------------in UserAuth--> user: " + userName);

		List<UserComplex> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(UserComplex.class)
				.addAnnotatedClass(GroupUsersComplex.class)
				.addAnnotatedClass(Groups.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			st1 = session.createQuery(
					"from UserComplex s where s.userName LIKE :username AND s.fname LIKE :fname AND s.lname LIKE :lname AND s.isactive in (0,1)")
					.setString("username", "%"+userName+"%")
					.setString("fname", "%"+fname+"%")
					.setString("lname", "%"+lname+"%")
					.list();

			for (UserComplex ss : st1) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return st1;
	}
	
	public List<UserComplex> readUsersComplex() {

		List<UserComplex> us;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(UserComplex.class)
				.addAnnotatedClass(GroupUsersComplex.class)
				.addAnnotatedClass(Groups.class)
				.buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			us = session.createQuery("select s from UserComplex s where s.isactive in (0,1)").list();

			for (UserComplex ss : us) {
				System.out.println(ss.toString());
			}

			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		return us;
	}
	
	
	public void update(User u) {
		List<User> st1;
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			/*
			session.createQuery("update User set fname = '"+u.getFname()+"',"
					+ "lname = '"+u.getLname()+"',"
					+ "pass = '"+u.getPass()+"',"
					+ "email = '"+u.getEmail()+"',"
					+ "image = '"+u.getImage()+"',"
					//+ "description = '"+u.getDescription()+"'"
					+ " where userName = '"+u.getUserName()+"' ").executeUpdate();
			*/
			
			session.update(u);
			
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
		

	}
	
	public void delete(String userName) {

		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.createQuery(
					"delete from User where userName=:username")
					.setString("username", userName)
					.executeUpdate();


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}
	public void deleteActivationExpired() {

		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.createQuery(
					"delete from User where ADDDATE(createTime,1) <  NOW() and isactive='2'")
					.executeUpdate();


			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}
	public void create(User u) {

		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.save(u);
			// commit
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}
	
	public boolean create(String fname,
			   		   String lname,
					   String username,
					   String pass,
					   String email,
					   Date date,
					   int isactive) {
		boolean success=false;
		User u=new User(fname, lname, username, pass, email,date, isactive);
		u.setFacultyid(1);
		u.setUnid(1);;
		u.setState(3);
		u.setGradeid(1);
		u.setExptime(date);
		
		//////////////////////
		u.setRecommendation("");
		u.setNationalCard("");
		u.setSt_pr_card("");
		//////////////////////
		
		u.setAddress("");
		u.setPhone("");
		u.setNationalCode("");
		u.setSN_PN("");
		u.setField("");
		// session factory creation
		SessionFactory factory = new Configuration().configure("hibernate.xml")
				.addAnnotatedClass(User.class).buildSessionFactory();
		// session creation
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.save(u);
			// commit
			session.getTransaction().commit();
			success=true;
		} finally {
			factory.close();
		}
		return success;
	}

}
