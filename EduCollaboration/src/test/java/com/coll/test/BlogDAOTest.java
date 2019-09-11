package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogDAO;
import com.coll.model.Blog;

public class BlogDAOTest {
	
	static BlogDAO blogDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		blogDAO=(BlogDAO)context.getBean("blogDAO");
	}
	
	@Ignore
	@Test
	public void addBlogTest() {
		Blog blog=new Blog();
		blog.setBlogName("Ruby");
		blog.setBlogContent("This is a simlpe Blog for learning Ruby");
		blog.setUsername("abhi");
		blog.setCreateDate(new java.util.Date());
		blog.setStatus("A");
		blog.setLikes(0);
		blog.setDislikes(0);
		
		assertTrue("Problem Occured during Adding a Blog: ", blogDAO.addBlog(blog));
	}
	
	@Ignore
	@Test
	public void deleteBlogTest() {
		Blog blog=blogDAO.getBlog(952);
		
		assertTrue("Problem Occured while deleting Blog: ", blogDAO.deleteBlog(blog));
	}
	
	@Ignore
	@Test
	public void updateBlogTest() {
		Blog blog=blogDAO.getBlog(953);
		blog.setBlogName("Core Java");
		blog.setBlogContent("This is a simlpe Blog for learning Core Java");
		
		assertTrue("Problem Occured while deleting: ", blogDAO.updateBlog(blog));
	}
	
	@Ignore
	@Test
	public void listBlogsTest() {
		List<Blog> blogList=blogDAO.listBlogs();
		
		assertTrue("Problem in Retrieving the Blog: ", blogList.size()>0);
		
		for(Blog blog:blogList) {
			System.out.print(blog.getBlogId()+"::");
			System.out.print(blog.getBlogName()+"::");
			System.out.print(blog.getLikes()+"::");
			System.out.println(blog.getStatus());
		}
	}
	
	@Ignore
	@Test
	public void incrementLikesTest() {
		assertTrue("Problem in Increment Likes: ", blogDAO.incrementLikes(953));
	}
	
	@Ignore
	@Test
	public void incrementDislikesTest() {
		assertTrue("Problem in Increment Dislikes: ", blogDAO.incrementDislikes(954));
	}
	
	@Ignore
	@Test
	public void approvedBlogTest() {
		Blog blog=blogDAO.getBlog(953);
		
		assertTrue("Problem in Approving the Blog: ", blogDAO.approvedBlog(blog));
	}
	
	@Ignore
	@Test
	public void rejectBlogTest() {
		Blog blog=blogDAO.getBlog(954);
		
		assertTrue("Problem in Rejecting the Blog: ", blogDAO.rejectBlog(blog));
	}
}
