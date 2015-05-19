package com.huemedia.cms.security.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.huemedia.cms.component.ImageUtil;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.model.entity.UserRole;
import com.huemedia.cms.model.repository.RoleRepository;
import com.huemedia.cms.service.UserService;

@SuppressWarnings("deprecation")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	@Autowired private UserService userService;
	@Autowired private RoleRepository roleRepository;
	@Value("#{globalProperties['contextPath']}")
	String contextPath;
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, DataAccessException {
	
		final User user = userService.getByUsername(username);
		
		if(user == null){
			throw new BadCredentialsException("BadCredential");
		}

		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		final List<UserRole> list = userService.getUserRoles(username);
	    String auth="";
		for (final UserRole userRole : list) {
			 final String rolename = userRole.getRoleId();
			 auth= roleRepository.findOne(rolename).getName();
			authorities.add(new GrantedAuthorityImpl(rolename));
		}
		Profile p=user.getProfile();
		String img=contextPath+"resources/img/examples/users/no-photo_s.jpg";
		if(p.getPhoto()!=null){
		InputStream myInputStream = new ByteArrayInputStream(p.getPhoto()); 
		img="data:image/jpg;base64,"+Base64.encodeBase64String(ImageUtil.resize(myInputStream, 26, 26));
		}
		
		final String displayName =p.getName();
		boolean enabled=false;
		if(user.getStatus().equals("A")){
			enabled=true;
		}
		
		final UserDetail userDetail = new UserDetail(username, user.getPassword(), authorities, user.getUsername(),p, displayName,img,auth, new Date(), enabled);
		return userDetail;
		
	}

}
