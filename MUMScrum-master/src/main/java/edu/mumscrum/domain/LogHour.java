package edu.mumscrum.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LogHour {
	
	@Id
	@GeneratedValue
	private long id;
	
	private int hoursDone;
	
	private LocalDate timeStamp;
	
	public LogHour(){
		
	}
	
	public LogHour(Date timeStamp, int hoursDone) {
		super();
		this.hoursDone = hoursDone;
		this.timeStamp = timeStamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getHoursDone() {
		return hoursDone;
	}

	public void setHoursDone(int hoursDone) {
		this.hoursDone = hoursDone;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
