package com.coll.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.JobDAO;
import com.coll.model.Job;

@RestController
public class JobRestController {
	
	@Autowired
	JobDAO jobDAO;
	
	@PostMapping(value="/publishJob", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> publishJob(@RequestBody Job job) {
		
		if(jobDAO.publishJob(job)) {
			return new ResponseEntity<String>("Nwe Job Added", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/showAllJobs")
	public ResponseEntity<List<Job>> getAllJobs() {
		
		List<Job> jobList=jobDAO.showListJobs();
		
		if(jobList.size()>0) {
			return new ResponseEntity<List<Job>>(jobList, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<List<Job>>(jobList, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/getJob/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable("jobId")int jobId) {
		
		Job job=jobDAO.getJob(jobId);
		
		if(job!=null)
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		else
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/updateJob", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateJob(@RequestBody Job job) {
		
		Job tempJob=jobDAO.getJob(job.getJobId());
		
		tempJob.setDesignation(job.getDesignation());
		tempJob.setJobDesc(job.getJobDesc());
		tempJob.setCompanyName(job.getCompanyName());
		tempJob.setSkills(job.getSkills());
		tempJob.setCtc(job.getCtc());
		tempJob.setExperienceYear(job.getExperienceYear());
		tempJob.setLastDateToApply(job.getLastDateToApply());

		if(jobDAO.updateJob(tempJob)) {
			return new ResponseEntity<String>("Job Updated", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteJob/{jobId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteJob(@PathVariable("jobId")int jobId) {
		
		Job tempJob=jobDAO.getJob(jobId);
		
		if(jobDAO.deleteJob(tempJob)) {
			return new ResponseEntity<String>("Job Deleted", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<String>("Error Occured", HttpStatus.NOT_FOUND);
		}
	}
}
