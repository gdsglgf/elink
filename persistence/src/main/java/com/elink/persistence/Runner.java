package com.elink.persistence;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-servlet.xml");
		Task task = (Task) ctx.getBean("task");
		task.doTask();
		ctx.close();
		ctx.registerShutdownHook();
	}
}
