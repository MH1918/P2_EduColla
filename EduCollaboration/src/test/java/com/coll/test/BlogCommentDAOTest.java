package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogCommentDAO;
import com.coll.model.Blog;
import com.coll.model.BlogComment;

public class BlogCommentDAOTest {

	static BlogCommentDAO blogCommentDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		blogCommentDAO=(BlogCommentDAO)context.getBean("blogCommentDAO");
	}
	
	@Ignore
	@Test
	public void addCommentTest() {
		BlogComment comment=new BlogComment();
		comment.setBlogId(953);
		comment.setBlogCommentText("This is a nice Blog. Everyone should read this Blog.");
		comment.setBlogCommentDate(new java.util.Date());
		comment.setUsername("subha");
		
		assertTrue("Problem Occured during Adding a Comment: ", blogCommentDAO.addComment(comment));
	}
	
	@Ignore
	@Test
	public void deleteCommentTest() {
		BlogComment comment=blogCommentDAO.getBlogComment(952);
		
		assertTrue("Problem Occured while deleting a Comment: ", blogCommentDAO.deleteComment(comment));
	}
	
	@Ignore
	@Test
	public void listBlogCommentsTest() {
		List<BlogComment> commentList=blogCommentDAO.listBlogComments(953);
		
		assertTrue("Problem in Retrieving Comments in a Blog: ", commentList.size()>0);
		
		for(BlogComment comment:commentList) {
			System.out.println(comment.getBlogCommentText());
			System.out.println(comment.getBlogCommentDate());
		}
	}
}
