package org.acme.quarkus.sample;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.testcontainers.containers.MySQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class DataBaseLifeCycle implements QuarkusTestResourceLifecycleManager {
	
	private static MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql/mysql-server:8.0.21");

	@Override
	public Map<String, String> start() {
		MYSQL.start();
		Map<String, String> properties = new HashedMap();
		properties.put("quarkus.datasource.url", MYSQL.getJdbcUrl());
		properties.put("quarkus.datasource.username", MYSQL.getUsername());
		properties.put("quarkus.datasource.password", MYSQL.getPassword());
		
		return properties;
	}

	@Override
	public void stop() {
		if(MYSQL !=null) {
			MYSQL.stop();
		}
		
	}
}
