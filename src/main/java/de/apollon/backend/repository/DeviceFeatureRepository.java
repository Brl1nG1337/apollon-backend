package de.apollon.backend.repository;

import de.apollon.backend.model.DeviceFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceFeatureRepository extends JpaRepository<DeviceFeature, Long> {
    Optional<DeviceFeature> findByStateTopic(String stateTopic);
}