package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Friend;

@Repository
public interface FriendDAO {
	
public List<Friend> getMyFriends(String user_id);

public Friend get(String user_id,String friend_id);

public boolean save(Friend friend);

public boolean update(Friend friend);

public void delete(String user_id,String friend_id);

public List<Friend> getNewFriendRequests(String user_id);

public void setOnline(String user_id);

public void setOffline(String user_id);
}
