package com.huemedia.cms.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.huemedia.cms.model.entity.Notification;

public interface NotificationRepository extends PagingAndSortingRepository<Notification, String> {

	
}
