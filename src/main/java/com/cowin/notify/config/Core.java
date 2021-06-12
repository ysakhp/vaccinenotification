package com.cowin.notify.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Core {
	public static Logger log = LoggerFactory.getLogger(Core.class);

	@Bean(name = "Logger")
	public Logger getLogger() {
		return log;
	}

}
