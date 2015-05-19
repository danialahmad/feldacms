package com.huemedia.cms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.component.ImageUtil;
import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.model.entity.StaffGroup;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.model.repository.CountryRepository;
import com.huemedia.cms.model.repository.GroupRepository;
import com.huemedia.cms.model.repository.PersonCategoryRepository;
import com.huemedia.cms.model.repository.PlanRepository;
import com.huemedia.cms.model.repository.ProfileRepository;
import com.huemedia.cms.model.repository.RegionRepository;
import com.huemedia.cms.model.repository.StaffGroupRepository;
import com.huemedia.cms.model.repository.StateRepository;
import com.huemedia.cms.model.repository.UserRepository;
import com.huemedia.cms.web.form.Response;
import com.huemedia.cms.web.form.UserForm;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	UserRepository userRepository;
	@Value("#{globalProperties['contextPath']}")
	String contextPath;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	StaffGroupRepository staffGroupRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	PersonCategoryRepository personCategoryRepository;
	@Autowired
	PlanRepository planRepository;

	@Override
	public UserForm findProfileByUsername(String username) {
		User user = userRepository.findOne(username);
		UserForm userForm = new UserForm();
		Profile p = profileRepository.findOne(user.getProfile().getId());
		user.setProfile(p);
		userForm.setPersonCategory(user.getProfile().getPersonCategory());
		userForm.setUsername(user.getUsername());
		userForm.setName(user.getProfile().getName());
		userForm.setEmail(user.getProfile().getEmail());
		userForm.setIcNo(user.getProfile().getIcNo());
		userForm.setSettlerNo(user.getProfile().getSettlerNo());
		userForm.setStaffNo(user.getProfile().getStaffNo());
		userForm.setCompany(user.getProfile().getCompany());
		userForm.setAddress1(user.getProfile().getAddress1());
		userForm.setAddress2(user.getProfile().getAddress2());
		userForm.setCity(user.getProfile().getCity());

		Country country = new Country();
		State state = new State();
		Region r = new Region();
		if (user.getProfile().getCountry() != null) {
			if (user.getProfile().getCountry().getId() != null) {
				country = countryRepository.findOne(user.getProfile()
						.getCountry().getId());

			}

		}
		if (user.getProfile().getState() != null) {
			if (user.getProfile().getState().getId() != null) {
				state = stateRepository.findOne(user.getProfile().getState()
						.getId());

			}

		}
		
		if (user.getProfile().getRegion() != null) {
			if (user.getProfile().getRegion().getId() != null) {
				r = regionRepository.findOne(user.getProfile().getRegion()
						.getId());

			}

		}
		userForm.setCountry(country);
		userForm.setState(state);

		userForm.setPhoneNo(user.getProfile().getPhoneNo());
		userForm.setMobileNo(user.getProfile().getMobileNo());
		userForm.setFaxNo(user.getProfile().getFaxNo());
		userForm.setRegion(user.getProfile().getRegion());
		userForm.setRegionStaff(r);
		userForm.setPlan(user.getProfile().getPlan());
		String img = contextPath + "resources/img/examples/users/no-photo.jpg";
		if (user.getProfile().getPhoto() != null) {
			img = "data:image/jpg;base64,"
					+ Base64.encodeBase64String(user.getProfile().getPhoto());
		}
		userForm.setPhoto(img);

		JSONArray arr = new JSONArray();
		List<StaffGroup> lsg = staffGroupRepository.findByProfile(user
				.getProfile());
		if (lsg.size() != 0) {
			String[] ids = new String[lsg.size()];
			int i = 0;
			for (StaffGroup sg : lsg) {
				Group g = groupRepository.findOne(sg.getGroup().getId());
				arr.put(g.getName());
				ids[i] = sg.getGroup().getId();
				i++;
			}
			userForm.setGroupIds(ids);
			try {
				userForm.setGroups(arr.getString(0));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return userForm;
	}

	@Override
	@Transactional
	public Response updateProfile(UserForm userForm) {
		Response response = new Response();
		User user = userRepository.findOne(userForm.getUsername());
		Profile p=profileRepository.findOne(user.getProfile().getId());
		p.setName(userForm.getName());
		p.setIcNo(userForm.getIcNo());
		p.setSettlerNo(userForm.getSettlerNo());
		p.setStaffNo(userForm.getStaffNo());
		p.setEmail(userForm.getEmail());
		p.setMobileNo(userForm.getMobileNo());
		p.setPhoneNo(userForm.getPhoneNo());
		p.setFaxNo(userForm.getFaxNo());
		
		p.setCompany(userForm.getCompany());
		p.setAddress1(userForm.getAddress1());
		p.setAddress2(userForm.getAddress2());
		p.setCity(userForm.getCity());
		
		if(userForm.getFile()!=null){
			try {
				InputStream is = userForm.getFile().getInputStream();
				p.setPhoto(ImageUtil.resize(is, 100, 100));
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
		}
		
		if(userForm.getCountry()!=null){
			if(userForm.getCountry().getId()!=null){
				Country c= countryRepository.findOne(userForm.getCountry().getId());
				p.setCountry(c);
			}else{
				p.setCountry(null);
			}
		}
		if(userForm.getState()!=null){
			if(userForm.getState().getId()!=null){
				State s= stateRepository.findOne(userForm.getState().getId());
				p.setState(s);
			}else{
				p.setState(null);
			}
		}
		
		if(userForm.getPlan()!=null){
			if(userForm.getPlan().getId()!=null){
				Plan plan= planRepository.findOne(userForm.getPlan().getId());
				p.setPlan(plan);
			}else{
				p.setPlan(null);
			}
		}
		if(userForm.getRegion() !=null){
			if(userForm.getRegion().getId()!=null){
				Region r= regionRepository.findOne(userForm.getRegion().getId());
				p.setRegion(r);
			}else{
				p.setRegion(null);
			}
		}
		
		
		
		profileRepository.save(p);
		return response;
	}

	@Override
	public void delete(String username) {
		userRepository.delete(username);
	}

}
