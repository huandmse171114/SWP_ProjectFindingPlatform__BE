package com.findhub.finhubbackend.service.deliverableType;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;
import com.findhub.finhubbackend.entity.deliverableType.DeliverableTypeStatus;
import com.findhub.finhubbackend.repository.DeliverableTypeRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class DeliverableTypeServiceImpl
        extends ServiceImpl<DeliverableType, DeliverableTypeRepository, DeliverableTypeStatus>
        implements DeliverableTypeService {

    @Override
    public List<DeliverableType> findAllByNameLike(String name) {
        return repo.findAllByNameLike(name);
    }

    @Override
    public Optional<DeliverableType> findByName(String name) {
        return repo.findByName(name);
    }

}
