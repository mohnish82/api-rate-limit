package com.desasha.apiratelimit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastConfig {

    @Bean
    HazelcastInstance hazelcastInstance() {

    	CacheSimpleConfig cfg = new CacheSimpleConfig()
    								.setName("app-bucket");
    	
        Config config = new Config()
        						.setLiteMember(false)
        						.addCacheConfig(cfg);

        return Hazelcast.newHazelcastInstance(config);
    }	
	
}