package com.huemedia.cms.service;

import java.util.List;

import com.huemedia.cms.model.entity.Notification;
import com.huemedia.cms.web.form.MasterForm;

public interface NotificationService {
  List<Notification> getNotifications();
  boolean save(MasterForm form);
}
