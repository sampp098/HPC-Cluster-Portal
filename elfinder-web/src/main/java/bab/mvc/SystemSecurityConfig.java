package bab.mvc;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



@Configuration
@EnableWebSecurity
public class SystemSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean                           // bean for http session listener
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionListener() {
        	
        	
        	
            @Override
            public void sessionCreated(HttpSessionEvent se) {    
            	// This method will be called when session created
                se.getSession().setMaxInactiveInterval(60*Execute.sessionTimoutMin);
                String ipAddr = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()) .getRequest().toString();
                System.out.println("-------------ipAddr---->:"+ipAddr);
            	System.out.println("#################################Session Created with session id+" + se.getSession().getId());
            }
            @Override
            public void sessionDestroyed(HttpSessionEvent se) {         // This method will be automatically called when session destroyed
                System.out.println("#################################Session Destroyed, Session id:" + se.getSession().getId());
            }
        };
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
	    http.sessionManagement().maximumSessions(1).expiredUrl("/user/login");
	    http.sessionManagement().invalidSessionUrl("/user/login");
	    //http.authorizeRequests().anyRequest().permitAll();
	    
	}
	
	
	
	
}
