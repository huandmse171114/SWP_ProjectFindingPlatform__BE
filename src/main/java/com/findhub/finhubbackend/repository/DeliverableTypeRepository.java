package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;

@Repository
public interface DeliverableTypeRepository extends JpaRepository<DeliverableType, Integer>, Repo<DeliverableType> {
    /**
     * tìm chính xác name
     */
    Optional<DeliverableType> findByName(String name);

    /**
     * tìm tất cả DeliverableType có chính xác
     */
    List<DeliverableType> findAllByNameLike(String name);
}
