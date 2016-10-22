package com.niit.collaboration.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.niit.collaboration.model.User;

@Repository(value="UserDAO")
public class UserDAOImpl implements UserDAO {
	private static final Query SessionFactory = null;
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
   @Transactional
	public boolean save(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
   @Transactional
	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public boolean delete(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public User get(String id) {

		String hql = "from User where id=" + " ' " + id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.list();
		if (list == null)

			return null;
		else {
			return list.get(0);
		}
	}
@Transactional
	public List<User> list() {
		String hql = "from User";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
}
		



	
	
	


