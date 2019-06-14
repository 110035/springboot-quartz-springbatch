package com.xiaomi.sunjianfei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableBatchProcessing
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args){
		
//		SpringApplication app = new SpringApplication(DemoApplication.class);
//		Set<Object> set = new HashSet<>();
//		set.add("classpath:job.xml");
//		app.setSources(set);
//		ApplicationContext ctx = app.run(args);
		
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("job.xml");
//		
//		SimpleJobLauncher launcher = (SimpleJobLauncher) ctx.getBean("laucher");
//		Job job = (Job) ctx.getBean("jobExample");
//		System.out.println(launcher);
//		System.out.println(job);
//		launcher.run(job, new JobParameters());
//		ctx.close();
		
		SpringApplication.run(DemoApplication.class, args);
	}
}
