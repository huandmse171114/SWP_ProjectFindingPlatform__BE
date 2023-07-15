package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.findhub.finhubbackend.dto.PublisherDTO;
import com.findhub.finhubbackend.entity.publisher.Publisher;

public interface PublisherRepository extends Repo<Publisher> {

    /**
     * tìm chính xác name
     */
    Optional<Publisher> findByName(String name);

    /**
     * tìm tất cả Publisher có chính xác
     */
    List<Publisher> findAllByNameContaining(String name);

    Optional<Publisher> findByEmail(String email);

    List<Publisher> findAllByEmailContaining(String email);

    Optional<Publisher> findByPhone(String phone);

    List<Publisher> findAllByPhoneLike(String phone);

    // balance: không cần tìm
    List<Publisher> findAllByBalance(float balance);

    List<Publisher> findAllByBalanceBetween(float start, float end);

    List<Publisher> findAllByStatus(int status);

    @Query(value = """
            SELECT *
            FROM
                Publisher
            WHERE
                Name LIKE %:name%
            OR
                Email LIKE %:email%
            OR
                Phone LIKE %:phone%
            OR
                Id LIKE :id%
			ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<PublisherDTO> getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(
            @Param("id") int id,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("name") String name);

    @Query(value = """
            SELECT *
            FROM
                Publisher
            WHERE
                Name LIKE %:name%
            OR
                Email LIKE %:email%
            OR
                Phone LIKE %:phone%
			ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<PublisherDTO> getAllByNameContainingOrEmailContainingOrPhoneContaining(
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("name") String name);

    @Query(value = """
            SELECT *
            FROM
                Publisher
            WHERE
                Name LIKE %:name%
			ORDER BY
                Id ASC
            """, nativeQuery = true)
    List<PublisherDTO> getAllByNameContaining(@Param("name") String name);

    boolean existsByEmail(String email);
    
    @Transactional
    @Modifying
    @Query(value = "update Publisher set Name = :name, Phone = :phone, Email = :email, "
    		+ "Status = :status, DOB = :dob where Id = :id", nativeQuery = true)
	void update(@Param("id") int id, @Param("name") String name, @Param("email") String email, @Param("phone") String phone,
		 @Param("dob") String dob, @Param("status") int status);

    @Transactional
    @Modifying
    @Query(value = "update Publisher set Description = :description where Id = :id", nativeQuery = true)
	void updateDescription(@Param("id") int id, @Param("description") String description);
}
