package com.exam.ex;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("ma")
public class MyApp {
	
	@Autowired
//	@Named("msk")
	private MyService myService;
	
	public MyService getMyService() {
		return myService;
	}

	public void setMyService(MyService myService) {
		this.myService = myService;
	}

	public void say() {
		System.out.println( myService.getMessage() );
	}

}
