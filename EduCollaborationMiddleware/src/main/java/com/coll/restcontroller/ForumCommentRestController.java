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

import com.coll.dao.ForumCommentDAO;
import com.coll.dao.ForumDAO;
import com.coll.model.ForumComment;

@RestController
public class ForumCommentRestController {
	
	@Autowired
	ForumCommentDAO forumCommentDAO;
	
	@Autowired
	ForumDAO forumDAO;
	
	@PostMapping(value="/addForumComment", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addForumComment(@RequestBody ForumComment comment) {

		comment.setForumCommentDate(new Date());
		
		if(forumCommentDAO.addForumComment(comment)) {
			return new ResponseEntity<String>("Forum Comment Added", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteForumComment/{forumCommentId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteForumComment(@PathVariable("forumCommentId")int forumCommentId) {

		ForumComment comment=forumCommentDAO.getForumComment(forumCommentId);
		
		if(forumCommentDAO.deleteForumComment(comment)) {
			return new ResponseEntity<String>("Forum Comment Deleted", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/listForumComments/{forumId}")
	public ResponseEntity<List<ForumComment>> listBlogComments(@PathVariable("forumId")int forumId) {
		
		List<ForumComment> listComments=forumCommentDAO.listForumComments(forumId);
		
		if(listComments.size()>0) {
			return new ResponseEntity<List<ForumComment>>(listComments, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<List<ForumComment>>(listComments, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getForumComment/{forumCommentId}")
	public ResponseEntity<ForumComment> getForumComment(@PathVariable("forumCommentId")int forumCommentId) {
		
		ForumComment comment=forumCommentDAO.getForumComment(forumCommentId);
		
		if(comment!=null) {
			return new ResponseEntity<ForumComment>(comment, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ForumComment>(comment, HttpStatus.NOT_FOUND);
		}
	}
}
