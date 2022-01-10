package com.nagarro.javafreshertraining.helper;

public class Message {
	private String content;
	private String type;
	
	/* Constructor */
	public Message(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}

	
	/* Getters & Setters */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
