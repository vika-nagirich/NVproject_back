package com.nagirich.datasense.repository;

import com.nagirich.datasense.entity.EndpointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointRepository extends JpaRepository<EndpointEntity, Long> {
}
