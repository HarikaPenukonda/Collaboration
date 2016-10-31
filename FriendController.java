package com.niit.collaboration.restcontroller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;

public class FriendController {
	
	private static final Logger Logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	Friend friend;
	
	
	
	@RequestMapping(value = "/myFriends",method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(HttpSession session){
		Logger.debug("calling method getMyFriends");
		User loggerInUser = (User) session.getAttribute("loggedInUser");
		List<Friend> myFriends = friendDAO.getMyFriends(loggerInUser.getUser_id());
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
	}
	@RequestMapping(value = "/addFriend/{friendID}",method = RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendID") String friendID,HttpSession session){
	Logger.debug("calling method sendFriendRequest");
	User loggedInUser = (User) session.getAttribute("loggedInUser");
	friend.setUser_ID(loggedInUser.getUser_id());
	friend.setFriend_ID(friendID);
	friend.setStatus("N");
	friendDAO.save(friend);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	
	}
	@RequestMapping(value = "/unFriend/{friendID}",method = RequestMethod.GET)
	public ResponseEntity<Friend> unFriend(@PathVariable("friendID") String friendID,HttpSession session){
	Logger.debug("calling method sendFriendRequest");
	updateRequest(friendID,"U",session);
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	
	}
	
	@RequestMapping(value = "/rejectFriend/{friendID}",method = RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriendFriendRequest(@PathVariable("friendID") String friendID,HttpSession session){
		Logger.debug("calling method rejectFriendRequest");
		updateRequest(friendID,"N",session);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	private void updateRequest(String friendID, String string, HttpSession session) {
		String loggedInUserID  = (String) session.getAttribute("loggedInUserID");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friend.setUser_ID(loggedInUser.getUser_id());
		friend.setFriend_ID(friendID);
		friend.setStatus("R");
		friendDAO.update(friend);
	}
	
	@RequestMapping(value = "/getMyFriendRequests",method = RequestMethod.GET)
	public ResponseEntity<Friend> getMyFriendRequests(HttpSession session){
		Logger.debug("calling method getMyFriendRequests");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		friendDAO.getNewFriendRequests(loggedInUser.getUser_id());
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
}
