package  com.realpage.otis.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.realpage.otis.filter.CustomUsernamePasswordAuthenticationFilter;
import  com.realpage.otis.security.CustomAuthenticationProvider;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/theme/**").and()
				.debug(true);
	}
	
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
		return customAuthenticationProvider;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		List<AuthenticationProvider> authenticationProviderList = new ArrayList<AuthenticationProvider>();
		authenticationProviderList.add(customAuthenticationProvider());
		AuthenticationManager authenticationManager = new ProviderManager(authenticationProviderList);
		return authenticationManager;
	}
	
	
	@Bean
	public CustomUsernamePasswordAuthenticationFilter getFilter() throws Exception{
		
		CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setFilterProcessesUrl("/callcenter/login");
		filter.setUsernameParameter("username");
		filter.setPasswordParameter("password");
		SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		authenticationSuccessHandler.setDefaultTargetUrl("/callcenter/getAllCalls");
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/callcenter/loginPage?error=true"));
		return filter;
		
	}
	
	@Bean
	public CustomVoter getRoleVoter(){
		CustomVoter voter = new CustomVoter();
		voter.setRolePrefix("");
		
		return voter;
	}
	
	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
	    return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
	}
	
	@Bean
	public AccessDecisionManager getAccessMangers(){
		AccessDecisionManager manager = new AffirmativeBased(Arrays.asList(getRoleVoter(),new WebExpressionVoter()));
		return manager;
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.addFilterBefore(getFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()   
			.antMatchers("/callcenter/getAllCalls").access("hasAuthority('QA')")
			.antMatchers("/callcenter/loginPage").permitAll()
			.anyRequest().authenticated().accessDecisionManager(getAccessMangers())
		.and().formLogin().loginPage("/callcenter/loginPage")
		.and().logout();
		
		http.csrf().disable();
	}
	
}
