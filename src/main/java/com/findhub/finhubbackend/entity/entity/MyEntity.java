package com.findhub.finhubbackend.entity.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MyEntity {
    @Id
    @Column(
        name = "Id",
        nullable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(
        name = "Status",
        nullable = false
    )
    protected int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
