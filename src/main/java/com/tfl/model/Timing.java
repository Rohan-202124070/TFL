package com.tfl.model;

import java.util.Date;

public class Timing {
	public String type;
    public String countdownServerAdjustment;
    public Date source;
    public Date insert;
    public Date read;
    public Date sent;
    public Date received;
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCountdownServerAdjustment() {
		return countdownServerAdjustment;
	}
	public void setCountdownServerAdjustment(String countdownServerAdjustment) {
		this.countdownServerAdjustment = countdownServerAdjustment;
	}
	public Date getSource() {
		return source;
	}
	public void setSource(Date source) {
		this.source = source;
	}
	public Date getInsert() {
		return insert;
	}
	public void setInsert(Date insert) {
		this.insert = insert;
	}
	public Date getRead() {
		return read;
	}
	public void setRead(Date read) {
		this.read = read;
	}
	public Date getSent() {
		return sent;
	}
	public void setSent(Date sent) {
		this.sent = sent;
	}
	public Date getReceived() {
		return received;
	}
	public void setReceived(Date received) {
		this.received = received;
	}
}
