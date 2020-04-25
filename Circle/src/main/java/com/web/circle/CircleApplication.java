package com.web.circle;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * https://localhost:8443/
 * For super account
 * Username: super	
 * Password: 1
 * Hashed: $2a$10$7XfvhikJ3zxXsLNWX2JL..aWwP8qkuW/ok/NhByyVm7RGVHzNpVTe
 * -------------------------
 * For admin account
 * Username: admin	
 * Password: 1
 * Hashed: $2a$10$7XfvhikJ3zxXsLNWX2JL..aWwP8qkuW/ok/NhByyVm7RGVHzNpVTe
 * -------------------------
 * For User account
 * Username: user
 * Password: 1
 * Hashed: $2a$10$7XfvhikJ3zxXsLNWX2JL..aWwP8qkuW/ok/NhByyVm7RGVHzNpVTe
 * -------------------------
 * 
 * @author jr
 * */
@SpringBootApplication
@EnableJpaAuditing // Enabling JPA Auditing
public class CircleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircleApplication.class, args);
	}

	@Bean
    public ServletWebServerFactory servletContainer() {
        // Enable SSL Trafic
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        // Add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }


    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
}

/**
 * List of Todo..
 * 1. User sign up fill all the information needed for registration.
 * 2.0 https://www.javadevjournal.com/spring/spring-file-upload/
 * 2.1. https://mkyong.com/webservices/jax-rs/file-upload-example-in-jersey/ Upload file
 * 2.2. https://www.boraji.com/spring-4-mvc-jquery-ajax-file-upload-example-with-progress-bar
 * 2.3. https://www.boraji.com/spring-4-mvc-jquery-ajax-file-upload-example-with-progress-bar
 * 3. Create a custom error page.
 * 4. https://netsurfingzone.com/spring/spring-transaction-management-example-using-spring-boot/
 * 5. https://memorynotfound.com/spring-mail-sending-email-freemarker-html-template-example/ Send email
 * 6. https://memorynotfound.com/spring-security-forgot-password-send-email-reset-password/ // forgot password
 * 
 * 
 * */
