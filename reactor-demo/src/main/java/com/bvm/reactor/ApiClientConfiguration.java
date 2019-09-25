package com.bvm.reactor;

import lombok.Builder;
import lombok.Cleanup;
import lombok.Data;

//@Data
@Builder
public class ApiClientConfiguration {

	private String host;
	private int port;
	private boolean useHttps;

	private long connectTimeout;
	private long readTimeout;

	private String username;
	private String password;

	// Whatever other options you may thing.

	// Empty constructor? All combinations?

	// getters... and setters?
	
	public void test() {
		//ApiClientConfiguration config = ApiClientConfiguration.builder();
	}
	
}