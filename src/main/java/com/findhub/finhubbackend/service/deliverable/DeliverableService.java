package com.findhub.finhubbackend.service.deliverable;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;
import com.findhub.finhubbackend.entity.deliverableType.DeliverableStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface DeliverableService extends Service<DeliverableType, DeliverableStatus> {

    /**
     * tìm chính xác name
     */
    public Optional<DeliverableType> findByName(String name);

    /**
     * tìm tất cả DeliverableType có chính xác
     */
    public List<DeliverableType> findAllByNameContaining(String name);

    public boolean existsByName(String name);

}
