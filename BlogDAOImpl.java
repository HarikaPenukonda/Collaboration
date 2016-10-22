package com.niit.collaboration.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.niit.collaboration.model.Blog;

@Repository(value="BlogDAO")
public class BlogDAOImpl implements BlogDAO {
	private static final Query SessionFactory = null;
	@Autowired
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
   @Transactional
	public boolean save(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
   @Transactional
	public boolean update(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public boolean delete(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public Blog get(String id) {

		String hql = "from Blog where id=" + " ' " + id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog> list = query.list();
		if (list == null)

			return null;
		else {
			return list.get(0);
		}
	}
@Transactional
	public List<Blog> list() {
		String hql = "from Blog";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
}
		



	
	
	


