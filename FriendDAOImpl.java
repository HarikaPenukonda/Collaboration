package com.niit.collaboration.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.niit.collaboration.model.Friend;

@Repository(value="FriendDAO")
public class FriendDAOImpl implements FriendDAO {
	private static final Query SessionFactory = null;
	@Autowired
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
   @Transactional
	public boolean save(Friend friend) {
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
   @Transactional
	public boolean update(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public Friend get(String id) {

		String hql = "from Friend where id=" + " ' " + id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Friend> list = query.list();
		if (list == null)

			return null;
		else {
			return list.get(0);
		}
	}
@Transactional
	public List<Friend> list() {
		String hql = "from Friend";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
}
		



	
	
	


