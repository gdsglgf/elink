package com.elink.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCallOrder {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
		PersonService person = (PersonService) ctx.getBean("personService");
		person.setMessage("hello spring");
		PersonService person_new = (PersonService) ctx.getBean("personService");

		System.out.println(person.getMessage());
		System.out.println(person_new.getMessage());
		ctx.close();
		ctx.registerShutdownHook();
	}
}
