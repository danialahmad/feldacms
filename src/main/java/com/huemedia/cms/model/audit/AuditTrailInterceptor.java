package com.huemedia.cms.model.audit;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.huemedia.cms.model.entity.Ticket;
import com.huemedia.cms.model.repository.TicketRepository;


public class AuditTrailInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
   
	
	
	@Override
	public boolean onFlushDirty(final Object entity, final Serializable id,
			final Object[] currentState, final Object[] previousState,
			final String[] propertyNames, final Type[] types) {
		String updatedBy = "";
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
			updatedBy = SecurityContextHolder.getContext().getAuthentication().getName();
		} else {
			updatedBy = "anonymousUser";
		}
		setValue(currentState, propertyNames, "updateBy", updatedBy);
		setValue(currentState, propertyNames, "updateDate", new Date());
		
		
		
		return true;
	}

	@Override
	public boolean onSave(final Object entity, final Serializable id,
			final Object[] state, final String[] propertyNames,
			final Type[] types) {
		String createdBy = "";
		if(SecurityContextHolder.getContext().getAuthentication()!=null){
			createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
		} else {
			createdBy = "anonymousUser";
		}
		
		if (entity instanceof Ticket){
			Ticket t=(Ticket) entity;
			System.out.println("ONE TICKET HAS BEEN CREATED ON :"+new Date());
			System.out.println("ONE TICKET HAS BEEN CREATED BY :"+createdBy);
			System.out.println("TICKET ID :"+t.getId());
			
		}
	
		setValue(state, propertyNames, "createBy", createdBy);
		setValue(state, propertyNames, "createDate", new Date());
		setValue(state, propertyNames, "updateBy", createdBy);
		setValue(state, propertyNames, "updateDate", new Date());
		return true;
	}

	private void setValue(final Object[] currentState,
			final String[] propertyNames, final String propertyToSet,
			final Object value) {
		//final int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
		int i=0;
		for(String name:propertyNames){
			if(name.equals(propertyToSet)){
				currentState[i] = value;
			}
			i=i+1;
		}
		
		
		//if (index > 0) {
			//currentState[index] = value;
		//}
	}




	
}
