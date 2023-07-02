package com.findhub.finhubbackend.service.output;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.output.Output;
import com.findhub.finhubbackend.entity.output.OutputStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface OutputService extends Service<Output, OutputStatus> {

    /**
     * tìm chính xác name
     */
    public Optional<Output> findByName(String name);

    /**
     * tìm tất cả DeliverableType có chính xác
     */
    public List<Output> findAllByNameContaining(String name);

    public boolean existsByName(String name);

}
