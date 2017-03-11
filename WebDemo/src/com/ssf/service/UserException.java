package com.ssf.service;

public class UserException extends Exception{
	
	private String error;
	public UserException(String error){
		this.error =error;
		
	}

}
