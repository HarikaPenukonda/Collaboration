package com.niit.collaboration.restcontroller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;


@RestController
public class UserController {
	
	private static final Logger logger	= LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	FriendDAO friendDAO;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers(){
		logger.debug("calling method listAllUsers");
		List<User> user=userDAO.list();
		if(user.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}

	@RequestMapping(value="/user/",method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){
		logger.debug("calling method createUser" + user.getUser_id());
		if(userDAO.save(user)== true){
			return new ResponseEntity<User>(user,HttpStatus.OK);
						
		}
		logger.debug("user already exists with id:" + user.getUser_id());
		user.setErrormessage("user already exists with id:" + user.getUser_id());
		return new ResponseEntity<User>(user,HttpStatus.OK);
			}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") String user_id,@RequestBody User user){
		logger.debug("calling method updateUser" + user.getUser_id());
		/*if(userDAO.get(user_id)==null){
			logger.debug("user does not exists with id:" + user.getUser_id());		
			user=new User();
			user.setErrormessage("user does not exists with id:" + user.getUser_id());
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);
		}*/
		
		if(userDAO.update(user)== true)
			logger.debug("user updated successfully");
			return new ResponseEntity<User> (user,HttpStatus.OK);	
		}
			
	

	
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") String user_id){
		logger.debug("calling method deleteUser for user id: " + user_id);
		User user=userDAO.get(user_id);
		/*if(user==null){
			logger.debug("user does not exists with id:" + user_id);
			user=new User();
			user.setErrormessage("user does not exists with id:" + user_id);
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);	*/
		
		{
	if(	userDAO.delete(user))
		logger.debug("user deleted successfully");
		return new ResponseEntity<User> (user,HttpStatus.OK);		
	}
	}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String user_id){
		logger.debug("calling method getUser for user id: " + user_id);
		User user=userDAO.get(user_id);
		if(user==null){
			logger.debug("user does not exists with id:" + user_id);
			user=new User();
			user.setErrormessage("user does not exists with id:" + user_id);
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);
		}
		logger.debug("user exists with id:" + user_id);
		return new ResponseEntity<User> (user,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/authenticate/",method = RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user,HttpSession session){
		//logger.debug("calling method authenticate");
		user = userDAO.authenticate(user.getUser_id(),user.getPassword());
		if(user == null){
			user = new User();
			user.setErrorcode("404");
			user.setErrormessage("Invalid Credentials. Please enter valid credentials");
			logger.debug("In valid credentials");
		}
		else{
			user.setErrorcode("200");
			logger.debug("valid Credentials");
			session.setAttribute("loggedInUser",user);
			session.setAttribute("loggedInUser", user.getUser_id());
			friendDAO.setOnline(user.getUser_id());
			userDAO.setOnline(user.getUser_id());
			
		}
		return new ResponseEntity<User>(user , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/logout",method = RequestMethod.GET)
	public String logout(HttpSession session){
		logger.debug("calling method logout");
		String loggedInUserID = (String)session.getAttribute("loggedInUserID");
		friendDAO.setOffline(loggedInUserID);
		userDAO.setOffline(loggedInUserID);
		
		session.invalidate();
		return("You successfully loggedOut");
		
	}


}


