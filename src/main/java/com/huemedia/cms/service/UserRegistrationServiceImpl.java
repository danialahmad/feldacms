package com.huemedia.cms.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.model.repository.UserRepository;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	EmailService emailService;
	@Value("#{globalProperties['contextPath']}")
	String contextPath;
	@Override
	public String encryptPassword(String password) {
		PasswordEncoder encoder = new ShaPasswordEncoder(256);
		String pass=encoder.encodePassword(password, null);
		return pass;
	}

	@Override
	public String generateActivationID() {
		String e =UUID.randomUUID().toString();
		return e;
	}

	@Override
	public void sendActivationID(User user) {
		String url=contextPath+"registration/activate/"+user.getUsername()+"/"+user.getActivationId()+"/";
		
		emailService.sendEmailForActivation(user, url);
	}

	@Override
	public boolean isUserExist(String username,String icNo) {
		boolean exist=false;
		User p=userRepository.findByUsernameOrIcNo(username, icNo);
		
		if(p!=null){
			exist=true;
		}else{
			exist=false;
		}
		
		return exist;
	}

	@Override
	@Transactional
	public void activateUser(String username, String id) {
		userRepository.updateStatus("A", username, id);
	}

}
