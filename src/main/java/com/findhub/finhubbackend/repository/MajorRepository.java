package com.findhub.finhubbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.major.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
}
