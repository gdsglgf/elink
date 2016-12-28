package com.elink.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/*
<bean id="personService" class="PersonService" 
	scope="singleton" init-method="init" destroy-method="cleanUp" />
*/
@Component
public class PersonService {
	private String message;
	
	public PersonService() {
		System.out.println("in constructor...");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@PostConstruct
	public void init() {
		System.out.println("init");
	}

	@PreDestroy
	public void cleanUp() {
		System.out.println("cleanUp");
	}
}