package com.huemedia.cms.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.component.ImageUtil;
import com.huemedia.cms.component.RandomGenerator;
import com.huemedia.cms.model.dao.TicketGroupDAO;
import com.huemedia.cms.model.dao.UserDAO;
import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.PersonCategory;
import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.model.entity.Relation;
import com.huemedia.cms.model.entity.Role;
import com.huemedia.cms.model.entity.StaffGroup;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.model.entity.User;
import com.huemedia.cms.model.entity.UserRole;
import com.huemedia.cms.model.repository.CountryRepository;
import com.huemedia.cms.model.repository.GroupRepository;
import com.huemedia.cms.model.repository.PersonCategoryRepository;
import com.huemedia.cms.model.repository.PlanRepository;
import com.huemedia.cms.model.repository.ProfileRepository;
import com.huemedia.cms.model.repository.RegionRepository;
import com.huemedia.cms.model.repository.RelationRepository;
import com.huemedia.cms.model.repository.RoleRepository;
import com.huemedia.cms.model.repository.StaffGroupRepository;
import com.huemedia.cms.model.repository.StateRepository;
import com.huemedia.cms.model.repository.UserRepository;
import com.huemedia.cms.model.repository.UserRoleRepository;
import com.huemedia.cms.web.controller.administration.datatables.UserResult;
import com.huemedia.cms.web.form.Response;
import com.huemedia.cms.web.form.UserForm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Value("#{globalProperties['contextPath']}")
	String contextPath;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	EmailService emailService;
	@Autowired
	UserDAO userDAO;
	@Autowired
	TicketGroupDAO ticketGroupDAO;
	@Autowired
	StaffGroupRepository staffGroupRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	RelationRepository relationRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	UserRegistrationService userRegistrationService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	PersonCategoryRepository personCategoryRepository;
	@Autowired
	PlanRepository planRepository;
	
	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
	//	User user=userRepository.findProfileByUsername(username);
		User user = userDAO.findByUsername(username);
		//System.out.println("USEr Nak Login :-"+user.getUsername());
		return user;
	}

	@Override
	public List<UserRole> getUserRoles(String username) {
		return userRoleRepository.findByUser(userRepository.findOne(username));
	}

	@Override
	public List<StaffGroup> getStaffGroups(Profile p) {
		// TODO Auto-generated method stub
		return staffGroupRepository.findByProfile(p);
	}

	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return userDAO.countAll();
	}

	@Override
	public Long countSearch(UserForm form) {
		// TODO Auto-generated method stub
		return userDAO.countSearch(form);
	}

	@Override
	public List<UserResult> getUsers(UserForm form, Integer iDisplayStart,
			Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<User> listUsers = userDAO.search(form, iDisplayStart, iDisplayLength);
		List<UserResult> list = new ArrayList<UserResult>();
		for(User user:listUsers){
			UserResult r=new UserResult();
			r.setUsername(user.getUsername());
			r.setName(user.getProfile().getName());
		    r.setEmail(user.getProfile().getEmail());
		    if(user.getCreateDate()!=null){
		    	 r.setCreateDate(sdfFull.format(user.getCreateDate()));
		    }else{
		    	 r.setCreateDate("");
		    }
		   
		    
		    String company="";
		    if(user.getProfile().getCompany()!=null){
		    	company=user.getProfile().getCompany();
		    }
		    r.setCompany(company);
			JSONArray arr= new JSONArray();
			String role="";
			for(UserRole ur:user.getUserRoles()){
			   //arr.put(ur.getRoleId());
				role=ur.getRoleId();
			}
			r.setRole(role);
			list.add(r);
		}
		
		return list;
	}

	@Override
	@Transactional
	public Response registerNewUser(UserForm userForm,boolean admin) {
		Response response= new Response();
		if(userRegistrationService.isUserExist(userForm.getUsername(),userForm.getIcNo())){
			response.setSuccess(false);
			response.setMessage("User already exist");
			return response;
		}
		
		User user = new User();
		user.setUsername(userForm.getUsername());
		user.setPassword(userRegistrationService.encryptPassword(userForm.getPassword()));
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		if(!admin){
		userRole.setRoleId("ROLE_REQUESTER");	
		user.setActivationId(userRegistrationService.generateActivationID());
		
		user.setStatus("NA");
		}else{
			if(userForm.isStatus()){
				user.setStatus("A");
			}else{
				user.setStatus("NA");
			}
		}
		
		if(userForm.getRole()!=null){
			if(userForm.getRole().getId()!=null){
				userRole.setRoleId(userForm.getRole().getId());	
			}
		}
		
		Profile p=new Profile();
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
		
		if(userForm.getPersonCategory() !=null){
			if(userForm.getPersonCategory().getId()!=null){
				PersonCategory pc= personCategoryRepository.findOne(userForm.getPersonCategory().getId());
				p.setPersonCategory(pc);
			}
		}
		
		if(userForm.getRelation()!=null){
			if(userForm.getRelation().getId()!=null){
				Relation relation =relationRepository.findOne(userForm.getRelation().getId());
				p.setRelation(relation);
			}
		}
		
		if(userForm.getRegion() !=null){
			if(userForm.getRegion().getId()!=null){
				Region r= regionRepository.findOne(userForm.getRegion().getId());
				p.setRegion(r);
			}
		}
		if(userForm.getRegionStaff() !=null){
			if(userForm.getRegionStaff().getId()!=null){
				Region r= regionRepository.findOne(userForm.getRegionStaff().getId());
				p.setRegion(r);
			}
		}
		if(userForm.getPlan()!=null){
			if(userForm.getPlan().getId()!=null){
				Plan plan= planRepository.findOne(userForm.getPlan().getId());
				p.setPlan(plan);
			}
		}
		
		if(userForm.getCountry()!=null){
			if(userForm.getCountry().getId()!=null){
				Country c= countryRepository.findOne(userForm.getCountry().getId());
				p.setCountry(c);
			}
		}
		if(userForm.getState()!=null){
			if(userForm.getState().getId()!=null){
				State s= stateRepository.findOne(userForm.getState().getId());
				p.setState(s);
			}
		}
		p=profileRepository.save(p);
		user.setProfile(p);
		if(!admin){
			userRegistrationService.sendActivationID(user);
		}
		
		
		if(!StringUtils.isEmpty(userForm.getGroups())){
		
			
			Group group= groupRepository.findOne(userForm.getGroups());
			StaffGroup sg = new StaffGroup();
			sg.setGroup(group);
			sg.setProfile(p);
			staffGroupRepository.save(sg);
			
			
		
		}
		
		
		userRepository.save(user);
		userRoleRepository.save(userRole);
		
		return response;
	}

	@Override
	public UserForm findUserByUsername(String username) {
		User user=userRepository.findOne(username);
		Integer idp=user.getProfile().getId();
		UserForm userForm =new UserForm();
		Profile p = profileRepository.findOne(user.getProfile().getId());
		user.setProfile(p);
		userForm.setUsername(username);
	
		userForm.setName(user.getProfile().getName());
		userForm.setEmail(user.getProfile().getEmail());
		userForm.setIcNo(user.getProfile().getIcNo());
		userForm.setSettlerNo(user.getProfile().getSettlerNo());
		userForm.setStaffNo(user.getProfile().getStaffNo());
		userForm.setLastLogin(user.getLastLogin());
		userForm.setLastIp(user.getLastIp());
		boolean status=false;
		if(user.getStatus()!=null){
		if(user.getStatus().equals("A")){
			status=true;
		}
		}
		userForm.setStatus(status);
		
		List<UserRole> userRoles = userRoleRepository.findByUser(user);
		UserRole userRole=userRoles.get(0);
		Role role=new Role();
		role= roleRepository.findOne(userRole.getRoleId());
		userForm.setRole(role);
		userForm.setPersonCategory(user.getProfile().getPersonCategory());
		userForm.setCompany(user.getProfile().getCompany());
		userForm.setAddress1(user.getProfile().getAddress1());
		userForm.setAddress2(user.getProfile().getAddress2());
		userForm.setCity(user.getProfile().getCity());
		userForm.setCreateDate(user.getCreateDate());
		Country country=new Country();
		State state=new State();
		if(user.getProfile().getCountry()!=null){
			if(user.getProfile().getCountry().getId()!=null){
				country = countryRepository.findOne(user.getProfile().getCountry().getId());
			  
			}
		
		}
		if(user.getProfile().getState()!=null){
			if(user.getProfile().getState().getId()!=null){
				state=stateRepository.findOne(user.getProfile().getState().getId());
				
			}
		
		}
		userForm.setCountry(country);
		userForm.setState(state);
		userForm.setPhoneNo(user.getProfile().getPhoneNo());
		userForm.setMobileNo(user.getProfile().getMobileNo());
		userForm.setFaxNo(user.getProfile().getFaxNo());
		userForm.setRegion(user.getProfile().getRegion());
		userForm.setRegionStaff(user.getProfile().getRegion());
		userForm.setPlan(user.getProfile().getPlan());
		String img=contextPath+"resources/img/examples/users/no-photo.jpg";
		if(user.getProfile().getPhoto()!=null){
			img="data:image/jpg;base64,"+Base64.encodeBase64String(user.getProfile().getPhoto());
		}
		userForm.setPhoto(img);
		
		JSONArray arr= new JSONArray();
		List<StaffGroup> lsg= staffGroupRepository.findByProfile(user.getProfile());
		if(lsg.size()!=0){
			String[] ids=new String[lsg.size()];
			int i=0;
			String grp="";
			for(StaffGroup sg: lsg){
				
				grp=sg.getGroup().getId();
				
				ids[i]=sg.getGroup().getId();
				i++;
			}
			userForm.setGroupIds(ids);
			userForm.setGroups(grp);
		}
		
		
		
		return userForm;
	}

	@Override
	@Transactional
	public Response updateUser(UserForm userForm) {
		Response response= new Response();
		
		User user=userRepository.findOne(userForm.getUsername());
		user.setUsername(userForm.getUsername());
		if(!StringUtils.isEmpty(userForm.getPassword())){
		user.setPassword(userRegistrationService.encryptPassword(userForm.getPassword()));
		}
		
		//userRole.setUser(user);
		
	
		
		if(userForm.isStatus()){
			user.setStatus("A");
		}else{
			user.setStatus("NA");
		}
		
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
		if(userForm.getRegionStaff() !=null){
			if(userForm.getRegionStaff().getId()!=null){
				Region r= regionRepository.findOne(userForm.getRegionStaff().getId());
				p.setRegion(r);
			}else{
				p.setRegion(null);
			}
		}
			
			//check group for delete
			List<StaffGroup> list= staffGroupRepository.findByProfile(p);
			for(StaffGroup sg:list){
				boolean found=false;
				if(userForm.getGroups()!=null){
					if(sg.getGroup().getId().equals(userForm.getGroups())){
						found=true;
					}
				
				}
				if(!found){
					staffGroupRepository.delete(sg);
				}
				
			}
			//add new group
			if(!StringUtils.isEmpty(userForm.getGroups())){
					Group group= groupRepository.findOne(userForm.getGroups());
					List<StaffGroup> lsg= staffGroupRepository.findByProfileAndGroup(p, group);
					if(lsg.size()==0){
						StaffGroup sg =new StaffGroup();
						sg.setGroup(group);
						sg.setProfile(p);
						staffGroupRepository.save(sg);
					}
			}
				
			
			if(userForm.getPersonCategory() !=null){
				if(userForm.getPersonCategory().getId()!=null){
					PersonCategory pc= personCategoryRepository.findOne(userForm.getPersonCategory().getId());
					p.setPersonCategory(pc);
				}else{
					p.setPersonCategory(null);
				}
			}
			if(userForm.getRelation()!=null){
				if(userForm.getRelation().getId()!=null){
					Relation relation =relationRepository.findOne(userForm.getRelation().getId());
					p.setRelation(relation);
				}else{
					p.setRelation(null);
				}
			}
		
		p=profileRepository.save(p);
		user.setProfile(p);
		
		userRepository.save(user);
		if(userForm.getRole()!=null){
			
			if(userForm.getRole().getId()!=null){
				List<UserRole> userRoles = userRoleRepository.findByUser(user);
				UserRole userRole=userRoles.get(0);
				userRole.setRoleId(userForm.getRole().getId());
				userRoleRepository.save(userRole);
			}
		}
		
		
		return response;
	}

	@Override
	public Response changePassword(UserForm userForm) {
		Response response= new Response();
		User user=userRepository.findOne(userForm.getUsername());
		String password= userRegistrationService.encryptPassword(userForm.getCurrPassword());
		String currPassword = user.getPassword();
		
		if(StringUtils.equals(currPassword, password)){
			response.setSuccess(true);
			user.setPassword(userRegistrationService.encryptPassword(userForm.getPassword()));
			userRepository.save(user);
		}else{
			response.setSuccess(false);
			response.setMessage("Kata Laluan Semasa Tidak Sah");
		}
		
		return response;
	}

	public static void main(String[] args) throws JSONException{
		JSONArray arr= new JSONArray("[1,2,23,5,6,65,9]");
		String s = arr.toString().substring(1,arr.toString().length()-1);
		String[] aa= s.split(",");
		
		System.out.println(aa[2]);
		if(StringUtils.contains(arr.toString(), "3")){
			System.out.println("ado");
		}else{
			System.out.println("Xdo");
		}
		
		System.out.println("Size :"+arr.length());
		
		for(int i=0;i<arr.length();i++){
			System.out.println("value :"+arr.get(i));
		}
		
		
	}

	@Override
	@Transactional
	public boolean resetPassword(String username) {
		boolean result = false;
		User user= userRepository.findProfileByUsername(username);
		if(user!=null){
			String password= RandomGenerator.generateRandomString(8);
			String pass=userRegistrationService.encryptPassword(password);
			user.setPassword(pass);
			try{
				
			    userRepository.save(user);
			    if(emailService.sendEmailForReset(user, password))
				  result=true;
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

}
