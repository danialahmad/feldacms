package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huemedia.cms.model.dao.RatingDAO;
import com.huemedia.cms.model.entity.Priority;
import com.huemedia.cms.model.entity.Rating;
import com.huemedia.cms.model.repository.RatingRepository;
import com.huemedia.cms.web.controller.administration.datatables.RatingResult;
import com.huemedia.cms.web.form.MasterForm;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingDAO ratingDAO;
	@Autowired 
	RatingRepository ratingRepository;
	
	@Override
	public Long countAll() {
		// TODO Auto-generated method stub
		return ratingDAO.countAll();
	}

	@Override
	public Long countSearch(MasterForm form) {
		// TODO Auto-generated method stub
		return ratingDAO.countSearch(form);
	}

	@Override
	public List<RatingResult> getRatings(MasterForm form,
			Integer iDisplayStart, Integer iDisplayLength) {
		SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Rating> ratings= ratingDAO.search(form, iDisplayStart, iDisplayLength);
		List<RatingResult> list=new ArrayList<RatingResult>();
		for(Rating p:ratings){
			RatingResult result=new RatingResult();
		    result.setName(p.getName());
		    result.setId(String.valueOf(p.getId()));
		
			list.add(result);
		}
		
		
		return list;
	}

	@Override
	public boolean save(MasterForm form) {
		Rating rating =new Rating();
		if(StringUtils.isNotEmpty(form.getId()) && StringUtils.isNotBlank(form.getId())){
			rating = ratingRepository.findOne(Integer.parseInt(form.getId()));
		}
		rating.setName(form.getName());
		rating.setRank(form.getRank());
		
		ratingRepository.save(rating);
		return false;
	}

	@Override
	public MasterForm findRating(Integer id) {
		MasterForm form = new MasterForm();
		Rating rating=ratingRepository.findOne(id);
		form.setId(String.valueOf(rating.getId()));
		form.setName(rating.getName());
		form.setRank(rating.getRank());
		return form;
	}

	@Override
	public void delete(Integer id) {
		ratingRepository.delete(id);
	}

}
