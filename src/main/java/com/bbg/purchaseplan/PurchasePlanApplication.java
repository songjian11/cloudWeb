package com.bbg.purchaseplan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = {"com.bbg.**.dao"})
public class PurchasePlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchasePlanApplication.class, args);
		System.out.println("====================采购计划启动成功===========================");
	}
}
