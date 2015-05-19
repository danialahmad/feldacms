package com.huemedia.cms.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huemedia.cms.model.entity.Sequence;
import com.huemedia.cms.model.repository.SequenceRepository;

@Service
public class IDServiceImpl implements IDService {

	@Autowired
	SequenceRepository sequenceRepository;
	
	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public String generateSEQID(String prefix) {
		SimpleDateFormat fyear = new SimpleDateFormat("yyyy");
		SimpleDateFormat fmonth = new SimpleDateFormat("MM");
		Integer year =Integer.parseInt(fyear.format(new Date()));
		Integer month =Integer.parseInt(fmonth.format(new Date()));
		Sequence seq= sequenceRepository.findByPrefixAndYearAndMonth(prefix, year, month);
		if(seq!=null&&seq.getId()!=null){
			seq.setNo(seq.getNo()+1);
		}else{
			seq = new Sequence();
			seq.setPrefix(prefix);
			seq.setYear(year);
			seq.setMonth(month);
			seq.setNo(1);
		}
		sequenceRepository.save(seq);
		String id = seq.getPrefix()+seq.getYear()+seq.getMonth()+digitLastFormatted(seq.getNo().toString(),4);
		return id;
	}
	
	private String digitLastFormatted(String output,int digits){
		//int digits = 4;
		while (output.length() < digits)
			output = "0" + output;
		return output;
	}

}
