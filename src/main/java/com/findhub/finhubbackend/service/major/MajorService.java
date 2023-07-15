package com.findhub.finhubbackend.service.major;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.dto.MajorDTO;
import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.major.MajorStatus;
import com.findhub.finhubbackend.model.response.MajorResponseModel;
import com.findhub.finhubbackend.model.update.MajorUpdateModel;
import com.findhub.finhubbackend.service.service.Service;

public interface MajorService extends Service<Major, MajorStatus> {

    /**
     * tìm chính xác name
     */
    public Optional<Major> findByName(String name);

    /**
     * tìm tất cả Major có chính xác
     */
    public List<Major> findAllByNameContaining(String name);

    /**
     * tìm chính xác major
     */
    public Optional<Major> findByCode(String code);

    /**
     * tìm tất cả major có Name chính xác
     */
    public List<Major> findAllByCodeStartingWith(String code);

    public List<MajorDTO> getAllByNameLikeOrCodeLikeOrIdLike(String input);

    public List<MajorDTO> getAllByNameLikeOrCodeLike(String input);

    public boolean existsByName(String name);

    public boolean existsByCode(String code);
    
    public MajorResponseModel getModel(int id);

	public boolean update(MajorUpdateModel majorModel);
}
