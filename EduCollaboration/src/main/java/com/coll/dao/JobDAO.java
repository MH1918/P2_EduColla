package com.coll.dao;

import java.util.List;

import com.coll.model.Job;

public interface JobDAO {
	
	public boolean publishJob(Job job);
	
	public boolean deleteJob(Job job);
	
	public boolean updateJob(Job job);
	
	public List<Job> showListJobs();
	
	public Job getJob(int jobId);
}
