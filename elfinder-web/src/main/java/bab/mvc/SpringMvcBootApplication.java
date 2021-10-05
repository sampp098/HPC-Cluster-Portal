package bab.mvc;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;

import bab.mvc.data.services.BasicsService;
import bab.mvc.data.services.util.ShellSocket;







@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@EnableConfigurationProperties
@EnableWebSocket


public class SpringMvcBootApplication extends SpringBootServletInitializer implements WebSocketConfigurer{
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcBootApplication.class, args); 
		//keytool -genkeypair -alias tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650
		new BasicsService().Read();
		new PerConnectionWebSocketHandler(ShellSocket.class);
		new StaticData();

	}
	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/shell").withSockJS();
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new PerConnectionWebSocketHandler(ShellSocket.class);
    }

}
