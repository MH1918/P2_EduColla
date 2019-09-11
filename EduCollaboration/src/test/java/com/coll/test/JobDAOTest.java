package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.JobDAO;
import com.coll.model.Job;

public class JobDAOTest {

	static JobDAO jobDAO;

	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		jobDAO=(JobDAO)context.getBean("jobDAO");
	}
	
	@Ignore
	@Test
	public void publishJobTest() {
		Job job=new Job();
		job.setDesignation("Looking for a developer");
		job.setJobDesc("the candidate should be able to work independently");
		job.setCompanyName("Space Consultants");
		job.setSkills("jquery,  mysql,  html,  javascript,  java,  project estimation,  communication skills,  j2ee,  oops,  ideal,  design,  spring");
		job.setCtc(6);
		job.setExperienceYear(3);
		job.setLastDateToApply(new java.util.Date());
		
		assertTrue("Problem Occured during Adding a Blog: ", jobDAO.publishJob(job));
	}
	
	@Ignore
	@Test
	public void deleteJobTest() {
		Job job=jobDAO.getJob(952);
		
		assertTrue("Problem Occured while deleting a Job: ", jobDAO.deleteJob(job));
	}
	
	@Ignore
	@Test
	public void updateJobTest() {
		Job job=jobDAO.getJob(953);
		job.setSkills("jquery,  mysql,  html,  javascript,  java");
		
		assertTrue("Problem Occured while Updating the Job: ", jobDAO.updateJob(job));
	}
	
	@Ignore
	@Test
	public void showListJobsTest() {
		List<Job> jobList=jobDAO.showListJobs();
		
		assertTrue("Problem in Retrieving All Jobs: ", jobList.size()>0);
		
		for(Job job:jobList) {
			System.out.println("Job Designation: "+job.getDesignation());
			System.out.println("Job Description: "+job.getJobDesc());
			System.out.println("Name of the Company: "+job.getCompanyName());
			System.out.println("Required Skills: "+job.getSkills());
			System.out.println("Salary P.A.: "+job.getCtc());
			System.out.println("Experience: "+job.getExperienceYear());
			System.out.println("Last Date: "+job.getLastDateToApply());
		}
	}
}
