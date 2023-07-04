package com.findhub.finhubbackend.service.publisher;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.dto.PublisherDTO;
import com.findhub.finhubbackend.entity.publisher.Publisher;
import com.findhub.finhubbackend.entity.publisher.PublisherStatus;
import com.findhub.finhubbackend.model.response.PublisherResponseModel;
import com.findhub.finhubbackend.repository.PublisherRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;
import com.findhub.finhubbackend.util.Utils;

@Service
public class PublisherServiceImpl extends ServiceImpl<Publisher, PublisherRepository, PublisherStatus>
        implements PublisherService {

    @Override
    public List<Publisher> findAllByBalance(float balance) {
        return repo.findAllByBalance(balance);
    }

    @Override
    public List<PublisherDTO> getAllByNameContainingOrEmailContainingOrPhoneContaining(String input) {
        return repo.getAllByNameContainingOrEmailContainingOrPhoneContaining(input, input, input);
    }

    @Override
    public List<PublisherDTO> getAllByNameContaining(String name) {
        return repo.getAllByNameContaining(name);
    }

    @Override
    public List<PublisherDTO> getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(String input) {
        int id = Integer.parseInt(input);
        return repo.getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(id, input, input, input);
    }

    @Override
    public List<Publisher> findAllByBalanceBetween(float start, float end) {
        return repo.findAllByBalanceBetween(start, end);
    }

    @Override
    public List<Publisher> findAllByEmailContaining(String email) {
        return repo.findAllByEmailContaining(email);
    }

    @Override
    public List<Publisher> findAllByNameContaining(String name) {
        return repo.findAllByNameContaining(name);
    }

    @Override
    public List<Publisher> findAllByPhoneLike(String phone) {
        return repo.findAllByPhoneLike(phone);
    }

    @Override
    public Optional<Publisher> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<Publisher> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Optional<Publisher> findByPhone(String phone) {
        return repo.findByPhone(phone);
    }

    @Override
    public PublisherResponseModel getResponseModelById(int id) {
        Publisher publisher = get(id);

        if (publisher == null)
            return null;

        String status = PublisherStatus.nameOf(publisher.getStatus());

        return PublisherResponseModel
            .builder()
                .id(id)
                .name(publisher.getName())
                .email(publisher.getEmail())
                .phone(publisher.getPhone())
                .DOB(publisher.getDob())
                .balance(publisher.getBalance())
                .status(
                    Utils.capitalize(status)
                )
            .build();
    }
}
