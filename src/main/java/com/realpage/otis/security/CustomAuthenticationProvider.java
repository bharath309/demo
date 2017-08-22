package  com.realpage.otis.security;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import  com.realpage.otis.model.User;
import  com.realpage.otis.overview.service.IUserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private IUserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken  token = (UsernamePasswordAuthenticationToken)authentication;
		
		Boolean authenticated = false;

        try {
            authenticated = authenticateViaLdap(token.getName(),authentication.getCredentials().toString());
            User user = userService.loadUserByUserName(authentication.getName());
            if(user == null){
            	throw new BadCredentialsException("No User Found in database");
            }
            System.out.println(user);
            token = new UsernamePasswordAuthenticationToken(token.getName(), token.getCredentials(),user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
        } catch (CommunicationException ce) {
//        	 logger.error(ExceptionUtil.getDetailedMessage(ce));
            throw new BadCredentialsException("");
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Either username OR password is invalid");
        } catch (NamingException e) {
//            logger.error(ExceptionUtil.getDetailedMessage(e));
            throw new BadCredentialsException("Problem authenticating against the LDAP server.<br/>" + e.getMessage());
        }
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
	
	
	private static boolean authenticateViaLdap(String username, String password) throws AuthenticationException, CommunicationException,
    NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		boolean authenticationFlag = false;
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "LDAP://" + "corp.realpage.com:389");// MpfServerConstants.LDAP_HOST);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, username + "@corp.realpage.com");
		env.put(Context.SECURITY_CREDENTIALS, password);
		// timeout for connecting to the server
		env.put("com.sun.jndi.ldap.connect.timeout", "1000");
		// The read timeout applies to the LDAP response from the server after
		// the initial connection is established with the server.
		env.put("com.sun.jndi.ldap.read.timeout", "1000");
		
		InitialDirContext initialDirContext = new InitialDirContext(env);
		initialDirContext.close();
		authenticationFlag = true;
		
		return authenticationFlag;
		
		}

}
