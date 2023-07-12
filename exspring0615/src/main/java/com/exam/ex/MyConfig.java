package com.exam.ex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.exam.ex")
public class MyConfig {
	
//	@Bean("ma")
	public MyApp ma() {
		
		MyApp app = new MyApp();
		app.setMyService( msk() );
		return app;
	}
	
//	@Bean("msk")
	public MyService msk() {
		return new MyServiceKo();
	}
	
//	@Bean("mse")
	public MyService mse() {
		return new MyServiceEn();
	}

	
}
