package com.coll.dao;

import java.util.List;

import com.coll.model.ForumComment;

public interface ForumCommentDAO {
	public boolean addForumComment(ForumComment comment);
	public boolean deleteForumComment(ForumComment comment);
	public List<ForumComment> listForumComments(int forumId);
	public ForumComment getForumComment(int forumCommentId);
}
