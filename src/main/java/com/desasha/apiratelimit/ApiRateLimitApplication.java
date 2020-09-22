package com.desasha.apiratelimit;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;

@SpringBootApplication
@EnableCaching
public class ApiRateLimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRateLimitApplication.class, args);
	}

	@Bean
	@Qualifier("vehicle-by-vin")
	public Bucket vehiclebyVinTokenBucket(Environment env) {
		
		long rps = env.getProperty("vehicle-by-vin-rps", Long.class, 12L);
		
		return Bucket4j.builder()
				.addLimit(Bandwidth.simple(rps, Duration.ofMinutes(1)).withInitialTokens(1))
				.build();
	}
	
}
