package  com.realpage.otis.common;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomVoter extends RoleVoter {
	
	public CustomVoter() {
		setRolePrefix("");
	}
	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		
		if(authentication == null) {
			return ACCESS_DENIED;
		}
		int result = ACCESS_ABSTAIN;
		Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

		for (ConfigAttribute attribute : attributes) {
				result = ACCESS_DENIED;
				// Attempt to find a matching granted authority
				for (GrantedAuthority authority : authorities) {
					String attr = attribute.getAttribute();
					if(attr!=null && attr.startsWith("ROLE_")){
						attr = attr.replace("ROLE_", "");
					}
					if (attr!=null && attr.equals(authority.getAuthority())) {
						return ACCESS_GRANTED;
					}
			}
		}

		return result;
	}
	
	private Collection<? extends GrantedAuthority> extractAuthorities(
			Authentication authentication) {
		return authentication.getAuthorities();
	}
}
