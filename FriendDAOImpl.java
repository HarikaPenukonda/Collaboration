package com.niit.collaboration.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Friend;

@Repository
public class FriendDAOImpl implements FriendDAO {
	
	private static final Logger Log = LoggerFactory.getLogger(FriendDAOImpl.class);

	@Autowired(required = true)
	private SessionFactory sessionFactory;
	
	public FriendDAOImpl(SessionFactory sessionFactory){
		try{
			this.sessionFactory = sessionFactory;
		}
		catch(Exception e){
		Log.error("Unable to connect to db");
		e.printStackTrace();
		}
	}
	
	private Integer getMaxId(){
		Log.debug("Starting of the method getMaxId");
	String hql = "select max(id) from Friend";
	Query query =  sessionFactory.getCurrentSession().createQuery(hql);
	Integer maxID = (Integer) ((Criteria) query).uniqueResult();
	Log.debug("Max id:"+maxID);
	return maxID;
	
	}
	
	@Transactional
	public boolean save(Friend friend){
		try{
			Log.debug("previous id"+getMaxId());
			friend.setId(getMaxId()+1);
			Log.debug("generated Id:"+getMaxId());
			sessionFactory.getCurrentSession().save(friend);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return false;
		}
	
	@Transactional
	public boolean update(Friend friend){
		try{
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	@Transactional
	public void delete(String user_id, String friend_id){
		Friend friend = new Friend();
		friend.setFriend_ID(friend_id);
		friend.setUser_ID(user_id);
		sessionFactory.getCurrentSession().delete(friend);
		
		
	}
	
	@Transactional
	public List<Friend> getMyFriends(String user_id){
		String hql = "from Friend where user_id" + "'" +user_id + "' and status = '"+"A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Friend> list = (List<Friend>) query.getResultList();
		return list;
		
	}
	
	
	@Transactional
	public List<Friend> getNewFriendRequests(String user_id){
		String hql = "from Friend where user_id" + "'" +user_id + "' and status = '"+"N'";	
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Friend> list = (List<Friend>) query.getResultList();
		return list;
	}
	
	@Transactional
	public Friend get(String user_id, String friend_id){
		String hql = "from Friend where user_id" + "'" +user_id + "' and FRIEND_ID = '"+friend_id;	
		Log.debug("hql:"+hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Friend> list = (List<Friend>) query.getResultList();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
	
		return null;
	}


	
	@Transactional
	public void setOnline(String user_id){
		Log.debug("starting of the method setOnline");
		String hql = "UPDATE Friend SET isOnline = 'Y' where user_id'"
				+ user_id +"'";
		Log.debug("hql :" +hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		Log.debug("Ending of the method setOnline");
		
	}
	
	@Transactional
	public void setOffline(String user_id){
		Log.debug("starting of the method setOnline");
		String hql = "UPDATE Friend SET isOnline = 'Y' where user_id'"
				+ user_id +"'";
		Log.debug("hql :" +hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		Log.debug("Ending of the method setOffline");
		
	}
	
	
	
	
	
}
		



	
	
	


