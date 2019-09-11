package com.coll.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.Friend;
import com.coll.model.UserDetail;

@Repository("friendDAO")
@Transactional
public class FriendDAOImpl implements FriendDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Friend> showFriendList(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where (username=:uname or friendUsername=:funame) and status='A'");
		query.setParameter("uname", username);
		query.setParameter("funame", username);
		List<Friend> friendList=query.list();
		session.close();
		return friendList;
	}

	public List<Friend> showPendingFriendRequest(String username) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where (friendUsername=:funame and status='P')");
		query.setParameter("funame", username);
		List<Friend> pendingFriendList=query.list();
		session.close();
		return pendingFriendList;
	}

	public List<UserDetail> showSuggestedFriend(String username) {
		Session session=sessionFactory.openSession();
		Query sqlQuery=session.createSQLQuery("select username from UserDetail where username not in(select friendUsername from Friend where username='"+username+"') and username not in(select username from Friend where friendUsername='"+username+"' and status='A') and username!='"+username+"'");
		
		List<String> userList=(List<String>)sqlQuery.list();
		
		ArrayList<UserDetail> userListDetail=new ArrayList<UserDetail>();
		
		int count=0;
		while(count<userList.size()) {
			UserDetail userDetail=session.get(UserDetail.class, userList.get(count).toString());
			
			userListDetail.add(userDetail);
			
			count++;
		}
		session.close();
		return userListDetail;
	}

	public boolean sendFriendRequest(Friend friend) {
		try {
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);
			return true;
		}
		catch(Exception e) {
			System.out.println("Exception: "+e);
			return false;
		}
	}

	public boolean acceptFriendRequest(int friendId) {
		try {
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendId);
			session.close();
			
			friend.setStatus("A");
			sessionFactory.getCurrentSession().update(friend);
			return true;
		}
		catch(Exception e) {
			System.out.println("Exception: "+e);
			return false;
		}
	}

	public boolean deleteFriendRequest(int friendId) {
		try {
			Session session=sessionFactory.openSession();
			Friend friend=session.get(Friend.class, friendId);
			session.close();
			
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		}
		catch(Exception e) {
			System.out.println("Exception: "+e);
			return false;
		}
	}

}
