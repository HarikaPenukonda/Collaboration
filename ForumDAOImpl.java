package com.niit.collaboration.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.niit.collaboration.model.Forum;

@Repository(value="ForumDAO")
public class ForumDAOImpl implements ForumDAO {
	private static final Query SessionFactory = null;
	@Autowired
	private SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
   @Transactional
	public boolean save(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
   @Transactional
	public boolean update(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public boolean delete(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public Forum get(String id) {

		String hql = "from Forum where id=" + " ' " + id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Forum> list = query.list();
		if (list == null)

			return null;
		else {
			return list.get(0);
		}
	}
@Transactional
	public List<Forum> list() {
		String hql = "from Forum";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
}
		



	
	
	


