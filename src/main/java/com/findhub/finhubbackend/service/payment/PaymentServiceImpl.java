package com.findhub.finhubbackend.service.payment;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.entity.payment.Payment;
import com.findhub.finhubbackend.entity.payment.PaymentStatus;
import com.findhub.finhubbackend.repository.PaymentRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class PaymentServiceImpl extends ServiceImpl<Payment, PaymentRepository, PaymentStatus>
        implements PaymentService {

    @Override
    public List<Payment> findAllByAmount(float amount) {
        return repo.findAllByAmount(amount);
    }

    @Override
    public List<Payment> findAllByAmountBetween(float start, float end) {
        return repo.findAllByAmountBetween(start, end);
    }

    @Override
    public List<Payment> findAllByAmountGreaterThanEqual(float amount) {
        return repo.findAllByAmountGreaterThanEqual(amount);
    }

    @Override
    public List<Payment> findAllByAmountLessThanEqual(float amount) {
        return repo.findAllByAmountLessThanEqual(amount);
    }

    @Override
    public List<Payment> findAllByCreateAt(Date date) {
        return repo.findAllByCreateAt(date);
    }

    @Override
    public List<Payment> findAllByCreateAtAfter(Date date) {
        return repo.findAllByCreateAtAfter(date);
    }

    @Override
    public List<Payment> findAllByCreateAtBefore(Date date) {
        return repo.findAllByCreateAtBefore(date);
    }

    @Override
    public List<Payment> findAllByCreateAtBetween(Date start, Date end) {
        return repo.findAllByCreateAtBetween(start, end);
    }

    @Override
    public List<Payment> findAllByProjectId(int projectId) {
        return repo.findAllByProjectId(projectId);
    }

    @Override
    public List<Payment> findAllByProjectIdStartingWith(int projectId) {
        return repo.findAllByProjectIdStartingWith(projectId);
    }

    @Override
    public List<Payment> findAllByTeamId(int teamId) {
        return repo.findAllByTeamId(teamId);
    }

    @Override
    public List<Payment> findAllByTeamIdStartingWith(int teamId) {
        return repo.findAllByTeamIdStartingWith(teamId);
    }
}
