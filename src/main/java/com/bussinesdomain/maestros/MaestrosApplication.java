package com.bussinesdomain.maestros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MaestrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaestrosApplication.class, args);
	}

}
