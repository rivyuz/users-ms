package com.riviewz.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("users-ms")
public class ReadProperties {
	
	public String server;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

}
