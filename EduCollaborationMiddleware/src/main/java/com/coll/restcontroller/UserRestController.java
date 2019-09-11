package com.coll.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.UserDAO;
import com.coll.model.Job;
import com.coll.model.UserDetail;

@RestController
public class UserRestController {
	
	@Autowired
	UserDAO userDAO;
	
	@PostMapping(value="/registerUser", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> registerUser(@RequestBody UserDetail user) {
		
		user.setRole("ROLE_USER");
		user.setStatus("A");
		user.setIsOnline("Y");
		
		if(userDAO.registerUser(user)) {
			return new ResponseEntity<String>("User Registeres", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/checkUser")
	public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail userDetail, HttpSession session) {
		
		UserDetail user=userDAO.checkUser(userDetail.getUsername(), userDetail.getPassword());
		
		if(user!=null) {
			session.setAttribute("userDetail", user);
			return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<UserDetail>(user, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getUser/{username}")
	public ResponseEntity<UserDetail> getUser(@PathVariable("username")String username) {
		UserDetail user=userDAO.getUser(username);
		return new ResponseEntity<UserDetail>(user, HttpStatus.OK);
	}
	
	@PostMapping(value="/updateUser", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody UserDetail user) {
		
		UserDetail tempUser=userDAO.getUser(user.getUsername());
		
		tempUser.setName(user.getName());
		tempUser.setEmailId(user.getEmailId());
		
		if(userDAO.updateProfile(tempUser)) {
			return new ResponseEntity<String>("Profile Updated", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showAllUsers")
	public ResponseEntity<List<UserDetail>> listUsers() {
		
		List<UserDetail> userList=userDAO.listUsers();
		
		if(userList.size()>0) {
			return new ResponseEntity<List<UserDetail>>(userList, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<List<UserDetail>>(userList, HttpStatus.NOT_FOUND);
		}
	}
}
