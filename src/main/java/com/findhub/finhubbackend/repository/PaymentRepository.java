package com.findhub.finhubbackend.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.payment.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>, Repo<Payment> {
    /**
     * tìm tất cả payment có ProjectId chính xác
     */
    List<Payment> findAllByProjectId(int projectId);

    /**
     * tìm tất cả payment có ProjectId gần đúng
     */
    List<Payment> findAllByProjectIdStartingWith(int projectId);

    /**
     * tìm tất cả payment có TeamId chính xác
     */
    List<Payment> findAllByTeamId(int teamId);

    /**
     * tìm tất cả payment có TeamId gần đúng
     */
    List<Payment> findAllByTeamIdStartingWith(int teamId);

    List<Payment> findAllByAmount(float amount);

    List<Payment> findAllByAmountGreaterThanEqual(float amount);

    List<Payment> findAllByAmountLessThanEqual(float amount);

    List<Payment> findAllByAmountBetween(float start, float end);

    /**
     * tìm tất cả payment theo date
     */
    List<Payment> findAllByCreateDate(Date date);

    /**
     * tìm tất cả payment theo date before
     */
    List<Payment> findAllByCreateDateBefore(Date date);

    /**
     * tìm tất cả payment theo date after
     */
    List<Payment> findAllByCreateDateAfter(Date date);

    /**
     * tìm tất cả payment từ date tới date
     */
    List<Payment> findAllByCreateDateBetween(Date startDate, Date endDate);

    boolean existsByTeamIdAndProjectId(int teamId, int projectId);

}
