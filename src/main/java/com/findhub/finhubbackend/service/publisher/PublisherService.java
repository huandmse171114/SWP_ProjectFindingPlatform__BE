package com.findhub.finhubbackend.service.publisher;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.dto.PublisherDTO;
import com.findhub.finhubbackend.entity.publisher.Publisher;
import com.findhub.finhubbackend.entity.publisher.PublisherStatus;
import com.findhub.finhubbackend.model.response.PublisherResponseModel;
import com.findhub.finhubbackend.service.service.Service;

public interface PublisherService extends Service<Publisher, PublisherStatus> {
    /**
     * tìm chính xác name
     */
    public Optional<Publisher> findByName(String name);

    /**
     * tìm tất cả Publisher có chính xác
     */
    public List<Publisher> findAllByNameContaining(String name);

    public Optional<Publisher> findByEmail(String email);

    public List<Publisher> findAllByEmailContaining(String email);

    public Optional<Publisher> findByPhone(String phone);

    public List<Publisher> findAllByPhoneLike(String phone);

    // balance: không cần tìm
    public List<Publisher> findAllByBalance(float balance);

    public List<Publisher> findAllByBalanceBetween(float start, float end);

    public PublisherResponseModel getResponseModelById(int id);

    public List<PublisherDTO> getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(String input);

    public List<PublisherDTO> getAllByNameContainingOrEmailContainingOrPhoneContaining(
            String input);

    public List<PublisherDTO> getAllByNameContaining(String name);
}
