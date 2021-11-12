package com.safety;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class HttpTraceRepository {
	@Configuration
	// @Profile("actuator-endpoints") /* if you want: register bean only if profile is set */
	public class HttpTraceActuatorConfiguration {

	    @Bean
	    public InMemoryHttpTraceRepository httpTraceRepository() {
	        return new InMemoryHttpTraceRepository();
	    }

	}

}
