package com.ecnu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class Client1Application {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DiscoveryClient client;
	public static void  main(String args[]) {
		SpringApplication.run(Client1Application.class, args);
	}
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String index() {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
		return "Hello World";
	}
}
