package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.dto.MajorDTO;
import com.findhub.finhubbackend.entity.major.Major;

// @Repository
public interface MajorRepository extends Repo<Major> {
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

    @Query(value = """
                SELECT *
                FROM Major
                WHERE
                    Name LIKE %:name%
                OR
                    Code LIKE :code%
                OR
                    Id LIKE :id%
            """, nativeQuery = true)
    List<MajorDTO> getAllByNameLikeOrCodeLikeOrIdLike(
            @Param("id") int id,
            @Param("code") String code,
            @Param("name") String name);

    @Query(value = """
                SELECT *
                FROM Major
                WHERE
                    Name LIKE %:name%
                OR
                    Code LIKE :code%
            """, nativeQuery = true)
    List<MajorDTO> getAllByNameLikeOrCodeLike(
            @Param("code") String code,
            @Param("name") String name);

    boolean existsByName(String name);

    boolean existsByCode(String code);

}
