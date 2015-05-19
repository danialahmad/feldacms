package com.huemedia.cms.velocity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huemedia.cms.model.entity.RefVelocityMailTemplate;
import com.huemedia.cms.service.VelocityMailTemplateService;

public class SpringServiceResourceLoader extends ResourceLoader {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private VelocityMailTemplateService velocityMailTemplateService;

	@Override
	public void init(final ExtendedProperties configuration) {
	}

	protected RefVelocityMailTemplate getByName(final String name) {
		if (StringUtils.isEmpty(name)) {
			throw new NullPointerException("Template name was empty or null");
		}
		RefVelocityMailTemplate mailTemplate;
		mailTemplate = velocityMailTemplateService.getVelocityMailTemplate(name);
		if (mailTemplate == null) {
			throw new ResourceNotFoundException("Could not find resource '" + name + "'");
		}
		return mailTemplate;
	}

	@Override
	public InputStream getResourceStream(final String source) throws ResourceNotFoundException {
		final RefVelocityMailTemplate mailTemplate = getByName(source);
		final String content = mailTemplate.getContent();
		if (content == null) {
			throw new ResourceNotFoundException("Template for '" + source + "' is null");
		}
		return new ByteArrayInputStream(content.getBytes());
	}

	@Override
	public boolean isSourceModified(final Resource resource) {
		return (resource.getLastModified() != getLastModified(resource));
	}

	@Override
	public long getLastModified(final Resource resource) {
		long timeStamp = 0;
		final String source = resource.getName();
		final RefVelocityMailTemplate mailTemplate = getByName(source);
		final Date date = mailTemplate.getTimestamp();
		if (date != null) {
			timeStamp = date.getTime();
		}
		return timeStamp;
	}

}
