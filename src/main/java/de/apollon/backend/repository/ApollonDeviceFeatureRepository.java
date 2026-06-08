package de.apollon.backend.repository;

import de.apollon.backend.model.devices.ApollonDeviceFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApollonDeviceFeatureRepository extends JpaRepository<ApollonDeviceFeature, Long> {
    Optional<ApollonDeviceFeature> findByStateTopic(String stateTopic);
}