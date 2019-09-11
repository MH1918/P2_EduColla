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

import com.coll.dao.BlogCommentDAO;
import com.coll.dao.BlogDAO;
import com.coll.model.BlogComment;

@RestController
public class BlogCommentRestController {
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@Autowired
	BlogDAO blogDAO;
	
	@PostMapping(value="/addBlogComment", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment comment) {

		comment.setBlogCommentDate(new Date());
		
		if(blogCommentDAO.addComment(comment)) {
			return new ResponseEntity<String>("Blog Comment Added", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteBlogComment/{blogCommentId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteBlogComment(@PathVariable("blogCommentId")int blogCommentId) {

		BlogComment comment=blogCommentDAO.getBlogComment(blogCommentId);
		
		if(blogCommentDAO.deleteComment(comment)) {
			return new ResponseEntity<String>("Blog Comment Deleted", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/listBlogComments/{blogId}")
	public ResponseEntity<List<BlogComment>> listBlogComments(@PathVariable("blogId")int blogId) {
		
		List<BlogComment> listComments=blogCommentDAO.listBlogComments(blogId);
		
		if(listComments.size()>0) {
			return new ResponseEntity<List<BlogComment>>(listComments, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<List<BlogComment>>(listComments, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getBlogComment/{blogCommentId}")
	public ResponseEntity<BlogComment> getBlogComment(@PathVariable("blogCommentId")int blogCommentId) {
		
		BlogComment comment=blogCommentDAO.getBlogComment(blogCommentId);
		
		if(comment!=null) {
			return new ResponseEntity<BlogComment>(comment, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<BlogComment>(comment, HttpStatus.NOT_FOUND);
		}
	}
}
