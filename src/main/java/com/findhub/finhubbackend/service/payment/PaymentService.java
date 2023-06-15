package com.findhub.finhubbackend.service.payment;

import java.sql.Date;
import java.util.List;

import com.findhub.finhubbackend.entity.payment.Payment;
import com.findhub.finhubbackend.entity.payment.PaymentStatus;
import com.findhub.finhubbackend.service.service.Service;

public interface PaymentService extends Service<Payment, PaymentStatus> {
    /**
     * tìm tất cả payment có ProjectId chính xác
     */
    public List<Payment> findAllByProjectId(int projectId);

    /**
     * tìm tất cả payment có ProjectId gần đúng
     */
    public List<Payment> findAllByProjectIdStartingWith(int projectId);

    /**
     * tìm tất cả payment có TeamId chính xác
     */
    public List<Payment> findAllByTeamId(int teamId);

    /**
     * tìm tất cả payment có TeamId gần đúng
     */
    public List<Payment> findAllByTeamIdStartingWith(int teamId);

    public List<Payment> findAllByAmount(float amount);

    public List<Payment> findAllByAmountGreaterThanEqual(float amount);

    public List<Payment> findAllByAmountLessThanEqual(float amount);

    public List<Payment> findAllByAmountBetween(float start, float end);

    /**
     * tìm tất cả payment theo date
     */
    public List<Payment> findAllByCreateAt(Date date);

    /**
     * tìm tất cả payment theo date before
     */
    public List<Payment> findAllByCreateAtBefore(Date date);

    /**
     * tìm tất cả payment theo date after
     */
    public List<Payment> findAllByCreateAtAfter(Date date);

    /**
     * tìm tất cả payment từ date tới date
     */
    public List<Payment> findAllByCreateAtBetween(Date startDate, Date endDate);

    public boolean existsByTeamIdAndProjectId(int teamId, int projectId);

}
