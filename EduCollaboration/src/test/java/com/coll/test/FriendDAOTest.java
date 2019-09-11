package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.FriendDAO;
import com.coll.model.Friend;
import com.coll.model.UserDetail;

public class FriendDAOTest {

	static FriendDAO friendDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		friendDAO=(FriendDAO)context.getBean("friendDAO");
	}
	
	@Ignore
	@Test
	public void sendFriendRequestTest() {
		Friend friend=new Friend();
		friend.setUsername("abhi");
		friend.setFriendUsername("subha");
		
		assertTrue("Problem while Sending Friend Request: ", friendDAO.sendFriendRequest(friend)); 
	}
	
	@Ignore
	@Test
	public void acceptFriendRequestTest() {
		assertTrue("Problem while Accepting a Friend Request: ", friendDAO.acceptFriendRequest(972));
	}
	
	@Ignore
	@Test
	public void deleteFriendRequestTest() {
		assertTrue("Problem while Deleting a Friend Request: ", friendDAO.deleteFriendRequest(953));
	}
	
	@Ignore
	@Test
	public void showFriendListTest() {
		List<Friend> friendList=friendDAO.showFriendList("abhi");
		
		assertTrue("Error while Retriving the Friend List: ", friendList.size()>0);
		
		for(Friend friend:friendList) {
			System.out.print(friend.getUsername()+":::");
			System.out.println(friend.getFriendUsername());
		}
	}
	
	@Ignore
	@Test
	public void showPendingFriendListTest() {
		List<Friend> pendingfriendList=friendDAO.showPendingFriendRequest("subha");
		
		assertTrue("Error while Retriving the Pending Friend List: ", pendingfriendList.size()>0);
		
		for(Friend friend:pendingfriendList) {
			System.out.print(friend.getUsername()+":::");
			System.out.println(friend.getFriendUsername());
		}
	}
	
	@Ignore
	@Test
	public void showSuggestedFriendListTest() {
		List<UserDetail> showSuggestedFriendList=friendDAO.showSuggestedFriend("abhi");
		
		assertTrue("Error while Retriving the Suggestion List: ", showSuggestedFriendList.size()>0);
		
		for(UserDetail userDetail:showSuggestedFriendList) {
			System.out.print(userDetail.getUsername()+":::");
			System.out.println(userDetail.getName());
		}
	}
}
