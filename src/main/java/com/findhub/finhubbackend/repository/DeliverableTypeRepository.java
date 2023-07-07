package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;

// @Repository
public interface DeliverableTypeRepository extends Repo<DeliverableType> {
    /**
     * tìm chính xác name
     */
    Optional<DeliverableType> findByName(String name);

    /**
     * tìm tất cả DeliverableType có chính xác
     */
    List<DeliverableType> findAllByNameContaining(String name);

    boolean existsByName(String name);

}
