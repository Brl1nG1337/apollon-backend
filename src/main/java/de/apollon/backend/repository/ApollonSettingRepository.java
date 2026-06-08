package de.apollon.backend.repository;

import de.apollon.backend.model.settings.ApollonSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApollonSettingRepository extends JpaRepository<ApollonSetting, Long> {
    @Query("SELECT s FROM ApollonSettingsProfile p " +
            "JOIN p.categories c " +
            "JOIN c.settings s " +
            "WHERE p.name = :profileName AND s.key = :settingKey")
    Optional<ApollonSetting> findByProfileNameAndSettingKey(
            @Param("profileName") String profileName,
            @Param("settingKey") String settingKey
    );
}