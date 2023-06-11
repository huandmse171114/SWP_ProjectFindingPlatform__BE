package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.major.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer>, Repo<Major> {
    /**
     * tìm chính xác name
     */
    Optional<Major> findByName(String name);

    /**
     * tìm tất cả Major có chính xác
     */
    List<Major> findAllByNameContaining(String name);

    /**
     * tìm chính xác major
     */
    Optional<Major> findByCode(String code);

    /**
     * tìm tất cả major có Name chính xác
     */
    List<Major> findAllByCodeStartingWith(String code);

}
