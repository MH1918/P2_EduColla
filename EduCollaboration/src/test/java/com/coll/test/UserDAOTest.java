package com.coll.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.UserDAO;
import com.coll.model.Blog;
import com.coll.model.UserDetail;

public class UserDAOTest {

	static UserDAO userDAO;
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		userDAO=(UserDAO)context.getBean("userDAO");
	}
	
	@Ignore
	@Test
	public void registerUserTest() {
		UserDetail user=new UserDetail();
		user.setUsername("subha");
		user.setName("Subhajit Naskar");
		user.setPassword("pass1234");
		user.setEmailId("subhajit@gmail.com");
		user.setRole("ROLE_USER");
		user.setStatus("A");
		user.setIsOnline("Y");
		
		assertTrue("Problem Occured during Adding a User: ", userDAO.registerUser(user));
	}
	
	@Ignore
	@Test
	public void updateUserTeat() {
		UserDetail user=userDAO.getUser("sudipta");
		user.setPassword("Pass1234");
		user.setRole("ROLE_USER");
		
		assertTrue("Problem in Updating the UserDetail: ", userDAO.updateProfile(user));
	}
	
	@Ignore
	@Test
	public void checkUserTest() {
		UserDetail user=userDAO.checkUser("sudipta", "pass1234");
		
		assertNotNull("Problem in checking User! ", user);
		
		System.out.println("Name: "+user.getName());
		System.out.println("Email: "+user.getEmailId());
		System.out.println("Role: "+user.getRole());
	}
	
	@Ignore
	@Test
	public void listUsersTest() {
		List<UserDetail> userList=userDAO.listUsers();
		
		assertTrue("Problem in Retrieving the UserList: ", userList.size()>0);
		
		for(UserDetail user:userList) {
			System.out.print(user.getUsername()+"::");
			System.out.print(user.getName()+"::");
			System.out.print(user.getEmailId()+"::");
			System.out.println(user.getPassword());
		}
	}
}
