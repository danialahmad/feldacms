package com.huemedia.cms.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.huemedia.cms.model.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, String> {
	@Query(nativeQuery = true, value = "select count(*) from ticket_assignment t where date(t.assign_date) = date(now()) and t.assignee_id=?1")
	List<Object> todayCaseByProfile(Integer id);
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket_assignment t where week(t.assign_date) = week(now()) and t.assignee_id=?1")
	List<Object> weekCaseByProfile(Integer id);
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket_assignment t where month(t.assign_date) = month(now()) and year(t.assign_date)=year(now()) and t.assignee_id=?1")
	List<Object> monthCaseByProfile(Integer id);
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket_assignment t where year(t.assign_date) = year(now()) and t.assignee_id=?1")
	List<Object> yearCaseByProfile(Integer id);
	
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket_assignment t where date(t.create_date) = date(now()) and t.supervisor_id=?1")
	List<Object> todayCaseBySupervisor(Integer id);
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket_assignment t where week(t.create_date) = week(now()) and t.supervisor_id=?1")
	List<Object> weekCaseBySupervisor(Integer id);
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket_assignment t where month(t.create_date) = month(now()) and year(t.create_date)=year(now()) and t.supervisor_id=?1")
	List<Object> monthCaseBySupervisor(Integer id);
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket t where year(t.create_date) = year(now()) and t.supervisor_id=?1")
	List<Object> yearCaseBySupervisor(Integer id);
	
	
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket t where date(t.create_date) = date(now())")
	List<Object> todayCase();
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket t where week(t.create_date) = week(now())")
	List<Object> weekCase();
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket t where month(t.create_date) = month(now()) and year(t.create_date)=year(now())")
	List<Object> monthCase();
	
	
	@Query(nativeQuery = true, value = "select count(*) from ticket t where year(t.create_date) = year(now())")
	List<Object> yearCase();
	
	@Query(nativeQuery = true, value = "select year(t.create_date),count(*) from ticket t group by year(t.create_date) order by year(t.create_date) desc limit 0,5")
	List<Object> yearStatistics();
	
	@Query(nativeQuery = true, value = "select s.name,count(t.id) from `status` s left join ticket t on t.`status`=s.id group by s.id order by rank asc")
	List<Object> countStatus();
	
	@Query(nativeQuery = true, value = "select p.name,count(t.id) from priority p left join ticket t on t.priority=p.id left join ticket_assignment a on a.ticket_id=t.id where a.assignee_id=?1 group by p.id order by p.rank asc")
	List<Object> countPriorityByProfile(Integer id);
	
	@Query(nativeQuery = true, value = "select p.name,count(t.id) from priority p left join ticket t on t.priority=p.id left join ticket_assignment a on a.ticket_id=t.id where a.supervisor_id=?1 group by p.id order by p.rank asc")
	List<Object> countPriorityBySupervisor(Integer id);
	
	
	@Query(nativeQuery = true, value = "select p.name,count(t.id) from priority p left join ticket t on t.priority=p.id group by p.id order by p.rank asc")
	List<Object> countPriority();
}
