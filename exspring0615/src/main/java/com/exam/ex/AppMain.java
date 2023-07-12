package com.exam.ex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
	
	public static void main(String[] args) {
		
////	MyServiceKo msk = new MyServiceKo();
////	System.out.println(msk.getMessage());
//		MyServiceEn mse = new MyServiceEn();
//		
//		MyApp app = new MyApp();
////	app.setMyService(msk);
//		app.setMyService(mse);
//		app.say();
		
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/exam/ex/context.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
		
//		MyApp app = (MyApp) ctx.getBean("ma");
		MyApp app = ctx.getBean("ma", MyApp.class);
		
		app.say();
		
		
	}

}
