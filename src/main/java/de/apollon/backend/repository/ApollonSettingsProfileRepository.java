package de.apollon.backend.repository;

import de.apollon.backend.model.settings.ApollonSettingsProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApollonSettingsProfileRepository extends JpaRepository<ApollonSettingsProfile, Long> {
    Optional<ApollonSettingsProfile> findByName(String name);
}