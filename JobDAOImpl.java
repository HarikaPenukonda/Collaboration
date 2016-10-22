package com.niit.collaboration.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.niit.collaboration.model.Job;

@Repository(value="JobDAO")
public class JobDAOImpl implements JobDAO {
	private static final Query SessionFactory = null;
	@Autowired
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
   @Transactional
	public boolean save(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
   @Transactional
	public boolean update(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public boolean delete(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	public Job get(String id) {

		String hql = "from Job where id=" + " ' " + id + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Job> list = query.list();
		if (list == null)

			return null;
		else {
			return list.get(0);
		}
	}
@Transactional
	public List<Job> list() {
		String hql = "from Job";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
}
		



	
	
	


