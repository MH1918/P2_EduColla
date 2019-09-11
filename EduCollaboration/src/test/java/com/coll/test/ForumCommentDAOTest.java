package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.ForumCommentDAO;
import com.coll.model.BlogComment;
import com.coll.model.ForumComment;

public class ForumCommentDAOTest {

	static ForumCommentDAO forumCommentDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		forumCommentDAO=(ForumCommentDAO)context.getBean("forumCommentDAO");
	}
	
	@Ignore
	@Test
	public void addCommentTest() {
		ForumComment comment=new ForumComment();
		comment.setForumId(953);
		comment.setForumCommentText("This is a nice Forum. Everyone should join this.");
		comment.setForumCommentDate(new java.util.Date());
		comment.setUsername("subha");
		
		assertTrue("Problem Occured during Adding a Comment: ", forumCommentDAO.addForumComment(comment));
	}
	
	@Ignore
	@Test
	public void deleteCommentTest() {
		ForumComment comment=forumCommentDAO.getForumComment(954);
		
		assertTrue("Problem Occured while deleting a Comment: ", forumCommentDAO.deleteForumComment(comment));
	}
	
	@Ignore
	@Test
	public void listForumCommentsTest() {
		List<ForumComment> commentList=forumCommentDAO.listForumComments(953);
		
		assertTrue("Problem in Retrieving Comments in a Forum: ", commentList.size()>0);
		
		for(ForumComment comment:commentList) {
			System.out.println(comment.getForumCommentText());
			System.out.println(comment.getForumCommentDate());
		}
	}
}
