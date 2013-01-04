package com.demo.jsf.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.demo.jsf.adapter.DateAdapter;
import com.demo.jsf.adapter.TimestampAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "requestDto")
public class RequestDto {
	
	@XmlElement
	private String email;
	
	@XmlElement(name = "date", required = true) 
    @XmlJavaTypeAdapter(DateAdapter.class)
	private Date date;
	
	@XmlElement
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp timestamp;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
