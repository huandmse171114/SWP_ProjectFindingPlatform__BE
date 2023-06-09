package com.findhub.finhubbackend.service.major;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.repository.MajorRepository;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorRepository majorRepository;

	@Override
	public Major add(Major application) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeStatus(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(Major major) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Major> findApplicationsByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Major> findApplicationsByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Major> findApplicationsByTeamId(int teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Major findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Major> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveApplication(Major application) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Major update(int id, Major application) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
