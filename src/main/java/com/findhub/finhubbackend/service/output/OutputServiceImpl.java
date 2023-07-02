package com.findhub.finhubbackend.service.output;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.output.Output;
import com.findhub.finhubbackend.entity.output.OutputStatus;
import com.findhub.finhubbackend.repository.OutputRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class OutputServiceImpl
        extends ServiceImpl<Output, OutputRepository, OutputStatus>
        implements OutputService {

    @Override
    public List<Output> findAllByNameContaining(String name) {
        return repo.findAllByNameContaining(name);
    }

    @Override
    public Optional<Output> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

}
