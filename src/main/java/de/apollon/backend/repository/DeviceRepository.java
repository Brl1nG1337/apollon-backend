package de.apollon.backend.repository;

import de.apollon.backend.model.devices.ApollonDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<ApollonDevice, Long> {
}
