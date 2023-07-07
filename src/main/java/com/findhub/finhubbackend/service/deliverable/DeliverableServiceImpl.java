package com.findhub.finhubbackend.service.deliverable;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;
import com.findhub.finhubbackend.entity.deliverableType.DeliverableStatus;
import com.findhub.finhubbackend.repository.DeliverableTypeRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class DeliverableServiceImpl
        extends ServiceImpl<DeliverableType, DeliverableTypeRepository, DeliverableStatus>
        implements DeliverableService {

    @Override
    public List<DeliverableType> findAllByNameContaining(String name) {
        return repo.findAllByNameContaining(name);
    }

    @Override
    public Optional<DeliverableType> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

}
