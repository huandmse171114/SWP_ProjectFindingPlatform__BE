package com.findhub.finhubbackend.service.major;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.major.MajorStatus;
import com.findhub.finhubbackend.repository.MajorRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class MajorServiceImpl extends ServiceImpl<Major, MajorRepository, MajorStatus>
		implements MajorService {

	@Override
	public List<Major> findAllByCodeLike(String code) {
		return repo.findAllByCodeLike(code);
	}

	@Override
	public List<Major> findAllByNameLike(String name) {
		return repo.findAllByNameLike(name);
	}

	@Override
	public Optional<Major> findByCode(String code) {
		return repo.findByCode(code);
	}

	@Override
	public Optional<Major> findByName(String name) {
		return repo.findByName(name);
	}
}
