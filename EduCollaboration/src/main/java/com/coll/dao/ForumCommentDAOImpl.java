package com.coll.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.ForumComment;

@Repository("forumCommentDAO")
@Transactional
public class ForumCommentDAOImpl implements ForumCommentDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addForumComment(ForumComment comment) {
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		}
		catch(Exception e) {
			System.out.println("Exception: "+e);
			return false;
		}
	}

	public boolean deleteForumComment(ForumComment comment) {
		try {
			sessionFactory.getCurrentSession().delete(comment);
			return true;
		}
		catch(Exception e) {
			System.out.println("Exception: "+e);
			return false;
		}
	}

	public List<ForumComment> listForumComments(int forumId) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from ForumComment where forumId=:forumid");
		query.setParameter("forumid", forumId);
		List<ForumComment> commentList=query.list();
		session.close();
		return commentList;
	}

	public ForumComment getForumComment(int forumCommentId) {
		Session session=sessionFactory.openSession();
		ForumComment comment=session.get(ForumComment.class, forumCommentId);
		session.close();
		return comment;
	}
}
