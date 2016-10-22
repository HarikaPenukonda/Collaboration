package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Friend;

@Repository
public interface FriendDAO {
	
public boolean save (Friend friend);
	
	public boolean update (Friend friend);
	
	public boolean delete (Friend friend);
	
	public Friend  get(String id);
	
	public List<Friend> list();
	

}
