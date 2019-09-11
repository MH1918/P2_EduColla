package com.coll.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.BlogComment;

@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addComment(BlogComment comment) {
		try {
			sessionFactory.getCurrentSession().save(comment);
			return true;
		}
		catch(Exception e) {
			System.out.println("Exception: "+e);
			return false;
		}
	}

	public boolean deleteComment(BlogComment comment) {
		try {
			sessionFactory.getCurrentSession().delete(comment);
			return true;
		}
		catch(Exception e) {
			System.out.println("Exception: "+e);
			return false;
		}
	}

	public List<BlogComment> listBlogComments(int blogId) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogComment where blogId=:blogid");
		query.setParameter("blogid", blogId);
		List<BlogComment> commentList=query.list();
		session.close();
		return commentList;
	}

	public BlogComment getBlogComment(int blogCommentId) {
		Session session=sessionFactory.openSession();
		BlogComment comment=session.get(BlogComment.class, blogCommentId);
		session.close();
		return comment;
	}
}
