package com.findhub.finhubbackend.service.major;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.major.MajorStatus;
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
}
