package com.coll.model;

import java.util.Date;

public class OutputMessage extends Message {
	
	private Date time;
	
	public OutputMessage(Message message, Date time) {
		
		super(message.getId(), message.getMessage());
		this.time=time;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
