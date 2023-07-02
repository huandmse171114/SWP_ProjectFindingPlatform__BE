package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.output.Output;

// @Repository
public interface OutputRepository extends Repo<Output> {
    /**
     * tìm chính xác name
     */
    Optional<Output> findByName(String name);

    /**
     * tìm tất cả DeliverableType có chính xác
     */
    List<Output> findAllByNameContaining(String name);

    boolean existsByName(String name);

}
