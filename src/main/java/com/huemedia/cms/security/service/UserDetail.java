package com.huemedia.cms.security.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.huemedia.cms.model.entity.Profile;

public class UserDetail extends User {
	
	private String id;
	
	private String displayName;
	
	private Date lastLogin;
	
	private Profile profile;
	
	private String displayPicture;
	
	private String displayAuth;

	public UserDetail(final String username, final String password,
			final Collection<? extends GrantedAuthority> authorities, final String id,final Profile profile,
			final String displayName, final Date lastLogin, final Boolean active) {
		super(username, password, active, true, true, true, authorities);
		this.id = id;
		this.profile=profile;
		this.displayName = displayName;
		this.lastLogin = lastLogin;
	}
	
	public UserDetail(final String username, final String password,
			final Collection<? extends GrantedAuthority> authorities, final String id,final Profile profile,
			final String displayName,String displayPicture,final String displayAuth, final Date lastLogin, final Boolean active) {
		super(username, password, active, true, true, true, authorities);
		this.id = id;
		this.profile=profile;
		this.displayName = displayName;
		this.lastLogin = lastLogin;
		this.displayPicture=displayPicture;
		this.displayAuth=displayAuth;
	}
	
	public UserDetail(final String username, final String password,
			final Collection<? extends GrantedAuthority> authorities, final String id,final Profile profile,
			final String displayName,String displayPicture, final Date lastLogin, final Boolean active) {
		super(username, password, active, true, true, true, authorities);
		this.id = id;
		this.profile=profile;
		this.displayName = displayName;
		this.lastLogin = lastLogin;
		this.displayPicture=displayPicture;
	}

	public UserDetail(final String username, final String password, final boolean enabled,
			final boolean accountNonExpired, final boolean credentialsNonExpired,
			final boolean accountNonLocked,
			final Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(final Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getDisplayPicture() {
		return displayPicture;
	}
	public void setDisplayPicture(String displayPicture) {
		this.displayPicture = displayPicture;
	}
	public String getDisplayAuth() {
		return displayAuth;
	}
	public void setDisplayAuth(String displayAuth) {
		this.displayAuth = displayAuth;
	}

}
