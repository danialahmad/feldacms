package com.huemedia.cms.service;

import com.huemedia.cms.web.form.Response;
import com.huemedia.cms.web.form.UserForm;

public interface ProfileService {
   UserForm findProfileByUsername(String username);
   Response updateProfile(UserForm userForm);
   void delete(String username);
}
