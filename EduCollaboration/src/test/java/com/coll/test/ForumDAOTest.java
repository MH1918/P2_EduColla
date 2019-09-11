package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.ForumDAO;
import com.coll.model.Forum;

public class ForumDAOTest {

	static ForumDAO forumDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		forumDAO=(ForumDAO)context.getBean("forumDAO");
	}
	
	@Ignore
	@Test
	public void addForumTest() {
		Forum forum=new Forum();
		forum.setForumName("About Ruby");
		forum.setForumContent("Discussion about Ruby");
		forum.setUsername("abhi");
		forum.setCreateDate(new java.util.Date());
		forum.setStatus("A");
		
		assertTrue("Problem Occured during Adding a Forum: ", forumDAO.addForum(forum));
	}
	
	@Ignore
	@Test
	public void deleteForumTest() {
		Forum forum=forumDAO.getForum(952);
		
		assertTrue("Problem Occured while deleting Froum: ", forumDAO.deleteForum(forum));
	}
	
	@Ignore
	@Test
	public void updateForumTest() {
		Forum forum=forumDAO.getForum(953);
		forum.setForumContent("Discussion about Fundamentals of Ruby");
		
		assertTrue("Problem Occured while Updating Froum: ", forumDAO.updateForum(forum));
	}
	
	@Ignore
	@Test
	public void listForumsTest() {
		List<Forum> forumList=forumDAO.listForums();
		
		assertTrue("Problem in Retrieving the Forum Content: ", forumList.size()>0);
		
		for(Forum forum:forumList) {
			System.out.print(forum.getForumId()+"::");
			System.out.print(forum.getForumName()+"::");
			System.out.println(forum.getStatus());
			System.out.println(forum.getForumContent());
		}
	}
	
	@Ignore
	@Test
	public void approvedForumTest() {
		Forum forum=forumDAO.getForum(953);
		
		assertTrue("Problem in Approving the Forum: ", forumDAO.approvedForum(forum));
	}
	
	@Ignore
	@Test
	public void rejectForumTest() {
		Forum forum=forumDAO.getForum(953);
		
		assertTrue("Problem in Rejecting the Forum: ", forumDAO.rejectForum(forum));
	}
}
