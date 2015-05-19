package com.huemedia.cms.service;

import java.util.List;

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

public interface CodeService {
   List<TicketGroup> getTicketGroupList();
   List<TicketCategory> getTicketCategoryList(TicketGroup ticketGroup);
   List<TicketCategory> getTicketCategoryList(Integer ticketGroupId);
   List<TicketCategory> getTicketCategoryList();
   List<Category> getCategoryList();
   List<KnowledgeCategory> getKnowledgeCategoryList();
   
   List<Status> getStatusList();
   List<Status> getStatusList(List<String> ids);
   
   List<Originator> getOriginatorList();
   List<Priority> getPriorityList();
   
   List<Escalation> getEscalationList();
   
   List<Profile> getProfileList(String groupId,Integer regionId);
   
   List<Group> getGroupList();
   
   List<ActivityType> getActivityTypeList();
   
   List<Contact> getContactList();
   
   List<Rating> getRatingList();
   
   List<Country> getCountryList();
   List<State> getStateList(Integer countryId);
   
   List<Role> getRoleList();
   
   List<Region> getRegionList();
   
   List<PersonCategory> getPersonCategoryList();
   List<Plan> getPlanRepositoryList(Integer regionId);
   
   List<Relation> getRelationList();
   
}
