package com.huemedia.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.entity.ActivityType;
import com.huemedia.cms.model.entity.Category;
import com.huemedia.cms.model.entity.Contact;
import com.huemedia.cms.model.entity.Country;
import com.huemedia.cms.model.entity.Escalation;
import com.huemedia.cms.model.entity.Group;
import com.huemedia.cms.model.entity.KnowledgeCategory;
import com.huemedia.cms.model.entity.Originator;
import com.huemedia.cms.model.entity.PersonCategory;
import com.huemedia.cms.model.entity.Plan;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.Profile;
import com.huemedia.cms.model.entity.Rating;
import com.huemedia.cms.model.entity.Region;
import com.huemedia.cms.model.entity.Relation;
import com.huemedia.cms.model.entity.Role;
import com.huemedia.cms.model.entity.State;
import com.huemedia.cms.model.entity.Status;
import com.huemedia.cms.model.entity.TicketCategory;
import com.huemedia.cms.model.entity.TicketGroup;
import com.huemedia.cms.model.repository.ActivityTypeRepository;
import com.huemedia.cms.model.repository.CategoryRepository;
import com.huemedia.cms.model.repository.ContactRepository;
import com.huemedia.cms.model.repository.CountryRepository;
import com.huemedia.cms.model.repository.EscalationRepository;
import com.huemedia.cms.model.repository.GroupRepository;
import com.huemedia.cms.model.repository.KnowledgeCategoryRepository;
import com.huemedia.cms.model.repository.OriginatorRepository;
import com.huemedia.cms.model.repository.PersonCategoryRepository;
import com.huemedia.cms.model.repository.PlanRepository;
import com.huemedia.cms.model.repository.PriorityRepository;
import com.huemedia.cms.model.repository.ProfileRepository;
import com.huemedia.cms.model.repository.RatingRepository;
import com.huemedia.cms.model.repository.RegionRepository;
import com.huemedia.cms.model.repository.RelationRepository;
import com.huemedia.cms.model.repository.RoleRepository;
import com.huemedia.cms.model.repository.StateRepository;
import com.huemedia.cms.model.repository.StatusRepository;
import com.huemedia.cms.model.repository.TicketCategoryRepository;
import com.huemedia.cms.model.repository.TicketGroupRepository;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	TicketGroupRepository ticketGroupRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TicketCategoryRepository ticketCategoryRepository;
	@Autowired
	OriginatorRepository originatorRepository;
	@Autowired
	RelationRepository relationRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	PriorityRepository priorityRepository;
	@Autowired
	EscalationRepository escalationRepository;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	ActivityTypeRepository activityTypeRepository;
	@Autowired
	ContactRepository contactRepository;
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	KnowledgeCategoryRepository knowledgeCategoryRepository;
	
	@Autowired
	PersonCategoryRepository personCategory;
	@Autowired
	PlanRepository planRepository;
	
	@Override
	public List<TicketGroup> getTicketGroupList() {
		// TODO Auto-generated method stub
		return (List<TicketGroup>) ticketGroupRepository.findAll();
	}

	@Override
	public List<TicketCategory> getTicketCategoryList(TicketGroup ticketGroup) {
		// TODO Auto-generated method stub
		return ticketCategoryRepository.findByTicketGroup(ticketGroup);
	}

	@Override
	public List<TicketCategory> getTicketCategoryList(Integer ticketGroupId) {
		TicketGroup ticketGroup=ticketGroupRepository.findOne(ticketGroupId);
		return ticketCategoryRepository.findByTicketGroup(ticketGroup);
	}

	@Override
	public List<TicketCategory> getTicketCategoryList() {
		// TODO Auto-generated method stub
		return (List<TicketCategory>) ticketCategoryRepository.findAll();
	}

	@Override
	public List<Status> getStatusList() {
		Order o= new Order(Direction.ASC,"rank");
		return (List<Status>) statusRepository.findAll(new Sort(o));
	}
	@Override
	public List<Status> getStatusList(List<String> ids) {
		// TODO Auto-generated method stub
		return (List<Status>) statusRepository.findByIdInOrderByRankAsc(ids);
	}

	@Override
	public List<Originator> getOriginatorList() {
		// TODO Auto-generated method stub
		return (List<Originator>) originatorRepository.findAll();
	}

	@Override
	public List<Priority> getPriorityList() {
		Order o= new Order(Direction.ASC,"rank");
		return (List<Priority>) priorityRepository.findAll(new Sort(o));
	}

	@Override
	public List<Escalation> getEscalationList() {
		// TODO Auto-generated method stub
		return (List<Escalation>) escalationRepository.findAll();
	}

	@Override
	public List<Profile> getProfileList(String groupId,Integer regionId) {
		// TODO Auto-generated method stub
		if(regionId!=null){
			return profileRepository.findByGroupAndRegion(groupId, regionId);
		}
		return profileRepository.findByGroup(groupId);
	}

	@Override
	public List<Group> getGroupList() {
		// TODO Auto-generated method stub
		return (List<Group>) groupRepository.findAll();
	}

	@Override
	public List<ActivityType> getActivityTypeList() {
		// TODO Auto-generated method stub
		return (List<ActivityType>) activityTypeRepository.findAll();
	}

	@Override
	public List<Contact> getContactList() {
		// TODO Auto-generated method stub
		return (List<Contact>) contactRepository.findAll();
	}

	@Override
	public List<Rating> getRatingList() {
		// TODO Auto-generated method stub
		return (List<Rating>) ratingRepository.findAll();
	}

	@Override
	public List<Country> getCountryList() {
		// TODO Auto-generated method stub
		return countryRepository.findAllWithOrderBy();
	}

	@Override
	public List<State> getStateList(Integer countryId) {
		Country country =countryRepository.findOne(countryId);
		return stateRepository.findByCountry(country);
	}

	@Override
	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public List<Region> getRegionList() {
		// TODO Auto-generated method stub
		return (List<Region>) regionRepository.findAll();
	}

	@Override
	public List<PersonCategory> getPersonCategoryList() {
		// TODO Auto-generated method stub
		return (List<PersonCategory>) personCategory.findAll();
	}

	@Override
	public List<Plan> getPlanRepositoryList(Integer regionId) {
		Region region=regionRepository.findOne(regionId);
		return planRepository.findByRegion(region);
	}

	@Override
	public List<KnowledgeCategory> getKnowledgeCategoryList() {
		// TODO Auto-generated method stub
		return (List<KnowledgeCategory>) knowledgeCategoryRepository.findAll();
	}

	@Override
	public List<Relation> getRelationList() {
		// TODO Auto-generated method stub
		return (List<Relation>) relationRepository.findAll();
	}

	@Override
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		return (List<Category>) categoryRepository.findAll();
	}

}
