package com.dimexer.spitter.model;

import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Spitter implements UserDetails{
	private static final long serialVersionUID = 1L;

	private int id;

	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long!")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be aphanumeric with no spaces!")
	private String username;

	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters long!")
	private String password;

	@Size(min = 3, max = 50, message = "Your full name must be between 3 and 50 characters long!")
	private String fullName;

	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Invalid email address")
	private String email;

	private Date lastLogin;
	
	public Spitter(int id, String uname, String pass, String fName, String email, Date lastLogin) {
		//super(uname, pass, true, true, true, true, new ArrayList());
		this.id=id;
		this.fullName = fName;
		this.username=uname;
		this.password=pass;
		this.email=email;
		this.lastLogin=lastLogin;
	}
	
	public Spitter(){
		//super(null, null, new ArrayList());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}
