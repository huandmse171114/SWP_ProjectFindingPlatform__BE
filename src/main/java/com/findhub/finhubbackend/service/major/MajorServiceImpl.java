package com.findhub.finhubbackend.service.major;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.repository.MajorRepository;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorRepository majorRepository;
}
