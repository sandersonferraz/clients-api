package com.dev.san.model.repository;

import com.dev.san.model.entity.ServicesProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<ServicesProvided, UUID> {
}
