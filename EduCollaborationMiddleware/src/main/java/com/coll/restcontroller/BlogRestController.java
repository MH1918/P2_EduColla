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

import com.coll.dao.BlogDAO;
import com.coll.model.Blog;

@RestController
public class BlogRestController {
	
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping(value="/showAllBlogs")
	public ResponseEntity<List<Blog>> getAllBlogs() {
		
		List<Blog> blogList=blogDAO.listBlogs();
		
		if(blogList.size()>0) {
			return new ResponseEntity<List<Blog>>(blogList, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<List<Blog>>(blogList, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/addBlog", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addBlog(@RequestBody Blog blog) {

		blog.setCreateDate(new Date());
		blog.setStatus("NA");
		blog.setLikes(0);
		blog.setDislikes(0);
		
		if(blogDAO.addBlog(blog)) {
			return new ResponseEntity<String>("Blog Added", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId")int blogId) {
		
		Blog blog=blogDAO.getBlog(blogId);
		
		if(blog!=null) {
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/approveBlog/{blogId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> approveBlog(@PathVariable("blogId")int blogId) {
		
		Blog blog=blogDAO.getBlog(blogId);
		
		if(blogDAO.approvedBlog(blog)) {
			return new ResponseEntity<String>("Approved", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/rejectBlog/{blogId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId")int blogId) {
		
		Blog blog=blogDAO.getBlog(blogId);
		
		if(blogDAO.rejectBlog(blog)) {
			return new ResponseEntity<String>("Rejected", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/incrementLikes/{blogId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> incrementLikes(@PathVariable("blogId")int blogId) {
		
		if(blogDAO.incrementLikes(blogId)) {
			return new ResponseEntity<String>("Likes Incremented", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/incrementDislikes/{blogId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> incrementDislikes(@PathVariable("blogId")int blogId) {
		
		if(blogDAO.incrementDislikes(blogId)) {
			return new ResponseEntity<String>("Sorry!! Don't like it.", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/updateBlog", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog) {
		
		Blog tempBlog=blogDAO.getBlog(blog.getBlogId());
		
		tempBlog.setBlogName(blog.getBlogName());
		tempBlog.setBlogContent(blog.getBlogContent());
		
		if(blogDAO.updateBlog(tempBlog)) {
			return new ResponseEntity<String>("Blog Updated", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteBlog/{blogId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId")int blogId) {
		
		Blog tempBlog=blogDAO.getBlog(blogId);
		
		if(blogDAO.deleteBlog(tempBlog)) {
			return new ResponseEntity<String>("Blog Deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
}
