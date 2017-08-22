package  com.realpage.otis.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

	private Integer mpfUserId;
	private String username;
	private String email;
	private String lastname;
	private String firstname;
	private Boolean active;
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getMpfUserId() {
		return mpfUserId;
	}

	public void setMpfUserId(Integer mpfUserId) {
		this.mpfUserId = mpfUserId;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "User [mpfUserId=" + mpfUserId + ", username=" + username + ", email=" + email + ", lastname=" + lastname
				+ ", firstname=" + firstname + ", active=" + active + ", roleName=" + roleName + "]";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = 	AuthorityUtils.createAuthorityList(getRoleName());
		System.out.println(list);
		return list;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return active;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
