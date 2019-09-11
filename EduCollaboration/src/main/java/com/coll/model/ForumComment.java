package com.coll.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@SequenceGenerator(name="forumCommIdSeq", sequenceName="myForumCommIdSeq")
public class ForumComment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="forumCommIdSeq")
	private int forumCommentId;
	
	private int forumId;
	private String forumCommentText;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date forumCommentDate;
	
	private String username;
	
	
	public int getForumCommentId() {
		return forumCommentId;
	}
	public void setForumCommentId(int forumCommentId) {
		this.forumCommentId = forumCommentId;
	}

	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getForumCommentText() {
		return forumCommentText;
	}
	public void setForumCommentText(String forumCommentText) {
		this.forumCommentText = forumCommentText;
	}

	public Date getForumCommentDate() {
		return forumCommentDate;
	}
	public void setForumCommentDate(Date forumCommentDate) {
		this.forumCommentDate = forumCommentDate;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
