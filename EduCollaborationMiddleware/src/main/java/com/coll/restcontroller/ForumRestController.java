package com.coll.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.ForumDAO;
import com.coll.model.Forum;

@RestController
public class ForumRestController {
	
	@Autowired
	ForumDAO forumDAO;
	
	@GetMapping(value="/showAllForums")
	public ResponseEntity<List<Forum>> getAllForums() {
		
		List<Forum> forumList=forumDAO.listForums();
		
		if(forumList.size()>0) {
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<List<Forum>>(forumList, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/addForum", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addForum(@RequestBody Forum forum) {

		forum.setCreateDate(new Date());
		forum.setStatus("NA");
		
		if(forumDAO.addForum(forum)) {
			return new ResponseEntity<String>("Forum Added", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getForum/{forumId}")
	public ResponseEntity<Forum> getForum(@PathVariable("forumId")int forumId) {
		
		Forum forum=forumDAO.getForum(forumId);
		
		if(forum!=null) {
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/updateForum", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateForum(@RequestBody Forum forum) {
		
		Forum tempForum=forumDAO.getForum(forum.getForumId());
		
		tempForum.setForumContent(forum.getForumContent());
		tempForum.setStatus(forum.getStatus());
		
		if(forumDAO.updateForum(tempForum)) {
			return new ResponseEntity<String>("Forum Updated", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteForum/{forumId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteForum(@PathVariable("forumId")int forumId) {
		
		Forum forum=forumDAO.getForum(forumId);
		
		if(forumDAO.deleteForum(forum)) {
			return new ResponseEntity<String>("Forum Deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/approveForum/{forumId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> approveForum(@PathVariable("forumId")int forumId) {
		
		Forum forum=forumDAO.getForum(forumId);
		
		if(forumDAO.approvedForum(forum)) {
			return new ResponseEntity<String>("Forum Approved", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/rejectForum/{forumId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> rejectForum(@PathVariable("forumId")int forumId) {
		
		Forum forum=forumDAO.getForum(forumId);
		
		if(forumDAO.rejectForum(forum)) {
			return new ResponseEntity<String>("Forum Rejected", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
}
