package com.findhub.finhubbackend.entity.payment;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.findhub.finhubbackend.entity.entity.MyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "Payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends MyEntity {

    @Id
    @Column(
        name = "Id",
        nullable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(
        name = "ProjectId",
        nullable = false
    )
    private int projectId;

    @Column(
        name = "TeamId",
        nullable = false
    )
    private int teamId;

    @Column(
        name = "Amount",
        nullable = false
    )
    @Default
    private float amount = 0;

    @Column(
        name = "CreateDate",
        nullable = false
    )
    @Default
    private Date createDate = new Date(System.currentTimeMillis());

    @Column(
        name = "Description",
        nullable = false
    )
    @Nationalized
    @Default
    private String description = "";

    @Column(
        name = "Status",
        nullable = false
    )
    @Default
    private int status = PaymentStatus.PENDING.getValue();
}
