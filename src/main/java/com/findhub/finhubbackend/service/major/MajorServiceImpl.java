package com.findhub.finhubbackend.service.major;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.dto.MajorDTO;
import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.major.MajorStatus;
import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.response.MajorResponseModel;
import com.findhub.finhubbackend.model.update.MajorUpdateModel;
import com.findhub.finhubbackend.repository.MajorRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class MajorServiceImpl extends ServiceImpl<Major, MajorRepository, MajorStatus>
		implements MajorService {

	@Override
	public List<Major> findAllByCodeStartingWith(String code) {
		return repo.findAllByCodeStartingWith(code);
	}

	@Override
	public List<Major> findAllByNameContaining(String name) {
		return repo.findAllByNameContaining(name);
	}

	@Override
	public List<MajorDTO> getAllByNameLikeOrCodeLikeOrIdLike(String keyword) {
		int id = Integer.parseInt(keyword);
		String code = keyword;
		return repo.getAllByNameLikeOrCodeLikeOrIdLike(id, code, keyword);
	}

	@Override
	public List<MajorDTO> getAllByNameLikeOrCodeLike(String input) {
		return repo.getAllByNameLikeOrCodeLike(input, input);
	}

	@Override
	public Optional<Major> findByCode(String code) {
		return repo.findByCode(code);
	}

	@Override
	public Optional<Major> findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public boolean existsByCode(String code) {
		return repo.existsByCode(code);
	}

	@Override
	public boolean existsByName(String name) {
		return repo.existsByName(name);
	}

	@Override
	public MajorResponseModel getModel(int id) {
		Major m = get(id);
		
		MajorResponseModel result = MajorResponseModel.builder()
				.id(id)
				.name(m.getName())
				.code(m.getCode())
				.status(new StatusModel(m.getStatus(), MajorStatus.nameOf(m.getStatus())))
				.build();
		return result;
	}

	@Override
	public boolean update(MajorUpdateModel majorModel) {
		repo.update(majorModel.getId(), majorModel.getName(), majorModel.getCode());
		return true;
	}

}
